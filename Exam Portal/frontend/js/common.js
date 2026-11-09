import { API_BASE } from "./config.js";

export const $ = (s, r = document) => r.querySelector(s);

/** Build DOM safely: text is always inserted as text, never as HTML. */
export function h(tag, attrs = {}, ...kids) {
  const e = document.createElement(tag);
  for (const [k, v] of Object.entries(attrs)) {
    if (v == null || v === false) continue;
    if (k === "class") e.className = v;
    else if (k === "text") e.textContent = v;
    else if (k.startsWith("on")) e.addEventListener(k.slice(2), v);
    else e.setAttribute(k, v === true ? "" : v);
  }
  for (const kid of kids.flat()) if (kid != null) e.append(kid.nodeType ? kid : document.createTextNode(kid));
  return e;
}

export const session = {
  get() { try { return JSON.parse(sessionStorage.getItem("exam_session") || "null"); } catch { return null; } },
  set(v) { sessionStorage.setItem("exam_session", JSON.stringify(v)); },
  clear() { sessionStorage.removeItem("exam_session"); },
};

export class ApiFail extends Error {
  constructor(message, status) { super(message); this.status = status; }
}

const isLocal = /^(localhost|127\.0\.0\.1|\[::1\])$/.test(location.hostname);

/** Free hosts sleep when idle. Tell people why a request is taking a while instead of leaving them guessing. */
function slowHint() {
  const el = h("div", { class: "toast", role: "status", text: "Waking the server up. This can take up to a minute the first time." });
  const t = setTimeout(() => document.body.append(el), 3000);
  return () => { clearTimeout(t); el.remove(); };
}

/** Fire-and-forget request that starts a sleeping server while the person is still typing. */
export function warmUp() {
  if (API_BASE) fetch(API_BASE + "/api/health").catch(() => {});
}

export async function api(path, { method = "GET", body, auth = true } = {}) {
  if (!API_BASE && !isLocal) {
    throw new ApiFail("This site isn't connected to its server yet. Set API_BASE in js/config.js.", 0);
  }
  const headers = { "Content-Type": "application/json" };
  const s = session.get();
  if (auth && s) headers.Authorization = "Bearer " + s.token;
  let res;
  const done = slowHint();
  try {
    res = await fetch(API_BASE + path, { method, headers, body: body ? JSON.stringify(body) : undefined });
  } catch {
    throw new ApiFail("Can't reach the server. Check your connection and try again.", 0);
  } finally {
    done();
  }
  let data = {};
  try { data = await res.json(); } catch { /* non-JSON error page */ }
  if (res.status === 401 && auth) {
    session.clear();
    const role = s && s.role;
    location.replace((role === "teacher" ? "login_teacher.html" : "login_student.html") + "?expired=1");
  }
  if (!res.ok) throw new ApiFail(data.message || `The server returned an error (${res.status}).`, res.status);
  return data;
}

export function requireRole(role) {
  const s = session.get();
  if (!s || s.role !== role) {
    location.replace(role === "teacher" ? "login_teacher.html" : "login_student.html");
    return null;
  }
  return s;
}

export function mountBar(s) {
  const bar = $("#bar");
  bar.replaceChildren(
    h("a", { class: "brand", href: "index.html", text: "Exam Portal" }),
    s ? h("div", { class: "who" }, s.email,
      h("button", { class: "btn link", type: "button", text: "Log out", onclick: () => { session.clear(); location.href = "index.html"; } }))
      : null);
}

export function say(el, text, kind = "info") {
  el.className = "msg " + kind;
  el.textContent = text;
}

export function busy(btn, on, label) {
  if (on) { btn.dataset.label = btn.textContent; btn.textContent = label; } else if (btn.dataset.label) btn.textContent = btn.dataset.label;
  btn.disabled = on;
}

export const fmt = (n) => (Math.round(n * 100) / 100).toString();
