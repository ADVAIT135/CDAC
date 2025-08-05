// js/main.js
import { APPS_PROXY_URL } from './app-config.js';

/**
 * Universal JSON‐POST helper
 */
async function callApi(endpoint, body = {}) {
  const res = await fetch(`${APPS_PROXY_URL}?endpoint=${endpoint}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ endpoint, ...body })
  });

  if (!res.ok) {
    throw new Error(`API proxy returned ${res.status}`);
  }
  const json = await res.json();
  if (json.status === 'error') {
    throw new Error(json.message || 'API returned error');
  }
  return json;
}

function showLoginError(err) {
  console.log('showLoginError called:', err.message);
  const msg = (err.message || '').toLowerCase();
  if (msg.includes('invalid credentials')) {
    alert('Invalid email or password.');
  } else {
    alert(err.message || 'Login failed. Please try again.');
  }
}

/** 1. Teacher Login */
export async function teacherLogin(e) {
  e.preventDefault();
  const email    = e.target.email.value;
  const password = e.target.password.value;

  try {
    console.log('teacherLogin: calling callApi');
    await callApi('teacherLogin', { email, password });
    sessionStorage.setItem('teacher', email);
    window.location.href = 'teacher_dashboard.html';
  } catch (err) {
    console.log('teacherLogin: caught error:', err.message);
    showLoginError(err);
    console.error(err);
  }
}

/** 2. Student Login */
export async function studentLogin(e) {
  e.preventDefault();
  const email = e.target.email.value;
  await callApi('studentLogin', { email });
  sessionStorage.setItem('student', email);
  window.location.href = 'student_exam.html';
}


/** 3. Post Questions (Teacher) */
export async function postQuestions(teacherEmail, questions) {
  await callApi('postQuestions', { teacherEmail, questions });
}


/** 4. Fetch Questions (Student) */
export async function getQuestions() {
  const { questions } = await callApi('getQuestions');
  return questions;
}


/** 5. Submit Student Responses */
export async function submitResponses(studentEmail, responses) {
  await callApi('submitResponses', { studentEmail, responses });
}


/** 6. Initialize Teacher Dashboard */
export function initTeacherDash() {
  const form            = document.getElementById('qForm');
  const numQInput       = form.elements['numQ'];
  const blocksContainer = document.getElementById('qBlocks');

  function renderBlocks(n) {
    blocksContainer.innerHTML = '';
    for (let i = 0; i < n; i++) {
      const div = document.createElement('div');
      div.className = 'q-block';
      div.innerHTML = `
        <label>Question ${i + 1} Text:</label>
        <input name="qText${i}" type="text" required />

        <label>Desired Answer:</label>
        <input name="qAnswer${i}" type="text" required />

        <label>Total Marks:</label>
        <input name="qMarks${i}" type="number" min="0" required />
      `;
      blocksContainer.appendChild(div);
    }
  }

  numQInput.addEventListener('input', () => {
    const n = parseInt(numQInput.value, 10);
    if (Number.isInteger(n) && n > 0) {
      renderBlocks(n);
    } else {
      blocksContainer.innerHTML = '';
    }
  });

  const initialN = parseInt(numQInput.value, 10);
  if (Number.isInteger(initialN) && initialN > 0) {
    renderBlocks(initialN);
  }

  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
      const n = parseInt(numQInput.value, 10);
      if (!n || n < 1) throw new Error('Enter a valid number of questions.');

      const questions = [];
      for (let i = 0; i < n; i++) {
        const questionText  = form.elements[`qText${i}`].value.trim();
        const desiredAnswer = form.elements[`qAnswer${i}`].value.trim();
        const totalMarks    = parseFloat(form.elements[`qMarks${i}`].value);
        if (!questionText || !desiredAnswer || isNaN(totalMarks)) {
          throw new Error(`Incomplete input for Question ${i + 1}`);
        }
        questions.push({ questionText, desiredAnswer, totalMarks });
      }

      const teacherEmail = sessionStorage.getItem('teacher');
      await postQuestions(teacherEmail, questions);
      alert('Questions saved successfully!');
      form.reset();
      blocksContainer.innerHTML = '';
    } catch (err) {
      console.error(err);
      alert(err.message);
    }
  });
}


/** 7. Initialize Student Exam */
export async function initStudentExam() {
  const studentEmail = sessionStorage.getItem('student');
  if (!studentEmail) {
    alert('Please log in first.');
    window.location.href = 'student_login.html';
    return;
  }

  // Fetch questions
  const questions = await getQuestions();
  const form      = document.getElementById('examForm');
  const submitBlk = form.querySelector('.submit-block');
  const results   = document.getElementById('results');

  // Inject question inputs
  questions.forEach((q, i) => {
    const wrapper = document.createElement('div');
    wrapper.className = 'q-block';
    wrapper.innerHTML = `
      <label for="q${i}">Q${i + 1}: ${q.questionText}</label>
      <input id="q${i}" name="q${i}" required />
    `;
    form.insertBefore(wrapper, submitBlk);
  });

  // Handle answer submission
  form.addEventListener('submit', async (e) => {
    e.preventDefault();
    try {
      results.innerHTML = ''; // clear old results

      // 1) Prepare items for scoring
      const data  = new FormData(form);
      const items = questions.map((q, i) => ({
        questionID:    q.questionID,
        questionText:  q.questionText,
        desiredAnswer: q.desiredAnswer,
        studentAnswer: data.get(`q${i}`).trim(),
        totalMarks:    parseFloat(q.totalMarks)
      }));

      // 2) Call local Flask scoring API
      const scoreRes = await fetch('http://localhost:5001/score', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ items })
      });
      if (!scoreRes.ok) throw new Error('Scoring service error');
      const { scores } = await scoreRes.json();

      // 3) Store to Sheets
      const rows = items.map((itm, idx) => ({
        questionID:    itm.questionID,
        studentAnswer: itm.studentAnswer,
        score:         scores[idx]
      }));
      await submitResponses(studentEmail, rows);

      // 4) Render results table
      let totalScored = 0, totalMax = 0;
      const tbl = document.createElement('table');
      tbl.innerHTML = `
        <thead>
          <tr>
            <th>Question</th>
            <th>Your Answer</th>
            <th>Score</th>
            <th>Max Marks</th>
          </tr>
        </thead>
        <tbody>
          ${items.map((itm, idx) => {
            const sc = scores[idx];
            totalScored += sc;
            totalMax    += itm.totalMarks;
            return `
              <tr>
                <td>${itm.questionText}</td>
                <td>${itm.studentAnswer}</td>
                <td>${sc.toFixed(2)}</td>
                <td>${itm.totalMarks}</td>
              </tr>
            `;
          }).join('')}
        </tbody>
        <tfoot>
          <tr>
            <th colspan="2">Total</th>
            <th>${totalScored.toFixed(2)}</th>
            <th>${totalMax}</th>
          </tr>
        </tfoot>
      `;
      results.appendChild(tbl);

    } catch (err) {
      console.error(err);
      alert(err.message || 'Error during submission/scoring.');
    }
  });
}


