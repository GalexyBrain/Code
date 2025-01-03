# Define the mapping dictionary
mapping_dict = {
    'a': '!',
    'b': '@',
    'c': '#',
    'd': '$',
    'e': '%',
    'f': '^',
    'g': '&',
    'h': '*',
    'i': '(',
    'j': ')',
    'k': '_',
    'l': '-',
    'm': '+',
    'n': '=',
    'o': '{',
    'p': '}',
    'q': '[',
    'r': ']',
    's': '\\',
    't': '|',
    'u': ';',
    'v': ':',
    'w': '??',
    'x': '//',
    'y': '>',
    'z': '<',
    "?": '?'
}

# Take input
input_str = input("Enter the string: ").lower()
new_string = ''

# Loop through characters
for char in input_str:
    if char.isalpha():  # Check if the character is a letter
        new_string += mapping_dict.get(char.lower(), '?')  # Use lowercase keys
    else:
        new_string += mapping_dict.get('?', '?')  # Default for unknown chars

# Print the result
print(new_string)
