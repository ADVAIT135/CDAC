# Exam Portal (free-tier edition)

Teachers write questions with reference answers. Students answer in their own words and are scored
instantly by comparing meaning with the MiniLM sentence model. Data lives in Google Sheets.

```
Browser  ->  pages (Hugging Face Static Space, free)  ->  API (Render free web service)  ->  Apps Script / Google Sheets
```

Reference answers and scoring stay on the API. The browser never sees reference answers and can't send its own
scores. Hugging Face's free accounts can only create Static Spaces, which is why the API runs elsewhere.

```
backend/      Flask API, ONNX scoring, Dockerfile, tests
render.yaml   Render blueprint (keep it at the repo root)
frontend/     the pages (upload these to a Static Space), plus netlify.toml if you prefer Netlify
```

## Setup (about 20 minutes)

### 1. Apps Script (new deployment)
Deploy a **new** web app version of your script and use its new URL. The old URL is in your public Git history.
To restrict it to your API, set a Script Property `API_SECRET` and add this at the top of `doPost`:

```js
function doPost(e) {
  var data = JSON.parse(e.postData.contents);
  var secret = PropertiesService.getScriptProperties().getProperty('API_SECRET');

  if (secret && data.secret !== secret) {
    return ContentService.createTextOutput(
      JSON.stringify({status: 'error', message: 'Unauthorized'})
    ).setMimeType(ContentService.MimeType.JSON);
  }

  // Keep your existing endpoint handling here...
}
```
Also make sure `studentLogin` rejects unregistered emails and `teacherLogin` checks the password.

### 2. API on Render (free)
1. Push this folder to a GitHub repo.
2. Render: **New > Blueprint**, choose the repo (it reads `render.yaml`). Or **New > Web Service**, runtime
   **Docker**, root directory `backend`, instance type **Free**.
3. Set environment variables:
   - `APPS_SCRIPT_URL`: the new Apps Script URL
   - `APPS_SCRIPT_SECRET`: the same value as `API_SECRET` (optional but recommended)
   - `ALLOWED_ORIGINS`: your pages' address, filled in at step 3 (no trailing slash)
   - `SECRET_KEY`: generated for you by the blueprint
4. When it's live, open `https://YOUR-SERVICE.onrender.com/api/health`. You should see `"ok": true`.

### 3. Pages on a Hugging Face Static Space (free)
1. Hugging Face: **New Space**, SDK **Static**.
2. Upload the contents of `frontend/` (including its `README.md`).
3. Edit `js/config.js`: set `API_BASE` to your Render address, for example `https://exam-portal-api.onrender.com`.
4. Open the Space's **Embed this Space** dialog, copy the **direct URL** (the `…hf.space` address), and put that
   origin into `ALLOWED_ORIGINS` on Render. Share that direct URL with students.

Prefer Netlify? Drop `frontend/` on Netlify instead (`netlify.toml` is included) and use your Netlify address
in `ALLOWED_ORIGINS`.

## What to expect on free hosting
- **Sleep:** Render's free service spins down after 15 minutes idle and takes about a minute to wake. The pages
  start waking it as soon as someone opens the login page, and show a "waking the server" note if a request is slow.
  Open the portal a couple of minutes before an exam starts.
- **Slow CPU:** the free instance has 0.1 CPU, so scoring a whole exam can take a few seconds.
- **Hours:** Render gives 750 free instance hours a month, which covers one service running most of the month.
- **Not for high-stakes use.** Render's own docs say free services shouldn't be used for production. For anything
  that counts toward a grade, test it under realistic load first, or move the API to a $7/month always-on instance.

## Run locally
```bash
cd backend
pip install -r requirements.txt
export APPS_SCRIPT_URL=... SECRET_KEY=any-long-string
python app.py          # serves the API and the pages at http://localhost:8000
```
Leave `API_BASE` empty for local runs. Tests: `pip install pytest && python -m pytest backend/tests`.

## Scoring
Marks follow a straight ramp: similarity at or below `SIM_LOW` (0.25) earns 0 and at or above `SIM_HIGH` (0.80)
earns full marks. Blank answers score 0. Tune both on a few real answers from your subject. Similarity can't tell
a sentence from its negation, so treat scores as a first pass and review borderline answers. The model uses the
same MiniLM weights as before, run through ONNX instead of PyTorch so it fits in free-tier memory. Very long answers
are cut off after a few hundred words.

## Settings
| Variable | Default | Purpose |
|---|---|---|
| `APPS_SCRIPT_URL` | required | Google Apps Script endpoint |
| `SECRET_KEY` | random per start | Signs sessions; set it so logins survive restarts |
| `ALLOWED_ORIGINS` | empty | Page origin(s) allowed to call the API, comma-separated |
| `APPS_SCRIPT_SECRET` | empty | Sent to Apps Script with every call |
| `TOKEN_TTL_SECONDS` | 14400 | Session length |
| `SIM_LOW` / `SIM_HIGH` | 0.25 / 0.80 | Scoring ramp |

Each login can submit once, tracked in memory, so a server restart clears it. Block repeat submissions in your
sheet if retakes matter.
