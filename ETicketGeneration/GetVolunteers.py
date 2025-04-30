import csv
import os
from PIL import Image, ImageDraw, ImageFont
from dotenv import load_dotenv

load_dotenv()

# Load the base certificate image and prepare output folder
base_image_path = r'VolCert.png'
output_folder = 'Vol_Cert'
os.makedirs(output_folder, exist_ok=True)

# Path to your TTF font
font_path = r'./poppins.bold.ttf'

# Predefined center-bottom positions for fields
positions = {
    'NAME': (720, 845),  # (center_x, bottom_y)
    'USN' : (1300, 845)
}

# Helper: Draw text centered at a bottom-center anchor with dynamic sizing
def draw_text_center(draw, text, bottom_center_position, max_width, max_font_size=50, min_font_size=10):
    font_size = max_font_size
    while font_size >= min_font_size:
        font = ImageFont.truetype(font_path, font_size)
        # Measure text bounding box
        bbox = draw.textbbox((0, 0), text, font=font)
        text_width = bbox[2] - bbox[0]
        text_height = bbox[3] - bbox[1]
        if text_width <= max_width:
            # Compute top-left from bottom-center anchor
            x = bottom_center_position[0] - text_width / 2
            y = bottom_center_position[1]
            draw.text((x, y), text, font=font, fill='black')
            return
        font_size -= 1
    # Fallback to smallest font size
    font = ImageFont.truetype(font_path, min_font_size)
    bbox = draw.textbbox((0, 0), text, font=font)
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]
    x = bottom_center_position[0] - text_width / 2
    y = bottom_center_position[1] - text_height
    draw.text((x, y), text, font=font, fill='black')

# Read CSV and generate certificates
with open('Vol.csv', newline='', encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)
    i = 1
    for row in reader:
        img = Image.open(base_image_path).convert('RGBA')
        draw = ImageDraw.Draw(img)
        draw_text_center(
            draw,
            row['Name'],
            positions['NAME'],
            max_width=600,
            max_font_size=50,
            min_font_size=12
        )
        draw_text_center(
            draw,
            row['USN'],
            positions['USN'],
            max_width=1000,
            max_font_size=50,
            min_font_size=12
        )
        output_path = os.path.join(output_folder, f"{i}.png")
        i += 1
        img.save(output_path)
        print(f"🎉 Certificate for {row['Name']} saved to {output_path}")
