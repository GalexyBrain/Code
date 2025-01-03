def edit(a):
    a[0] = 1
    a[1] = 100
    a.append(100)
    return a

a = [100, -1, 2]
edit(a)
print(edit(a))