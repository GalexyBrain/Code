data(mtcars)

#A
cat("Total observations : ", nrow(mtcars))
cat("Total variables : ", ncol(mtcars))

#B
mtcars[mtcars$hp == max(mtcars$hp),]
mtcars[mtcars$hp == min(mtcars$hp),]

#C
par(mfrow = c(3, 4))
for(i in 1:ncol(mtcars)){
    hist(mtcars[, i], main = colnames(mtcars)[i])
}

#D
cat("Difference of mean : ")
mean(mtcars$hp[mtcars$cyl == 3]) - mean(mtcars$hp[mtcars$cyl == 4])
cat("Difference of standard deviation : ")
sd(mtcars$hp[mtcars$cyl == 3]) - sd(mtcars$hp[mtcars$cyl == 4])

#E
cor.Matrix <- cor(mtcars)
max.cor <- max(cor.Matrix[upper.tri(cor.Matrix)], na.rm = TRUE)
indices <- which(cor.Matrix == max.cor, arr.ind = TRUE)
cat("Par with max cor is : ", rownames(cor.Matrix)[indices[1,]])
cat("With cor : ", max.cor)