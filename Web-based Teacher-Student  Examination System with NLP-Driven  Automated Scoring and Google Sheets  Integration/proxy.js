// proxy.js
const fetch   = require('node-fetch');
const express = require('express');
const cors    = require('cors');

const app = express();
const PORT = 8080;
const APPS_SCRIPT_URL = 'https://script.google.com/macros/s/AKfycbwHTNgPmbqMixH4_Xs0UPqlIKXfSyanbNoZxS9Y2bQXiaQWnBmmRTa0ez_me6TJtgTD/exec';

app.use(cors());
app.use(express.json());

app.all('/apps-script', async (req, res) => {
  try {
    // 1. Build target URL including all query params
    const queryString = new URLSearchParams(req.query).toString();
    const targetUrl = queryString
      ? `${APPS_SCRIPT_URL}?${queryString}`
      : APPS_SCRIPT_URL;

    // 2. Prepare fetch options, forwarding method & body if not GET
    const opts = { method: req.method };
    if (req.method !== 'GET') {
      opts.headers = { 'Content-Type': 'application/json' };
      opts.body = JSON.stringify(req.body);
    }

    // 3. Call Apps Script, parse JSON (not plain text)
    const apiRes = await fetch(targetUrl, opts);
    const json = await apiRes.json();

    // 4. Relay status, headers, and JSON
    res.status(apiRes.status).json(json);

  } catch (err) {
    console.error('Proxy error:', err);
    res.status(500).json({ status: 'error', message: err.message });
  }
});

app.listen(PORT, () => {
  console.log(`Proxy server on http://localhost:${PORT}/apps-script`);
});
