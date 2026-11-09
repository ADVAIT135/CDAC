"""Exam Portal API: sessions, server-side NLP scoring (ONNX MiniLM), Google Sheets (Apps Script) bridge."""
import math
import os
import re
import threading
import time
import uuid
from collections import defaultdict, deque

import requests
from flask import Flask, g, jsonify, request, send_from_directory
from werkzeug.exceptions import NotFound
from flask_cors import CORS
from itsdangerous import BadSignature, SignatureExpired, URLSafeTimedSerializer

# ---------------------------------------------------------------- config
APPS_SCRIPT_URL = os.environ.get("APPS_SCRIPT_URL", "").strip()
APPS_SCRIPT_SECRET = os.environ.get("APPS_SCRIPT_SECRET", "")
SECRET_KEY = os.environ.get("SECRET_KEY") or os.urandom(32).hex()
TOKEN_TTL = int(os.environ.get("TOKEN_TTL_SECONDS", 4 * 3600))
ALLOWED_ORIGINS = [o.strip() for o in os.environ.get("ALLOWED_ORIGINS", "").split(",") if o.strip()]
MODEL_NAME = os.environ.get("MODEL_NAME", "sentence-transformers/all-MiniLM-L6-v2")
MODEL_CACHE = os.environ.get("MODEL_CACHE", os.path.join(os.path.dirname(os.path.abspath(__file__)), "model_cache"))
FRONTEND_DIR = os.environ.get("FRONTEND_DIR", os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "frontend"))
SIM_LOW = float(os.environ.get("SIM_LOW", 0.25))    # cosine at or below this earns 0 marks
SIM_HIGH = float(os.environ.get("SIM_HIGH", 0.80))  # cosine at or above this earns full marks
MAX_ANSWER_CHARS, MAX_QUESTIONS, MAX_TEXT = 4000, 100, 2000
EMAIL_RE = re.compile(r"^[^@\s]+@[^@\s]+\.[^@\s]+$")

app = Flask(__name__, static_folder=None)
if ALLOWED_ORIGINS:  # pages and API are on different hosts, so list the page origins here
    CORS(app, resources={r"/api/*": {"origins": ALLOWED_ORIGINS}}, allow_headers=["Content-Type", "Authorization"])
if "SECRET_KEY" not in os.environ:
    app.logger.warning("SECRET_KEY is not set: sessions reset whenever the server restarts.")
signer = URLSafeTimedSerializer(SECRET_KEY, salt="exam-portal-auth")


class ApiError(Exception):
    def __init__(self, message, status=400):
        super().__init__(message)
        self.message, self.status = message, status


@app.errorhandler(ApiError)
def _api_error(e):
    return jsonify({"status": "error", "message": e.message}), e.status


@app.errorhandler(404)
def _not_found(e):
    if request.path.startswith("/api/"):
        return jsonify({"status": "error", "message": "Not found."}), 404
    return e


@app.errorhandler(500)
def _server_error(e):
    return jsonify({"status": "error", "message": "Something went wrong on the server."}), 500


@app.after_request
def _headers(resp):
    resp.headers.setdefault("X-Content-Type-Options", "nosniff")
    resp.headers.setdefault("Referrer-Policy", "same-origin")
    if request.path.startswith("/api/"):
        resp.headers["Cache-Control"] = "no-store"
        resp.headers.setdefault("Content-Security-Policy", "default-src 'none'; frame-ancestors 'none'")
    else:  # only reached when the pages are served locally for development
        resp.headers.setdefault("Content-Security-Policy", "default-src 'self'; img-src 'self' data:")
    return resp


# ---------------------------------------------------------------- helpers
_hits = defaultdict(deque)


def rate_limit(limit=10, window=60):
    ip = (request.headers.get("X-Forwarded-For", request.remote_addr or "?")).split(",")[0].strip()
    q, now = _hits[ip], time.time()
    while q and now - q[0] > window:
        q.popleft()
    if len(q) >= limit:
        raise ApiError("Too many attempts. Wait a minute and try again.", 429)
    q.append(now)


def body():
    data = request.get_json(silent=True)
    if not isinstance(data, dict):
        raise ApiError("Send a JSON object.")
    return data


def clean_email(v):
    v = str(v or "").strip().lower()
    if not EMAIL_RE.match(v) or len(v) > 254:
        raise ApiError("Enter a valid email address.")
    return v


