FROM python:3.11-slim

ENV PYTHONUNBUFFERED=1 PIP_NO_CACHE_DIR=1 MODEL_CACHE=/app/model_cache
WORKDIR /app

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .
# Download the ONNX model at build time so a waking server doesn't also have to download it.
RUN python -c "from fastembed import TextEmbedding; TextEmbedding('sentence-transformers/all-MiniLM-L6-v2', cache_dir='/app/model_cache')"

# Free hosts give very little memory: one worker, a few threads. Render supplies $PORT.
CMD gunicorn -b 0.0.0.0:${PORT:-8000} --workers 1 --threads 4 --timeout 120 app:app
