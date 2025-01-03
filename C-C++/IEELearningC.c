#include <stdio.h>
int max(int a, int b, int c) {
    return (a > b) ? ((c > a) ? c : a) : ((b > c) ? b : c);
}
int main(){
    int a, b, c;
    printf("Enter 3 numbers : ");
    scanf("%d %d %d", &a, &b, &c);
    printf("Maximum : %d\n", max(a, b, c));
    return 0;
}