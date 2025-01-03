import tensorflow as tf
import numpy as np
import os
from tensorflow.keras.preprocessing.image import load_img, img_to_array

# Paths
model_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\savedModel.keras'
image_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\O-.bmp'  # Replace with your test image path

# Classes
blood_group_classes = ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-']

# Image dimensions
img_width, img_height = 96, 103

# Load the trained model
if os.path.exists(model_path):
    model = tf.keras.models.load_model(model_path)
    print("Model loaded successfully.")
else:
    raise FileNotFoundError(f"Model file not found at {model_path}")

# Function to preprocess the image
def preprocess_image(image_path):
    """
    Preprocess a fingerprint image for model prediction.
    :param image_path: Path to the fingerprint image.
    :return: Preprocessed image array.
    """
    # Load the image
    image = load_img(image_path, target_size=(img_width, img_height), color_mode='grayscale')
    image_array = img_to_array(image)  # Convert to numpy array
    image_array = image_array / 255.0  # Normalize pixel values
    image_array = np.expand_dims(image_array, axis=0)  # Add batch dimension
    return image_array

# Preprocess the input image
try:
    input_image = preprocess_image(image_path)
    print("Image preprocessed successfully.")
except Exception as e:
    raise ValueError(f"Error processing image: {e}")

# Predict the blood group
predictions = model.predict(input_image)
predicted_class_index = np.argmax(predictions, axis=1)[0]
predicted_blood_group = blood_group_classes[predicted_class_index]

# Output the prediction
print(f"Predicted Blood Group: {predicted_blood_group}")
