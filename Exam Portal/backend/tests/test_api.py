import os, sys
os.environ.update(APPS_SCRIPT_URL="http://sheets.test/exec", SECRET_KEY="test", APPS_SCRIPT_SECRET="s3", WARMUP="0")
sys.path.insert(0, os.path.dirname(os.path.dirname(__file__)))
import pytest
import app as A

QUESTIONS = [{"questionID": 1, "questionText": "Q one <b>x</b>", "desiredAnswer": "SECRET-REF-1", "totalMarks": 4},
             {"questionID": 2, "questionText": "Q two", "desiredAnswer": "SECRET-REF-2", "totalMarks": 6}]


class FakeResp:
    def __init__(self, data): self.data = data
    def json(self): return self.data


@pytest.fixture
def client(monkeypatch):
    calls = []
    def fake_post(url, params=None, json=None, timeout=None):
        calls.append(json)
        ep = json["endpoint"]
        if ep == "teacherLogin" and json["password"] != "pw":
            return FakeResp({"status": "error", "message": "Invalid credentials"})
        if ep == "getQuestions":
            return FakeResp({"status": "ok", "questions": QUESTIONS})
        return FakeResp({"status": "ok"})
    monkeypatch.setattr(A.requests, "post", fake_post)
    monkeypatch.setattr(A, "score_answers", lambda pairs: [round(p[2] / 2, 2) for p in pairs])
    A._hits.clear(); A._used.clear()
    c = A.app.test_client(); c.calls = calls
    return c


def login(c, role, **kw):
    r = c.post(f"/api/{role}/login", json={"email": "a@b.co", **kw})
    assert r.status_code == 200, r.get_json()
    return {"Authorization": "Bearer " + r.get_json()["token"]}


def test_health(client):
    assert client.get("/api/health").get_json()["ok"] is True
    assert client.get("/").status_code == 200


def test_bad_teacher_password_is_401(client):
    r = client.post("/api/teacher/login", json={"email": "t@b.co", "password": "nope"})
    assert r.status_code == 401 and "credentials" in r.get_json()["message"].lower()


def test_auth_required_and_roles_enforced(client):
    assert client.get("/api/questions").status_code == 401
    student, teacher = login(client, "student"), login(client, "teacher", password="pw")
    assert client.post("/api/questions", json={}, headers=student).status_code == 403
    assert client.get("/api/questions", headers=teacher).status_code == 403


def test_students_never_receive_reference_answers(client):
    r = client.get("/api/questions", headers=login(client, "student"))
    assert "SECRET-REF" not in r.get_data(as_text=True) and "desiredAnswer" not in r.get_data(as_text=True)
    assert [q["questionID"] for q in r.get_json()["questions"]] == ["1", "2"]


def test_submit_scores_on_server_and_ignores_client_scores(client):
    h = login(client, "student")
    r = client.post("/api/submit", headers=h, json={"answers": [
        {"questionID": "1", "answer": "mine", "score": 999}, {"questionID": "2", "answer": ""}]})
    d = r.get_json()
    assert r.status_code == 200 and d["total"] == 5.0 and d["max"] == 10.0
    sent = [c for c in client.calls if c["endpoint"] == "submitResponses"][0]
    assert sent["studentEmail"] == "a@b.co" and sent["secret"] == "s3"
    assert [x["score"] for x in sent["responses"]] == [2.0, 3.0]
    assert "SECRET-REF" not in r.get_data(as_text=True)


def test_second_submit_with_same_login_is_rejected(client):
    h = login(client, "student")
    assert client.post("/api/submit", headers=h, json={"answers": []}).status_code == 200
    assert client.post("/api/submit", headers=h, json={"answers": []}).status_code == 409


def test_failed_submit_can_be_retried(client, monkeypatch):
    h = login(client, "student")
    monkeypatch.setattr(A, "score_answers", lambda p: (_ for _ in ()).throw(ApiErr()))
    class ApiErr(A.ApiError):
        def __init__(self): super().__init__("boom", 500)
    assert client.post("/api/submit", headers=h, json={"answers": []}).status_code == 500
    monkeypatch.setattr(A, "score_answers", lambda p: [0.0] * len(p))
    assert client.post("/api/submit", headers=h, json={"answers": []}).status_code == 200


