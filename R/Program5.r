sieve_of_eratosthenes <- function(limit) {
  is_prime <- rep(TRUE, limit)
  is_prime[1] = FALSE

  for (num in 2:sqrt(limit)) {
    is_prime[seq(num^2, limit, num)] <- FALSE
  }
  return(which(is_prime))
}

limit <- 50
prime_numbers <- sieve_of_eratosthenes(limit)

cat("Prime numbers up to", limit, "are:", prime_numbers, "\n")
