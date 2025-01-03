class complex:
    def __init__(self, real, imag = 0):
        self.real = real
        self.imag = imag
        if type(self.real) == type(str()):
            number = self.real.strip("i").split()
            sign = 1
            for i in number:
                if i.isdigit() and type(self.real) == type(str()):
                    self.real = int(i)
                elif i.isdigit():
                    self.imag = int(i) * sign
                elif i in '-':
                    sign = -1
                    
    
    def __iadd__(self, other):
        return complex(self.real + other.real, self.imag + other.imag)
    
    def __isub__(self, other):
        return complex(self.real - other.real, self.imag - other.imag)
    
    def __imul__(self, other):
        return complex(self.real * other.real + (self.imag * other.imag * -1), self.real * other.imag + other.real * self.imag)
    
    def __add__(self, other):
        return complex(self.real + other.real, self.imag + other.imag)
    
    def __sub__(self, other):
        return complex(self.real - other.real, self.imag - other.imag)
    
    def __mul__(self, other):
        return complex(self.real * other.real + (self.imag * other.imag * -1), self.real * other.imag + other.real * self.imag)
    
    
    

n = int(input("How many complex numbers : "))
cList = [complex(input("Enter a complex number : ")) for i in range(n)]
cListAdded, cListSubtracted = complex(0, 0), complex(0, 0)
for i in cList:
    cListAdded += i
    cListSubtracted -= i
print(f"({cListAdded.real}) + ({cListAdded.imag})i")
print(f"({cListSubtracted.real}) + ({cListSubtracted.imag})i")
test = cList[0] * cList[1]
print(f"({test.real}) + ({test.imag})i")