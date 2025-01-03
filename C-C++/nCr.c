#include <stdio.h>

int nCr(int n, int r) {
    if (r == 0 || n == r) {
        return 1;
    } else {
        return nCr(n-1, r-1) + nCr(n-1, r);
    }
}

int main() {
    int n, r;
    printf("Enter the values of n and r: ");
    scanf("%d %d", &n, &r);
    printf("%dC%d = %d\n", n, r, nCr(n, r));
    return 0;
}
