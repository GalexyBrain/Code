#include <stdio.h>
#include <math.h>
void main(){
    int m, n, flag = 1;
    printf("Enter two numbers : ");
    scanf("%d %d", &m, &n);
    for (int i = m; i < n; i++){
        flag = 1;
        for (int j = 2; j <= sqrt(i); j++){
            if (i % j == 0){
                flag = 0;
                break;
            }
        }
        if (flag) printf("%d\n",i);
    }
}