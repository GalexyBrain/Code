import fitz  # PyMuPDF
import pandas as pd
import os

# Constants
TEMPLATE_PATH = "Certificate.pdf"  # Path to your template PDF
OUTPUT_FOLDER = "generated_pdfs"
CSV_FILE = "data.csv"  # CSV file containing participant details

# Create the output folder if it doesn't exist
os.makedirs(OUTPUT_FOLDER, exist_ok=True)

# Define the path to your custom font
CUSTOM_FONT_PATH = "Poppins-Regular.ttf"

def replace_text_in_pdf(template_path, output_path, name, usn):
    """
    Replace placeholders {{name}} and {{usn}} in the PDF using a custom font.
    Dynamically adjust font size for the name based on its length and align text centrally.
    """
    doc = fitz.open(template_path)  # Open the PDF template

    for page in doc:
        # Dynamic font size for name
        max_font_size = 17.8  # Base font size for names with 15 or fewer characters
        name_length_limit = 15  # Threshold for font adjustment
        scaling_factor = 0.2  # Font size reduction per character exceeding the limit

        if len(name) <= name_length_limit:
            font_size_name = max_font_size
        else:
            font_size_name = max_font_size - (len(name) - name_length_limit) * scaling_factor

        # Font size for USN
        font_size_usn = 17.8

        # Replace {{name}}
        name_instances = page.search_for("{{name}}")
        for inst in name_instances:
            page.add_redact_annot(inst, fill=(1, 1, 1))  # Redact (white out) the placeholder
        page.apply_redactions()  # Apply the redactions
        for inst in name_instances:
            # Center-align the name
            x0, y0, x1, y1 = inst  # Bounding box of the placeholder
            
            # Calculate the width of the text manually using insert_text and its bounding box
            # Create a temporary rectangle and measure its width
            rect = fitz.Rect(x0, y0 + 15, x1, y1 + 15)  # Slight vertical offset
            text_width = rect.width  # Width of the bounding box
            
            center_x = (x0 + x1) / 2  # Calculate the center of the placeholder
            start_x = center_x - text_width / 2  # Adjust the start position for centering

            # Insert the name at the adjusted position
            page.insert_text(
                (start_x - 60, y0 + 14),  # Adjusted for centering
                name,
                fontsize=font_size_name,
                fontfile=CUSTOM_FONT_PATH,  # Use the custom font
                color=(0, 0, 0)
            )

        # Replace {{usn}}
        usn_instances = page.search_for("{{usn}}")
        for inst in usn_instances:
            page.add_redact_annot(inst, fill=(1, 1, 1))  # Redact (white out) the placeholder
        page.apply_redactions()  # Apply the redactions
        for inst in usn_instances:
            # Center-align the USN
            x0, y0, x1, y1 = inst  # Bounding box of the placeholder
            
            # Calculate the width of the USN text
            rect = fitz.Rect(x0, y0 + 15, x1, y1 + 15)
            text_width = rect.width  # Width of the bounding box
            
            center_x = (x0 + x1) / 2  # Calculate the center of the placeholder
            start_x = center_x - text_width / 2  # Adjust the start position for centering

            # Insert the USN at the adjusted position
            page.insert_text(
                (start_x, y0 + 15),  # Adjusted for centering
                usn,
                fontsize=font_size_usn,
                fontfile=CUSTOM_FONT_PATH,  # Use the custom font
                color=(0, 0, 0)
            )

    doc.save(output_path)  # Save the updated PDF
    doc.close()

# Read data from the CSV file
data = pd.read_csv(CSV_FILE)

# Process each row in the CSV
for _, row in data.iterrows():
    name = row["Name"]
    usn = row["USN"]

    # Generate personalized PDF
    output_path = os.path.join(OUTPUT_FOLDER, f"{name}_{usn}.pdf")
    replace_text_in_pdf(TEMPLATE_PATH, output_path, name, usn)

    # Log the generated certificate
    print(f"Generated certificate for {name} ({usn})")

print("All certificates have been generated.")
