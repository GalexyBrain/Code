def shift_ascii_values(string, shift_amount):
    shifted_string = ""
    for char in string:
        if char.isalpha():
            shifted_string += chr(ord(char) + shift_amount)
        else :
            shifted_string += char
    return shifted_string

# Example usage
input_string = input("Enter a string: ")
for i in range(-26, 27):
    result = shift_ascii_values(input_string, i)
    print("Shifted string by", i, ":", result)
