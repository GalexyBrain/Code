#include <stdio.h>

int main(){
    int n,key,low,high,mid;
    printf("Enter the number of elements : ");
    scanf("%d",&n);
    int num[n];
    printf("Enter the numbers to the array : ");
    for (int i = 0; i < n; i++){
        scanf("%d",&num[i]);
    }
    printf("Enter the key : ");
    scanf("%d",&key);
    low = 0;
    high = n - 1;
    mid = (low + high) / 2;
    while(low <= high){
        if (num[mid] == key){
            printf("The element found in index : %d",mid);
            break;
        }
        else if (key > num[mid])
            low = mid + 1;
        else if (key < num[mid])
            high = mid - 1;
        mid = (low + high) / 2;
    }
}