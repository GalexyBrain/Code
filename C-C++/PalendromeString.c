//program to check if the string is a palendrome of not
#include <stdio.h>
void main(){
    char str[100];
    int i = 0, cmp = 1;
    printf("Enter the string : ");
    scanf("%s", str);
    for (i = 0; str[i + 1] != '\0'; i++);
    for (int j = 0;i >= j; i--, j++){
        if (str[i] != str[j]){
            cmp--;
            break;
        }
    }
    if (cmp)
        printf("Palendrome");
    else 
        printf("Not palendrome");
}