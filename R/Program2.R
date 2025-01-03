monthlyRevenue <- c(seq(50000, 100000, 5000))
monthlyExpenses <- c(30000, 32000, 35000, 40000, 42000, 45000, 48000, 50000, 52000, 55000, 58000, 60000)

taxRate <- 0.3

profit <- (monthlyRevenue - monthlyExpenses)
profitAfterTax <- profit - (profit * taxRate)
profitMargin <- round(profitAfterTax / monthlyRevenue * 100, 2)

profitMean <- round(mean(profitAfterTax), 2) 

goodMonths <- profitAfterTax > profitMean
badMonths <- profitAfterTax < profitMean

bestMonth <- which.max(profitAfterTax)
worstMonth <- which.min(profitAfterTax)

data <- data.frame(
    Month = 1:12,
    Profit = paste("$ ",profit / 1000, "k"),
    ProfitAfterTax = paste("$ ",profitAfterTax / 1000, "k"),
    ProfitMargin = paste(profitMargin, "%"),
    GoodMonths = ifelse(goodMonths, "Yes", "No"),
    BadMonths = ifelse(badMonths, "Yes", "No"),
    BestMonth = ifelse(1:12 == bestMonth, "Yes", "No"),
    WorstMonth = ifelse(1:12 == worstMonth, "Yes", "No")
)
write.csv(data, "Finance.csv", row.names = FALSE)
print(data)