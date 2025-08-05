// Change this if your proxy lives elsewhere
export const APPS_PROXY_URL  = 'http://localhost:8080/apps-script';
// Keep your scoring API behind the same proxy if you like:
export const SCORING_API_URL = `${APPS_PROXY_URL}?endpoint=scoreAnswers`;
