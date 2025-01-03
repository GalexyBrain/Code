import os
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import load_model
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras import Model
import matplotlib.pyplot as plt
import pandas as pd

# Directories
data_dir = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\dataset_blood_group'
model_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\savedModel.keras'
feature_dir = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\Feature'

# Image dimensions and batch size
IMG_WIDTH = 96
IMG_HEIGHT = 103
BATCH_SIZE = 32
NUM_IMAGES_PER_CLASS = 50  # Limit to 50 images per class

# Load the model
print("Loading model...")
base_model = load_model(model_path, compile=False)

# Build the model if it has not been built
base_model.build(input_shape=(None, IMG_HEIGHT, IMG_WIDTH, 3))  # Build with input shape

# Inspect the model layers
print("Model loaded and redefined with input shape.")
print("Model layers:")
for i, layer in enumerate(base_model.layers):
    if hasattr(layer, "output_shape"):
        print(f"Layer {i}: {layer.name} - {layer.output_shape}")
    else:
        print(f"Layer {i}: {layer.name} - No output shape")

# Choose a layer name from the model, for example: 'conv2d_2'
layer_name = "conv2d_2"  # Update this to the correct layer name from the printed list

# Get the layer by name
layer = base_model.get_layer(name=layer_name)

# Extract the output of that layer
outputs = layer.output

# Create a feature extraction model that will output the chosen layer's output
feature_model = Model(inputs=base_model.inputs, outputs=outputs)

# Data generator for feature extraction
datagen = ImageDataGenerator(rescale=1.0 / 255)

generator = datagen.flow_from_directory(
    data_dir,
    target_size=(IMG_HEIGHT, IMG_WIDTH),
    batch_size=1,
    class_mode='categorical',
    shuffle=False,
    color_mode='rgb'
)

# Ensure the feature directory exists
os.makedirs(feature_dir, exist_ok=True)

# Extract and save features
class_indices = generator.class_indices
class_names = list(class_indices.keys())
documented_features = []

# Count the number of images processed per class
class_counts = {class_name: 0 for class_name in class_names}

print("Starting feature extraction...")
for i, (img, label) in enumerate(generator):
    if all(count >= NUM_IMAGES_PER_CLASS for count in class_counts.values()):
        break  # Stop processing if 50 images from each class have been processed

    # Get class label
    class_index = np.argmax(label[0])
    class_name = class_names[class_index]

    # Skip if we have already processed 50 images for this class
    if class_counts[class_name] >= NUM_IMAGES_PER_CLASS:
        continue

    # Extract features
    features = feature_model.predict(img, verbose=0)
    features = np.squeeze(features)

    # Average across the channels (128 channels) to get a single 2D image
    features_avg = np.mean(features, axis=-1)  # Averaging over the channels

    # Save feature as image
    feature_file = os.path.join(feature_dir, f"feature_{i}_{class_name}.png")
    plt.figure()
    plt.imshow(features_avg, cmap='viridis')  # Visualize features with a colormap
    plt.axis('off')
    plt.savefig(feature_file)
    plt.close()

    # Document feature details
    documented_features.append({
        "Feature Image Path": feature_file,
        "Blood Group": class_name
    })

    # Increment the count for this class
    class_counts[class_name] += 1

    print(f"Processed {i + 1}/{generator.samples} images.")

# Save documented features as a table-like structure
output_csv_path = os.path.join(feature_dir, "documented_features.csv")
feature_df = pd.DataFrame(documented_features)
feature_df.to_csv(output_csv_path, index=False)
print(f"Features documented and saved to {output_csv_path}.")
