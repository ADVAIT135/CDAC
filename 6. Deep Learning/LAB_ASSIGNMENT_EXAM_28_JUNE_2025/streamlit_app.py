import streamlit as st
import cv2
import numpy as np
from tensorflow.keras.models import load_model
from PIL import Image

# Load model
model = load_model(r"C:\Users\Gurunath Chavan\Desktop\CDAC\ASSIGNMENTS\DEEP LEARNING\LAB_ASSESSMENT\data\mask_detector_model.keras")
class_names = ['with_mask', 'without_mask', 'mask_worn_incorrect']
IMG_SIZE = 224

st.title("Mask Detection Streamlit App")

# Upload images
uploaded_files = st.file_uploader(
    "Upload images for prediction", type=["jpg", "jpeg", "png"], accept_multiple_files=True
)

if uploaded_files:
    for uploaded_file in uploaded_files:
        # Read image
        image = Image.open(uploaded_file).convert("RGB")
        img_resized = image.resize((IMG_SIZE, IMG_SIZE))
        img_array = np.array(img_resized)
        input_img = np.expand_dims(img_array, axis=0)

        # Predict
        _, pred_class = model.predict(input_img)
        pred_class_id = np.argmax(pred_class[0])
        label = class_names[pred_class_id]

        # Show heading and image
        st.header(label)
        st.image(image, use_column_width=True)