img = input("")
imageList = []
height = int(img.split(" ")[0])
for i in range(height):
    imageList.append(input("").split(" "));
x = 0
y = 0
rowlol = []
rowlmao = []
shape1 = []
shape2 = []
for i in imageList:
    for j in i:
        if int(j) == 1:
            rowlol.append([x,y])
        y += 1;
    y = 0;
    x += 1;
    shape1.append(rowlol)
    rowlol = []

for i in range(2,len(shape1) - 1):
    for j in range(0,len(shape1[i]) - 1):
        if shape1[i][j][1] == shape1[i][j - 1][1] + 1:
            rowlmao.append(shape1[i][j])
    shape2.append(rowlmao) 
    rowlmao = []

print(shape2)