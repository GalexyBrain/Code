set.seed(123) 
years_experience <- 0:9
salary <- 30000 + 5000 * years_experience
data <- data.frame(Experience = years_experience, Salary = salary)

plot(data$Experience, data$Salary)

lm.model <- lm(Salary ~ Experience, data)
abline(lm.model, col = "red")
png("SalaryVsYears.png")
dev.off()

predicted <- predict(lm.model, data)
plot(data$Salary, predicted)
png("ActualVsPredicted.png")
dev.off()

cat("Intercept : ", coef(lm.model)[1])
cat("Slope : ", coef(lm.model)[2])

data$predicted <- predicted

write.csv(data, "DataWithPredicted.csv", row.names = FALSE)