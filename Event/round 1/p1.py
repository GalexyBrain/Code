def draw_hollow_triangle(size):
    if size < 2:
        print("Size should be 2 or greater.")
        return

    for i in range(1, size + 1):
        # Calculate the number of spaces on the left for alignment
        spaces = size - i
        line = " " * spaces

        if i == 1:
            # Top of the triangle
            line += "*"
        elif i == size:
            # Bottom edge of the triangle
            line += "*" * (size * 2 - 1)
        else:
            # Middle rows with hollow center
            line += "* " + "  " * (i - 2) + "*"
        
        print(line)

# Example usage
size = int(input("Enter the size of the triangle: "))
draw_hollow_triangle(size)
