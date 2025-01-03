s = input("Enter a string : ")
s1 = ""
for i in s:
    if i.islower():
        s1 += i.upper()
    else:
        s1 += i.lower()
print(s1)
#print(''.join([i.upper() if i.islower() else i.lower() for i in input("Enter a string : ")]))