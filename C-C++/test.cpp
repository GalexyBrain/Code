#include <stdio.h>
int main (){
    int n1,n2,temp;
    printf("Enter two numbers : ");
    scanf("%d %d",&n1,&n2);
    while (n1>0){
        printf("%d ",n1);
        printf("%d ",n2);
        temp = n2;
        n2 = n1;
        n1 = temp % n2;
    }
    printf("test");
    printf("%d",n2);
    return 0;
}