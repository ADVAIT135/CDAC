import streamlit as st
import spacy
import joblib
from nltk.corpus import stopwords

# Load artifacts
vectorizer = joblib.load('vectorizer.pkl')
clf = joblib.load('classifier.pkl')

st.title('Disneyland Review Sentiment Classifier')
user_input = st.text_area('Enter a review:')

def preprocess(text):
    doc = spacy.load('en_core_web_sm')(text)
    stop_words = set(stopwords.words('english'))
    tokens = [token.lemma_.lower() for token in doc if token.is_alpha and token.text.lower() not in stop_words]
    return ' '.join(tokens)

if st.button('Classify'):
    clean = preprocess(user_input)
    vect = vectorizer.transform([clean])
    pred = clf.predict(vect)[0]
    st.write(f'Sentiment: {pred}')
