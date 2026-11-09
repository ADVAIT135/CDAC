import { $, h, api, requireRole, mountBar, say, busy, fmt } from "./common.js";

const s = requireRole("student");
if (s) {
  mountBar(s);
  const msg = $("#msg"), form = $("#form"), box = $("#questions"), summary = $("#summary"), dlg = $("#confirm");
  let questions = [];

  (async () => {
    box.replaceChildren(h("div", { class: "skeleton" }));
    try {
      questions = (await api("/api/questions")).questions;
    } catch (err) { box.replaceChildren(); say(msg, err.message, "error"); return; }
    box.replaceChildren();
    if (!questions.length) { say(msg, "No questions have been published yet. Check back later.", "info"); return; }
    const total = questions.reduce((a, q) => a + q.totalMarks, 0);
    summary.textContent = `${questions.length} ${questions.length === 1 ? "question" : "questions"} · ${fmt(total)} marks`;
    questions.forEach((q, i) => {
      const area = h("textarea", { id: "q" + i, maxlength: 4000, "aria-describedby": "c" + i });
      const count = h("div", { class: "count", id: "c" + i, text: "0 / 4000" });
      area.addEventListener("input", () => { count.textContent = `${area.value.length} / 4000`; });
      box.append(h("section", { class: "card" },
        h("div", { class: "qhead" }, h("span", { class: "qno", text: "Question " + (i + 1) }), h("span", { class: "pill", text: fmt(q.totalMarks) + (q.totalMarks === 1 ? " mark" : " marks") })),
        h("label", { for: "q" + i, class: "qtext", text: q.questionText }), area, count));
    });
    $("#actions").hidden = false;
  })();

  form.addEventListener("submit", (e) => {
    e.preventDefault();
    const blank = questions.filter((_, i) => !$("#q" + i).value.trim()).length;
    $("#confirm-text").textContent = (blank ? `${blank} ${blank === 1 ? "question is" : "questions are"} blank and will score 0. ` : "") +
      "You can't change your answers after submitting.";
    dlg.showModal();
  });

  dlg.addEventListener("close", async () => {
    if (dlg.returnValue !== "yes") return;
    const btn = $("#submit"), fields = [...form.querySelectorAll("textarea")];
    fields.forEach((f) => (f.disabled = true));
    busy(btn, true, "Scoring your answers…");
    msg.className = "msg"; msg.textContent = "";
    try {
      const r = await api("/api/submit", { method: "POST",
        body: { answers: questions.map((q, i) => ({ questionID: q.questionID, answer: $("#q" + i).value })) } });
      show(r);
    } catch (err) {
      fields.forEach((f) => (f.disabled = false));
      busy(btn, false);
      say(msg, err.message, "error");
    }
  });

  function show(r) {
    const pct = r.max ? Math.round((r.total / r.max) * 100) : 0;
    form.hidden = true; summary.hidden = true;
    $("#title").textContent = "Your results";
    $("#results").replaceChildren(
      h("div", { class: "card" },
        h("div", { class: "summary" }, h("span", { class: "big", text: `${fmt(r.total)} / ${fmt(r.max)}` }), h("span", { text: pct + "%" })),
        h("p", { class: "muted", text: "Each score reflects how closely your answer's meaning matches the teacher's reference answer." }),
        h("div", { class: "tw" }, h("table", {},
          h("thead", {}, h("tr", {}, h("th", { text: "Question" }), h("th", { text: "Your answer" }), h("th", { class: "n", text: "Score" }), h("th", { class: "n", text: "Out of" }))),
          h("tbody", {}, r.results.map((x) => h("tr", {}, h("td", { text: x.questionText }), h("td", { text: x.answer || "(blank)" }),
            h("td", { class: "n", text: fmt(x.score) }), h("td", { class: "n", text: fmt(x.totalMarks) })))),
          h("tfoot", {}, h("tr", {}, h("th", { colspan: "2", text: "Total" }), h("th", { class: "n", text: fmt(r.total) }), h("th", { class: "n", text: fmt(r.max) })))))));
    window.scrollTo({ top: 0 });
  }
}
