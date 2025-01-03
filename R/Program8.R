data(airquality)
#A
names(airquality) <- c("Ozone", "Solar.R", "Wind", "Temp", "Month", "Day")
#B
hist(airquality$Ozone, col = "red")
#C
hist(airquality$Ozone, xlab = "Ozone Levels", ylab = "Frequencey", main = "Histogram of ozone levels", axes = FALSE)    
#D
hist(airquality$Ozone, xlab = "Ozone Levels", ylab = "Frequencey", main = "Histogram of ozone levels", xlim = c(0, 200), ylim = c(0, 50))
#E
hist(airquality$Ozone, xlab = "Ozone Levels", ylab = "Frequency", main = "Histogram of Ozone Levels")
density <- density(airquality$Ozone, na.rm = TRUE)
lines(density$x, density$y * 2000, col = "red")
