import sys
#geting the number of rows and columns
p = int(input("Enter the number of rows of the first matrix : "))
q = int(input("Enter the number of columns of the first matrix : "))
r = int(input("Enter the number of rows of the second matrix : "))
s = int(input("Enter the number of columns of the second matrix : "))
#checking the eligiblity of the matrix for multiplication
if q != r:
    print("The matrix is not eligible for multiplication")
    sys.exit(0)
#getting the values of the matrix
m = [[int(input("Enter the value for first matrix : ")) for i in range(q)] for j in range(p)]
n = [[int(input("Enter the value for second matrix : ")) for i in range(s)] for j in range(r)]
#show the result
for i in range(p):
    for j in range(s):
        result = 0
        for k in range(q):
            result += m[i][k] * n [k][j]
        print(result, end="  ")
    print()