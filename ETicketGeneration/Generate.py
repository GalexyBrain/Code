import fitz  # PyMuPDF
import pandas as pd
import os
import re

# Constants
TEMPLATE_PATH = "Certificate.pdf"
OUTPUT_FOLDER = "generated_pdfs"
CSV_FILE = "data.csv"
CUSTOM_FONT_PATH = "Poppins-Regular.ttf"

# Create output folder if it doesn't exist
os.makedirs(OUTPUT_FOLDER, exist_ok=True)

def insert_text_centered(page, placeholder, text, font_size=17.8):
    instances = page.search_for(placeholder)
    if not instances:
        print(f"⚠️ Placeholder '{placeholder}' not found.")
        return
    for inst in instances:
        page.add_redact_annot(inst, fill=(0, 0, 0))
    page.apply_redactions()

    for inst in instances:
        page.insert_textbox(
            inst,
            text,
            fontsize=font_size,
            fontfile=CUSTOM_FONT_PATH,
            color=(1, 1, 1),
            align=1  # Center align
        )

def replace_placeholders_in_pdf(template_path, output_path, row_data):
    doc = fitz.open(template_path)
    for page in doc:
        for key, value in row_data.items():
            text = str(value).strip()
            placeholder = f"{{{{{key}}}}}"  # Example: {{NAME}}, {{ID}}, etc.

            # Adjust font size only for NAME (can add other rules if needed)
            if key.upper() == "NAME":
                base_font_size = 17.8
                limit = 15
                font_size = base_font_size if len(text) <= limit else max(12, base_font_size - (len(text) - limit) * 0.3)
            else:
                font_size = 17.8

            insert_text_centered(page, placeholder, text, font_size)

    doc.save(output_path)
    doc.close()

# Read the data
data = pd.read_csv(CSV_FILE)

for _, row in data.iterrows():
    row_dict = row.to_dict()
    name = str(row_dict.get("NAME", "Unknown")).strip()
    usn = str(row_dict.get("ID", "000")).strip()

    safe_name = re.sub(r'[^\w\s-]', '', name).replace(" ", "_")
    output_path = os.path.join(OUTPUT_FOLDER, f"{safe_name}_{usn}.pdf")

    replace_placeholders_in_pdf(TEMPLATE_PATH, output_path, row_dict)
    print(f"✅ Generated certificate for {name} ({usn})")

print("🎉 All certificates have been generated.")
