import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import load_model, Sequential
from tensorflow.keras import layers
from tensorflow.keras.callbacks import ModelCheckpoint, EarlyStopping, ReduceLROnPlateau
from tensorflow.keras.optimizers import Adam
import matplotlib.pyplot as plt
import os
import time

# Directory paths
data_dir = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\dataset_blood_group'
model_path = r'C:\Users\USER\Desktop\Stuff\Code\BloodGroupDetection\savedModel.keras'

# Image dimensions and settings
img_width, img_height = 96, 103
batch_size = 32
num_classes = 8  # Blood groups: A+, A-, B+, B-, AB+, AB-, O+, O-
target_epochs = 300

# Initialize data generators
def initialize():
    global train_datagen, train_generator, validation_generator

    train_datagen = ImageDataGenerator(
        rescale=1. / 255,
        rotation_range=20,
        width_shift_range=0.15,
        height_shift_range=0.15,
        shear_range=0.15,
        zoom_range=0.15,
        horizontal_flip=True,
        fill_mode='nearest',
        validation_split=0.2  # Split for training/validation
    )

    train_generator = train_datagen.flow_from_directory(
        data_dir,
        target_size=(img_width, img_height),
        batch_size=batch_size,
        class_mode='categorical',
        subset='training',
        color_mode='grayscale'
    )

    validation_generator = train_datagen.flow_from_directory(
        data_dir,
        target_size=(img_width, img_height),
        batch_size=batch_size,
        class_mode='categorical',
        subset='validation',
        color_mode='grayscale'
    )

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
checkpoint = ModelCheckpoint(model_path, save_best_only=True, monitor='val_accuracy', mode='max')
early_stopping = EarlyStopping(monitor='val_loss', patience=5, restore_best_weights=True)
reduce_lr = ReduceLROnPlateau(monitor='val_loss', factor=0.5, patience=3, min_lr=1e-6)

# Plot and save history including accuracy vs. time
def plot_and_save_history_with_time(history, elapsed_times):
    plt.figure(figsize=(18, 6))

    # Accuracy vs Epoch
    plt.subplot(1, 3, 1)
    plt.plot(history['accuracy'], label='Train Accuracy')
    plt.plot(history['val_accuracy'], label='Validation Accuracy')
    plt.title('Model Accuracy vs. Epoch')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    plt.legend()

    # Loss vs Epoch
    plt.subplot(1, 3, 2)
    plt.plot(history['loss'], label='Train Loss')
    plt.plot(history['val_loss'], label='Validation Loss')
    plt.title('Model Loss vs. Epoch')
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.legend()

    # Accuracy vs Time
    plt.subplot(1, 3, 3)
    plt.plot(elapsed_times, history['accuracy'], label='Train Accuracy')
    plt.plot(elapsed_times, history['val_accuracy'], label='Validation Accuracy')
    plt.title('Model Accuracy vs. Time')
    plt.xlabel('Time (seconds)')
    plt.ylabel('Accuracy')
    plt.legend()

    plt.tight_layout()
    plt.savefig('training_history_with_time.png')
    plt.close()
    print("Training history and accuracy vs. time saved to 'training_history_with_time.png'.")

# Train the model and track time
def train_with_time_tracking():
    global history, elapsed_times
    history = {'accuracy': [], 'val_accuracy': [], 'loss': [], 'val_loss': []}
    elapsed_times = []
    start_time = time.time()
    current_epoch = 0

    while current_epoch < target_epochs:
        epoch_start_time = time.time()

        # Training for one epoch
        epoch_history = model.fit(
            train_generator,
            steps_per_epoch=train_generator.samples // batch_size,
            epochs=current_epoch + 1,
            initial_epoch=current_epoch,
            validation_data=validation_generator,
            validation_steps=validation_generator.samples // batch_size,
            callbacks=[checkpoint, reduce_lr, early_stopping],
            verbose=1
        )

        # Update history and time tracking
        history['accuracy'] += epoch_history.history['accuracy']
        history['val_accuracy'] += epoch_history.history['val_accuracy']
        history['loss'] += epoch_history.history['loss']
        history['val_loss'] += epoch_history.history['val_loss']

        elapsed_times.append(time.time() - start_time)
        current_epoch += 1

        # Plot after each epoch
        plot_and_save_history_with_time(history, elapsed_times)

        elapsed_epoch_time = time.time() - epoch_start_time
        print(f"Epoch {current_epoch}/{target_epochs} completed in {elapsed_epoch_time:.2f} seconds.")

# Train until interrupted
def train_until_interrupted_with_time():
    try:
        train_with_time_tracking()
    except KeyboardInterrupt:
        print("Training interrupted. Saving the final model...")
        model.save(model_path)
        print("Model saved. Exiting training.")

train_until_interrupted_with_time()
