def arrange(n):
    return (n[0] + "".join(["," + n[i] if i % 3 == 0 else n[i] for i in range(1,len(n))]))[::-1]

num = input("Enter number : ")
print(arrange(num[::-1]),sep = "\n")