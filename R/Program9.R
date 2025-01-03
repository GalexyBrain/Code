employee.data <- data.frame(
    id = c(1:20),
    name = c("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"),
    salary = sample(5:55, size = 20) * 1000,
    start.date = sample(seq(as.Date("2021-01-01"), as.Date("2021-12-31"), "day"), size = 20),
    dept = sample(c(rep("IT", 7), rep("HR", 5), rep("Finance", 5), rep("Marketing", 5)), size = 20)
)
write.csv(employee.data, "input.csv", row.names = FALSE)

employee.data <- read.csv("input.csv")

#A
cat("Total rows : ", nrow(employee.data))
cat("Total columns : ", ncol(employee.data))

#B
cat("Maximum salary : ", max(employee.data$salary))

#C
cat("Maximum salary employee : ")
employee.data[employee.data$salary == max(employee.data$salary), ]

#D
IT.data <- employee.data[employee.data$dept == "IT", ]
IT.data

#E
IT.data.high.salary <- IT.data[IT.data$salary >= 20000, ]
IT.data.high.salary
write.csv(IT.data.high.salary, "output.csv", row.names = FALSE)