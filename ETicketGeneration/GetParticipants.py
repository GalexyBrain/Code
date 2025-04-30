import csv

# Input CSV file
input_file = "Actual.csv"
output_file = "participant_names.csv"

# Indices for participant name columns (based on your format)
participant_name_indices = [3, 5, 7, 9]  # Participant 1, 2, 3 names

participant_list = []

with open(input_file, newline='', encoding='utf-8') as csvfile:
    reader = csv.reader(csvfile)
    header = next(reader)  # Skip header
    for row in reader:
        for idx in participant_name_indices:
            name = row[idx].strip()
            if name and name.lower() != "none":
                participant_list.append([name])

# Save to new CSV
with open(output_file, mode='w', newline='', encoding='utf-8') as outfile:
    writer = csv.writer(outfile)
    writer.writerow(["Participant Name"])
    writer.writerows(participant_list)

print("✅ Extracted participant names to", output_file)