def call_sheets(endpoint, **fields):
    if not APPS_SCRIPT_URL:
        raise ApiError("The server isn't configured yet (APPS_SCRIPT_URL is missing).", 503)
    payload = {"endpoint": endpoint, **fields}
    if APPS_SCRIPT_SECRET:
        payload["secret"] = APPS_SCRIPT_SECRET
    try:
        r = requests.post(APPS_SCRIPT_URL, params={"endpoint": endpoint}, json=payload, timeout=30)
        data = r.json()
    except requests.Timeout:
        raise ApiError("Google Sheets took too long to respond. Try again.", 504)
    except (requests.RequestException, ValueError):
        raise ApiError("Couldn't reach Google Sheets. Try again shortly.", 502)
    if isinstance(data, dict) and data.get("status") == "error":
        msg = data.get("message") or "Request rejected."
        raise ApiError(msg, 401 if "credential" in msg.lower() or "unauthor" in msg.lower() else 400)
    return data if isinstance(data, dict) else {}


def issue(role, email):
    return signer.dumps({"r": role, "e": email, "j": uuid.uuid4().hex})


def require(role):
    header = request.headers.get("Authorization", "")
    token = header[7:] if header.startswith("Bearer ") else ""
    try:
        data = signer.loads(token, max_age=TOKEN_TTL)
    except SignatureExpired:
        raise ApiError("Your session expired. Please log in again.", 401)
    except BadSignature:
        raise ApiError("Please log in first.", 401)
    if data.get("r") != role:
        raise ApiError("You don't have access to this.", 403)
    g.email, g.jti = data["e"], data["j"]


_used, _used_lock = {}, threading.Lock()


def claim_submission(jti):
    with _used_lock:
        now = time.time()
        for k in [k for k, t in _used.items() if now - t > TOKEN_TTL]:
            del _used[k]
        if jti in _used:
            raise ApiError("You've already submitted this exam.", 409)
        _used[jti] = now


def release_submission(jti):
    with _used_lock:
        _used.pop(jti, None)


# ---------------------------------------------------------------- scoring
_model, _model_lock = None, threading.Lock()


def get_model():
    """Lazy-load the ONNX MiniLM model (same weights as sentence-transformers/all-MiniLM-L6-v2, no PyTorch)."""
    global _model
    with _model_lock:
        if _model is None:
            from fastembed import TextEmbedding
            _model = TextEmbedding(model_name=MODEL_NAME, cache_dir=MODEL_CACHE, threads=1)
    return _model


def marks_from_similarity(sim, total):
    """Linear ramp between SIM_LOW (0 marks) and SIM_HIGH (full marks)."""
    frac = max(0.0, min(1.0, (sim - SIM_LOW) / (SIM_HIGH - SIM_LOW)))
    return round(frac * total, 2)


def embed(texts):
    import numpy as np
    vecs = np.asarray(list(get_model().embed(texts)), dtype="float32")
    return vecs / np.maximum(np.linalg.norm(vecs, axis=1, keepdims=True), 1e-12)


def score_answers(pairs):
    """pairs: [(reference, answer, total_marks)] -> [marks]. Blank answers score 0 without calling the model."""
    scores = [0.0] * len(pairs)
    idx = [i for i, p in enumerate(pairs) if p[1].strip()]
    if idx:
        import numpy as np
        vecs = embed([pairs[i][0] for i in idx] + [pairs[i][1] for i in idx])
        ref, ans = vecs[:len(idx)], vecs[len(idx):]
        sims = np.sum(ref * ans, axis=1).tolist()  # cosine: vectors are unit length
        for k, i in enumerate(idx):
            scores[i] = marks_from_similarity(sims[k], pairs[i][2])
    return scores


def warm_up():
    try:
        get_model()
        app.logger.info("Model loaded.")
    except Exception as e:  # keep serving; the first scoring request will surface the error
        app.logger.error("Model warm-up failed: %s", e)


if os.environ.get("WARMUP", "1") == "1":
    threading.Thread(target=warm_up, daemon=True).start()


# ---------------------------------------------------------------- routes
@app.get("/")
def index():
    page = os.path.join(FRONTEND_DIR, "index.html")
    if os.path.exists(page):  # local development: serve the pages from here too
        return send_from_directory(FRONTEND_DIR, "index.html")
    return jsonify({"service": "exam-portal-api", "health": "/api/health"})


