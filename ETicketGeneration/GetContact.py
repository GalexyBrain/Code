import csv

# Paths for the input and output CSV files
input_file = 'data1.csv'    # Replace with your source CSV filename
output_file = 'contacts.csv'  # Desired output CSV filename

# Define the headers for the target format
headers = [
    "First Name",
    "Middle Name",
    "Last Name",
    "Phonetic First Name",
    "Phonetic Middle Name",
    "Phonetic Last Name",
    "Name Prefix",
    "Name Suffix",
    "Nickname",
    "File As",
    "Organization Name",
    "Organization Title",
    "Organization Department",
    "Birthday",
    "Notes",
    "Photo",
    "Labels",
    "Phone 1 - Label",
    "Phone 1 - Value"
]

# Open the source CSV and write to the target CSV
with open(input_file, mode='r', newline='', encoding='utf-8') as infile, \
     open(output_file, mode='w', newline='', encoding='utf-8') as outfile:
    reader = csv.DictReader(infile)
    writer = csv.DictWriter(outfile, fieldnames=headers)

    # Write the new header
    writer.writeheader()

    # Transform each row
    for row in reader:
        # Prepare an empty row for the new format
        new_row = {field: "" for field in headers}

        # Map the ID to the First Name column
        new_row["First Name"] = row.get("ID", "")

        # Set the phone details
        new_row["Phone 1 - Label"] = "Mobile"
        new_row["Phone 1 - Value"] = row.get("PHONE", "")

        # Write out the transformed row
        writer.writerow(new_row)

print(f"Conversion complete! Output written to '{output_file}'")