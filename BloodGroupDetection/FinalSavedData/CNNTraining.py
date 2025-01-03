import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import Sequential, load_model
from tensorflow.keras import layers
from tensorflow.keras.callbacks import ModelCheckpoint, ReduceLROnPlateau
from tensorflow.keras.optimizers import Adam
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import os
from sklearn.metrics import confusion_matrix

# Directory paths
data_dir = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\dataset_blood_group'
model_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\savedModel.keras'

# Image dimensions and settings
img_width, img_height = 96, 103
batch_size = 32
num_classes = 8  # Blood groups: A+, A-, B+, B-, AB+, AB-, O+, O-
target_epochs = 350

# Initialize data generator
def initialize():
    global train_datagen, train_generator, train_dataset, num_samples

    train_datagen = ImageDataGenerator(
        rescale=1. / 255,
        rotation_range=20,
        width_shift_range=0.2,
        height_shift_range=0.2,
        shear_range=0.2,
        zoom_range=0.2,
        horizontal_flip=True,
        fill_mode='nearest'
    )
    
    # Load the dataset
    train_generator = train_datagen.flow_from_directory(
        data_dir,
        target_size=(img_width, img_height),
        batch_size=batch_size,
        class_mode='categorical',
        shuffle=True,
        color_mode='grayscale'
    )
    
    # Calculate the number of samples
    num_samples = train_generator.samples

    # Create a tf.data.Dataset for training
    train_dataset = tf.data.Dataset.from_generator(
        lambda: train_generator,
        output_signature=(
            tf.TensorSpec(shape=(None, img_width, img_height, 1), dtype=tf.float32),
            tf.TensorSpec(shape=(None, num_classes), dtype=tf.float32)
        )
    ).repeat()

initialize()

# Build or load the model
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

if os.path.exists(model_path):
    model = load_model(model_path)
    print("Loaded model from checkpoint.")
else:
    model = build_model()
    print("Initialized a new model.")

model.compile(optimizer=Adam(learning_rate=0.0001),
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Callbacks
checkpoint = ModelCheckpoint(model_path, save_best_only=True, monitor='accuracy', mode='max')
reduce_lr = ReduceLROnPlateau(monitor='loss', factor=0.5, patience=5, min_lr=1e-6)

# Save confusion matrix as an image
def save_confusion_matrix(cm, classes, save_path_cm='confusion_matrix.png'):
    plt.figure(figsize=(10, 8))
    sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', xticklabels=classes, yticklabels=classes)
    plt.title('Confusion Matrix')
    plt.xlabel('Predicted')
    plt.ylabel('Actual')
    plt.tight_layout()
    plt.savefig(save_path_cm)
    plt.close()
    print(f"Confusion Matrix saved as {save_path_cm}.")

# Train until interrupted
def train_until_interrupted():
    try:
        model.fit(
            train_dataset,
            steps_per_epoch=num_samples // batch_size,
            epochs=target_epochs,
            callbacks=[checkpoint, reduce_lr],
            verbose=1
        )
    except KeyboardInterrupt:
        print("Training interrupted. Saving the final model...")
        model.save(model_path)
        print("Model saved. Exiting training.")

train_until_interrupted()

# Evaluate model and generate confusion matrix
def evaluate_and_save_metrics():
    train_generator.reset()  # Reset the generator to start from the beginning
    y_pred = model.predict(train_generator, verbose=1)
    y_true = train_generator.classes  # True labels from the generator
    y_pred_classes = np.argmax(y_pred, axis=1)  # Predicted class labels

    # Ensure the number of predictions matches the true labels
    print(f"True labels: {len(y_true)}, Predicted labels: {len(y_pred_classes)}")

    # Confusion Matrix
    cm = confusion_matrix(y_true, y_pred_classes)
    save_confusion_matrix(cm, list(train_generator.class_indices.keys()))

evaluate_and_save_metrics()
