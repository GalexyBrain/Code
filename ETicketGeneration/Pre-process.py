import csv
from datetime import datetime

# Input and output file paths
input_file = 'Actual.csv'
output_file = 'data1.csv'

# Read and process input CSV
with open(input_file, newline='', encoding='utf-8') as csvfile:
    reader = csv.DictReader(csvfile)
    rows = list(reader)


# Prepare output data
output_data = []
for i, row in enumerate(rows, start=101):
    output_data.append({
        "ID": row["ID"].strip(),
        "NAME": row["Name of the team lead"].strip(),
        "PHONE": row["Contact number of the team lead"].strip(),
        "EMAIL": row["Email of the team lead"].strip()
    })

# Write to output CSV
with open(output_file, 'w', newline='', encoding='utf-8') as csvfile:
    fieldnames = ["ID", "NAME", "PHONE", "EMAIL"]
    writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

    writer.writeheader()
    for data in output_data:
        writer.writerow(data)

print("Conversion completed. Output saved to:", output_file)
