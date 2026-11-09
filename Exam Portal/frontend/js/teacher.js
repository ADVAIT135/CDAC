import { $, h, api, requireRole, mountBar, say, busy, fmt } from "./common.js";

const s = requireRole("teacher");
if (s) {
  mountBar(s);
  const list = $("#list"), msg = $("#msg"), tally = $("#tally"), form = $("#form");
  let uid = 0;

  function card() {
    const id = ++uid;
    const c = h("section", { class: "card q" },
      h("div", { class: "qhead" }, h("span", { class: "qno" }),
        h("button", { class: "btn link rm", type: "button", text: "Remove", onclick: () => { c.remove(); update(); } })),
      h("div", { class: "field" },
        h("label", { for: "t" + id }, "Question"),
        h("textarea", { id: "t" + id, class: "qt", required: true, maxlength: 2000 })),
      h("div", { class: "field" },
        h("label", { for: "a" + id }, "Reference answer", h("span", { class: "hint", text: "Write the full answer you would accept. Student answers are marked by how closely their meaning matches it." })),
        h("textarea", { id: "a" + id, class: "qa", required: true, maxlength: 2000 })),
      h("div", { class: "field", style: "max-width:180px;margin:0" },
        h("label", { for: "m" + id, text: "Marks" }),
        h("input", { id: "m" + id, class: "qm", type: "number", min: "0.5", step: "0.5", required: true, value: "5", oninput: update })));
    return c;
  }

  function update() {
    const cards = [...list.children];
    cards.forEach((c, i) => {
      $(".qno", c).textContent = "Question " + (i + 1);
      $(".rm", c).hidden = cards.length === 1;
    });
    const total = cards.reduce((a, c) => a + (parseFloat($(".qm", c).value) || 0), 0);
    tally.textContent = `${cards.length} ${cards.length === 1 ? "question" : "questions"} · ${fmt(total)} marks`;
  }

  function reset() { list.replaceChildren(card()); update(); }
  $("#add").addEventListener("click", () => { const c = card(); list.append(c); update(); $(".qt", c).focus(); });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const questions = [...list.children].map((c) => ({
      questionText: $(".qt", c).value.trim(), desiredAnswer: $(".qa", c).value.trim(), totalMarks: parseFloat($(".qm", c).value) }));
    const btn = $("#save");
    busy(btn, true, "Saving…");
    try {
      const r = await api("/api/questions", { method: "POST", body: { questions } });
      say(msg, `Saved ${r.saved} ${r.saved === 1 ? "question" : "questions"}. Students can see them now.`, "ok");
      reset();
    } catch (err) { say(msg, err.message, "error"); }
    busy(btn, false);
    if (msg.scrollIntoView) msg.scrollIntoView({ behavior: "smooth", block: "nearest" });
  });
  reset();
}
