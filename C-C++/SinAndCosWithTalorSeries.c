#include <math.h>
#include <stdio.h>
int main() {
    int n;
    float sins = 0, coss = 0, deg, rad;
    printf("Enter the degree: ");
    scanf("%f", &deg);
    rad = deg * (M_PI / 180.0);
    printf("Enter the number of terms: ");
    scanf("%d", &n);
    for (int i = 0, j = 1, k = 1, fact = 1; i < n; i++, j += 2, k *= -1, fact *= j * (j -1)) {
        sins += k * pow(rad, j) / fact;
        coss += k * pow(rad, j - 1) / (fact/ j);
    }
    printf("\nSin: %f\nCos: %f", sins, coss);
    printf("\nLibrary Sin: %f\nLibrary Cos: %f\n", sin(rad), cos(rad));
    return 0;
}