@pytest.mark.parametrize("q", [
    {"questionText": "", "desiredAnswer": "a", "totalMarks": 1},
    {"questionText": "q", "desiredAnswer": "a", "totalMarks": -3},
    {"questionText": "q", "desiredAnswer": "a", "totalMarks": "abc"},
    {"questionText": "q", "desiredAnswer": "a", "totalMarks": 0},
])
def test_teacher_validation(client, q):
    r = client.post("/api/questions", headers=login(client, "teacher", password="pw"), json={"questions": [q]})
    assert r.status_code == 400 and r.get_json()["status"] == "error"


def test_teacher_can_save(client):
    r = client.post("/api/questions", headers=login(client, "teacher", password="pw"),
                    json={"questions": [{"questionText": "q", "desiredAnswer": "a", "totalMarks": 2}]})
    assert r.get_json()["saved"] == 1
    assert [c for c in client.calls if c["endpoint"] == "postQuestions"][0]["teacherEmail"] == "a@b.co"


def test_malformed_requests_return_json_errors(client):
    assert client.post("/api/student/login", data="x", content_type="text/plain").status_code == 400
    assert client.post("/api/student/login", json={"email": "nope"}).get_json()["status"] == "error"
    assert client.get("/api/nothing").get_json()["status"] == "error"


def test_login_rate_limit(client):
    codes = [client.post("/api/student/login", json={"email": "a@b.co"}).status_code for _ in range(12)]
    assert codes[:10] == [200] * 10 and codes[10] == 429


def test_marks_ramp_and_zero_floor():
    assert A.marks_from_similarity(0.1, 10) == 0 and A.marks_from_similarity(0.9, 10) == 10
    assert A.marks_from_similarity(0.525, 10) == 5.0


# ---- the real scoring path, with a fake embedder standing in for the ONNX model
class FakeEmbedder:
    VEC = {"ref-a": [1, 0, 0], "close": [0.9, 0.436, 0], "far": [0, 1, 0], "ref-b": [0, 0, 1]}
    def embed(self, texts): return iter([self.VEC[t] for t in texts])


def test_score_answers_uses_cosine_and_skips_blank(monkeypatch):
    monkeypatch.setattr(A, "get_model", lambda: FakeEmbedder())
    scores = A.score_answers([("ref-a", "close", 10), ("ref-a", "far", 10), ("ref-b", "  ", 10)])
    assert scores[0] == 10.0  # cosine 0.9 is above SIM_HIGH, so full marks
    assert scores[1] == 0.0 and scores[2] == 0.0


def test_identical_text_gets_full_marks(monkeypatch):
    monkeypatch.setattr(A, "get_model", lambda: FakeEmbedder())
    assert A.score_answers([("ref-a", "ref-a", 7)]) == [7.0]


def test_root_is_json_without_frontend_and_serves_pages_with_it(monkeypatch):
    c = A.app.test_client()
    monkeypatch.setattr(A, "FRONTEND_DIR", "/nonexistent")
    assert c.get("/").get_json()["service"] == "exam-portal-api"
    assert c.get("/login_student.html").status_code == 404
    monkeypatch.setattr(A, "FRONTEND_DIR", os.path.join(os.path.dirname(__file__), "..", "..", "frontend"))
    assert c.get("/").status_code == 200 and b"Exam Portal" in c.get("/").data
    assert c.get("/js/common.js").status_code == 200
    assert c.get("/api/nope").get_json()["status"] == "error"


def test_cors_allows_only_listed_origins(monkeypatch):
    from flask import Flask
    import importlib
    monkeypatch.setenv("ALLOWED_ORIGINS", "https://pages.example")
    mod = importlib.reload(A)
    c = mod.app.test_client()
    ok = c.get("/api/health", headers={"Origin": "https://pages.example"})
    bad = c.get("/api/health", headers={"Origin": "https://evil.example"})
    assert ok.headers.get("Access-Control-Allow-Origin") == "https://pages.example"
    assert "Access-Control-Allow-Origin" not in bad.headers
    monkeypatch.delenv("ALLOWED_ORIGINS"); importlib.reload(A)
