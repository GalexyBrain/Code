//program to reverse a string
#include <stdio.h>

int main(){
    char str[25], rev[25];

    printf("Enter a string : ");
    scanf("%s",str);

    int len;
    for (len = 0; str[len] != '\0';len++);

    for (int i = len; i >= 0; i--){
        rev[i] = str[len - i - 1];
    }
    rev[len] = '\0';

    printf("Reverse of the string '%s' is '%s'",str,rev);
    return 0;
}