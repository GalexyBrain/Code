n1 = int(input("Enter a number: "))
n2 = int(input("Etner another number : "))
factors = []
if max(n1, n2) % min(n1, n2) == 0:
    factors.append(min(n1,n2))
else:
    for i in range (1, (min(n1, n2)//2) + 1):
        if n1 % i == 0 and n2 % i == 0:
            factors.append(i)
print("GCD is", max(factors))
    