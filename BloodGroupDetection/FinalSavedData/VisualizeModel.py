import tensorflow as tf
from tensorflow.keras import Sequential, layers
from tensorflow.keras.utils import plot_model

# Define constants
img_width = 96
img_height = 103
num_classes = 8
output_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\model_architecture.png'

# Build the model
def build_model():
    model = Sequential([
        layers.Conv2D(32, (3, 3), activation='relu', input_shape=(img_width, img_height, 1)),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(128, (5, 5), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(256, (7, 7), activation='relu'),
        layers.BatchNormalization(),
        layers.MaxPooling2D((2, 2)),
        layers.Flatten(),
        layers.Dense(512, activation='relu'),
        layers.Dropout(0.5),
        layers.Dense(num_classes, activation='softmax')
    ])
    return model

# Create the model
model = build_model()

# Save model architecture visualization
plot_model(
    model,
    to_file=output_path,
    show_shapes=True,  # Show input and output shapes
    show_layer_names=True,  # Display layer names
    dpi=300  # High resolution for presentation
)

print(f"Model architecture saved as PNG at: {output_path}")
