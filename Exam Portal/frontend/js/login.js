import { $, api, session, say, busy, mountBar, warmUp } from "./common.js";

const role = document.body.dataset.role;
const home = role === "teacher" ? "teacher_dashboard.html" : "student_exam.html";
mountBar(null);
warmUp();

const existing = session.get();
if (existing && existing.role === role) location.replace(home);

const msg = $("#msg");
if (new URLSearchParams(location.search).get("expired")) say(msg, "Your session expired. Log in again to continue.", "info");

$("#form").addEventListener("submit", async (e) => {
  e.preventDefault();
  const btn = $("button[type=submit]");
  const body = { email: $("#email").value.trim() };
  if (role === "teacher") body.password = $("#password").value;
  busy(btn, true, "Logging in…");
  try {
    const r = await api(`/api/${role}/login`, { method: "POST", body, auth: false });
    session.set({ role, email: r.email, token: r.token });
    location.href = home;
  } catch (err) {
    say(msg, err.status === 401 ? "Those details didn't match. Check your email" + (role === "teacher" ? " and password." : ".") : err.message, "error");
    busy(btn, false);
  }
});
