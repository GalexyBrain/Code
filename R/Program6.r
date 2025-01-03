library(MASS)
data(mammals)

pearson.cor <- cor(mammals$body, mammals$brain, method = "pearson")
spearman.cor <- cor(mammals$body, mammals$brain, method = "spearman")

cat("Pearson correlation coefficient: ", round(pearson.cor, 3), "\n")
cat("Spearman correlation coefficient: ", round(spearman.cor, 3), "\n")

plot(mammals$body, mammals$brain, xlab = "Body weight", ylab = "Brain weight", main = "Body weight vs. Brain weight")

plot(log(mammals$body), log(mammals$brain), xlab = "Log body weight", ylab = "Log brain weigh", main = "Log body weight vs. Log brain weight")