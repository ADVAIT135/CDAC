from flask import Flask, request, jsonify
from flask_cors import CORS           
from sentence_transformers import SentenceTransformer, util

app = Flask(__name__)
CORS(app)                              


model = SentenceTransformer('all-MiniLM-L6-v2')

@app.route('/', methods=['GET'])
def home():
    return 'Score API is running. Use POST /score.'

@app.route('/score', methods=['POST'])
def score():
    data = request.get_json(force=True)
    items = data.get('items', [])
    if not isinstance(items, list):
        return jsonify({'status':'error','message':'items must be a list'}), 400

    desireds = [i['desiredAnswer'] for i in items]
    studs    = [i['studentAnswer'] for i in items]
    emb_des  = model.encode(desireds, convert_to_tensor=True)
    emb_stu  = model.encode(studs,    convert_to_tensor=True)
    sims     = util.cos_sim(emb_des, emb_stu).diagonal().tolist()

    scores = []
    for sim, itm in zip(sims, items):
        normalized = (sim + 1) / 2
        marks = round(normalized * float(itm['totalMarks']), 2)
        scores.append(marks)

    return jsonify({'scores':scores})

if __name__ == '__main__':
    app.run(port=5001, debug=True)
