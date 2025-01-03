//program to print the ASCII value of the name
#include <stdio.h>
int main(){
    char name[25];
    printf("Enter name: ");
    scanf("%s",name);
    for(int i=0; name[i] != '\0'; i++)
        printf("\n%d",name[i]);
}