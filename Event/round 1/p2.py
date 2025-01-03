import random

def generate_random_grid(rows=10, cols=10):
    symbols = ['*', '#']
    grid = [[random.choice(symbols) for _ in range(cols)] for _ in range(rows)]

    for row in grid:
        print(' '.join(row))

# Example usage
generate_random_grid()
