//program to find the sum,mean and standard deviation using pointers 
#include <stdio.h>
#include <math.h>

int main(){
    float sum = 0, stdDeviation = 0, mean = 0, *ptr;
    int n;

    printf("Enter the number of terms : ");
    scanf("%d",&n);

    float arr[n];
    ptr = arr;
    printf("Enter the numbers to the array : ");
    for (int i = 0; i < n; i++){
        scanf("%f",&arr[i]);
        sum += *ptr;
        ptr++;
    }

    mean = sum / n;

    ptr = arr;
    for (int i = 0; i < n; i++,ptr++){
        stdDeviation += pow((*ptr - mean),2);
    }
    stdDeviation = pow(stdDeviation / n, 0.5);

    printf("\nSum : %f",sum);
    printf("\nMean : %f",mean);
    printf("\nStanduard Deviation :%f",stdDeviation);
    return 0;
}