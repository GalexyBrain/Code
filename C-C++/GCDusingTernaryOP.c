#include <stdio.h>

int main(){
    int a,b,gcd = 1;
    printf("Enter 2 numbers : ");
    scanf("%d%d",&a,&b);
    for (int i = 1; i <= a && i <= b; i++){
        gcd = (a % i == 0 && b % i == 0) ? i : 1;
    }
    printf("gcd = %d\n",b); 
}