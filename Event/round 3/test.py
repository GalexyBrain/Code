import random

def generate_random_face():
    # Define facial components
    eyes = ["o", "O", "-", "^", "x"]
    noses = ["<", ">", "^", "-"]
    mouths = ["VVV", "WWW", "o", "O", "uuu"]

    # Randomly choose components
    eye_left = random.choice(eyes)
    eye_right = random.choice(eyes)
    nose = random.choice(noses)
    mouth = random.choice(mouths)

    # Build the face
    face = f"  {eye_left}   {eye_right}\n   {nose}\n   {mouth}"
    return face

# Generate and print the face
print(generate_random_face())
