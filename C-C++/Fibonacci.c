#include <stdio.h>
void main(){
    int n, lastValue = 0, CurrentValue = 1, temp;
    printf("How many values : ");
    scanf("%d", &n);
    int i = 0;
    while (i < n){
        i++;
        printf("%d\n", lastValue);
        temp = CurrentValue;
        CurrentValue += lastValue;
        lastValue = temp;
    }
}