@app.get("/<path:name>")
def frontend_file(name):
    if name.startswith("api/") or not os.path.isdir(FRONTEND_DIR):
        raise NotFound()
    return send_from_directory(FRONTEND_DIR, name)


@app.get("/api/health")
def health():
    return jsonify({"ok": True, "sheets_configured": bool(APPS_SCRIPT_URL), "model_loaded": _model is not None})


@app.post("/api/teacher/login")
def teacher_login():
    rate_limit()
    d = body()
    email, password = clean_email(d.get("email")), str(d.get("password") or "")
    if not password:
        raise ApiError("Enter your password.")
    call_sheets("teacherLogin", email=email, password=password)
    return jsonify({"status": "ok", "email": email, "token": issue("teacher", email)})


@app.post("/api/student/login")
def student_login():
    rate_limit()
    email = clean_email(body().get("email"))
    call_sheets("studentLogin", email=email)
    return jsonify({"status": "ok", "email": email, "token": issue("student", email)})


@app.post("/api/questions")
def post_questions():
    require("teacher")
    items = body().get("questions")
    if not isinstance(items, list) or not 1 <= len(items) <= MAX_QUESTIONS:
        raise ApiError(f"Add between 1 and {MAX_QUESTIONS} questions.")
    out = []
    for n, q in enumerate(items, 1):
        if not isinstance(q, dict):
            raise ApiError(f"Question {n} is invalid.")
        text, ref = str(q.get("questionText") or "").strip(), str(q.get("desiredAnswer") or "").strip()
        try:
            marks = float(q.get("totalMarks"))
        except (TypeError, ValueError):
            marks = float("nan")
        if not text or not ref:
            raise ApiError(f"Question {n} needs both a question and a reference answer.")
        if len(text) > MAX_TEXT or len(ref) > MAX_TEXT:
            raise ApiError(f"Question {n} is too long (limit {MAX_TEXT} characters).")
        if not math.isfinite(marks) or marks <= 0 or marks > 1000:
            raise ApiError(f"Question {n} needs marks greater than 0.")
        out.append({"questionText": text, "desiredAnswer": ref, "totalMarks": marks})
    call_sheets("postQuestions", teacherEmail=g.email, questions=out)
    return jsonify({"status": "ok", "saved": len(out)})


def _load_questions():
    qs = call_sheets("getQuestions").get("questions") or []
    return [q for q in qs if isinstance(q, dict) and q.get("questionID") is not None]


@app.get("/api/questions")
def get_questions():
    require("student")
    # Reference answers never leave the server.
    return jsonify({"questions": [
        {"questionID": str(q["questionID"]), "questionText": str(q.get("questionText", "")),
         "totalMarks": float(q.get("totalMarks") or 0)} for q in _load_questions()]})


@app.post("/api/submit")
def submit():
    require("student")
    given = body().get("answers")
    if not isinstance(given, list):
        raise ApiError("Send your answers as a list.")
    by_id = {str(a.get("questionID")): str(a.get("answer") or "")[:MAX_ANSWER_CHARS]
             for a in given if isinstance(a, dict)}
    claim_submission(g.jti)  # one submission per login; also blocks concurrent double-submits
    try:
        questions = _load_questions()
        if not questions:
            raise ApiError("There are no questions to answer yet.", 404)
        rows = [(q, by_id.get(str(q["questionID"]), "").strip()) for q in questions]
        scores = score_answers([(str(q.get("desiredAnswer", "")), a, float(q.get("totalMarks") or 0)) for q, a in rows])
        call_sheets("submitResponses", studentEmail=g.email, responses=[
            {"questionID": str(q["questionID"]), "studentAnswer": a, "score": s} for (q, a), s in zip(rows, scores)])
    except Exception:
        release_submission(g.jti)
        raise
    results = [{"questionID": str(q["questionID"]), "questionText": str(q.get("questionText", "")), "answer": a,
                "score": s, "totalMarks": float(q.get("totalMarks") or 0)} for (q, a), s in zip(rows, scores)]
    return jsonify({"status": "ok", "results": results, "total": round(sum(scores), 2),
                    "max": round(sum(r["totalMarks"] for r in results), 2)})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 8000)), debug=False)
