import csv
import os
import smtplib
from email.message import EmailMessage
from PIL import Image, ImageDraw, ImageFont
from dotenv import load_dotenv

load_dotenv()


# Load the base Ticket image
base_image_path = 'Certificate.png'
output_folder = 'generated_images'
os.makedirs(output_folder, exist_ok=True)

# Define font paths (adjust to actual paths if needed)
font_path = r'./Poppins-Regular.ttf'  # Change this if using Windows or custom fonts

# Predefined text positions (manually calculated for the given certificate)
positions = {
    'NO': (1450, 70),
    'NAME': (1370, 190),
    'PHONE': (1370, 300),
    'EMAIL': (1370, 420),
}

# Helper function to draw text with dynamic font size using bottom-left coordinates.
def draw_text(draw, text, bottom_left_position, max_width, max_font_size=30, min_font_size=12):
    font_size = max_font_size
    while font_size >= min_font_size:
        font = ImageFont.truetype(font_path, font_size)
        # Get the text dimensions using an origin of (0, 0)
        bbox = draw.textbbox((0, 0), text, font=font)
        text_width = bbox[2] - bbox[0]
        text_height = bbox[3] - bbox[1]
        if text_width <= max_width:
            # For bottom-left anchoring, subtract text height from the y coordinate
            draw_position = (bottom_left_position[0], bottom_left_position[1] - text_height)
            draw.text(draw_position, text, font=font, fill='white')
            return
        font_size -= 1
    # Draw anyway if too big with the minimum font size
    font = ImageFont.truetype(font_path, min_font_size)
    bbox = draw.textbbox((0, 0), text, font=font)
    text_height = bbox[3] - bbox[1]
    draw_position = (bottom_left_position[0], bottom_left_position[1] - text_height)
    draw.text(draw_position, text, font=font, fill='white')

# Function to send an email with the certificate attached.
def send_email(sender, recipient, subject, body, attachment_path):
    msg = EmailMessage()
    msg['Subject'] = subject
    msg['From'] = sender
    msg['To'] = recipient
    msg.set_content(body)
    
    with open(attachment_path, 'rb') as f:
        file_data = f.read()
        file_name = os.path.basename(attachment_path)
    
    # Attach image with MIME type for PNG images.
    msg.add_attachment(file_data, maintype='image', subtype='png', filename=file_name)
    
    # Configure your SMTP settings here:
    smtp_server = 'smtp.gmail.com'  # e.g., 'smtp.gmail.com' for Gmail
    smtp_port = 587
    smtp_username = sender
    smtp_password = os.getenv('SMTP_PASSWORD')
    
    with smtplib.SMTP(smtp_server, smtp_port) as server:
        server.starttls()
        server.login(smtp_username, smtp_password)
        server.send_message(msg)
        print(f"Email sent to {recipient} with attachment {file_name}")


# Read CSV and process each entry
with open('data1.csv', newline='') as csvfile:
    reader = csv.DictReader(csvfile)

    with open('Last.txt', 'r') as textFile:
        last = int(textFile.read())

    newLast = last
    for row in reader:

        if int(row['ID']) <= last:
            continue
        # Open certificate and get drawing context
        img = Image.open(base_image_path).convert('RGBA')
        draw = ImageDraw.Draw(img)

        # Draw the text fields at their positions
        draw_text(draw, row['ID'], positions['NO'], max_width=200)
        draw_text(draw, row['NAME'], positions['NAME'], max_width=300)
        draw_text(draw, row['PHONE'], positions['PHONE'], max_width=300)
        draw_text(draw, row['EMAIL'], positions['EMAIL'], max_width=300)

        # Save image using 'ID' as filename
        output_path = os.path.join(output_folder, f"{row['ID']}.png")
        img.save(output_path)
        print(f"Ticket for {row['ID']} saved to {output_path}")

        # Email settings
        sender_email = 'sentinelhack5.0.noreply@gmail.com'
        recipient_email = row['EMAIL']  # Email address from CSV
        email_subject = "Your Ticket"
        email_body = f"Dear {row['NAME']},\n\nPlease find attached your Ticket."

        # Send the generated Ticket via email
        send_email(sender_email, recipient_email, email_subject, email_body, output_path)

        newLast = int(row['ID'])

if newLast > last:
    with open('Last.txt', 'w') as textFile: 
        textFile.write(str(newLast))
