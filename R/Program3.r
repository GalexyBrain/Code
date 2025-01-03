A <- matrix(1:9, nrow = 3, byrow = TRUE)
B <- matrix(9:1, nrow = 3, byrow = TRUE)

cat("\nTranspose of Matrix A:\n")
print(t(A))

cat("\nTranspose of Matrix B:\n")
print(t(B))

cat("\nMatrix Addition (A + B):\n")
print(A + B)

cat("\nMatrix Subtraction (A - B):\n")
print(A - B)

cat("\nMatrix Multiplication (A * B):\n")
print(A %*% B)