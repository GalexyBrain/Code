#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char str[100], pat[50], rep[50], ans[100];
int index[50], length = 0;

void findSimilar(){
    int flag = 1;
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == pat[0]){
            for(int j = i, k = 0; pat[j] != '\0'; j++, k++){
                if (str[j] != pat[k]){
                    flag = 0;
                    break;
                }
            }
            if(flag == 1){
                index[length] = i;
                length++;
            }
        }
    }
}

void stringMatch(){
    findSimilar();
    int ind = 0;
    if(length == 0){
        printf("NO MATCHES FOUND");
    }else{
        for(int i = 0, j = 0; str[j] != '\0'; i++, j++){
            ans[i] = str[j];
            if (j == index[ind]){
                ind++;
                for(int k = 0; rep[k] != '\0'; k++, i++){
                    ans[i] = rep[k];
                }
                i -= ind;
            }
        }
        printf("The result is : %s", ans);
    }
}

void main(){
    printf("Main string : \n");
    gets(str);
    printf("Pattern string : \n");
    fflush(stdin);
    gets(pat);
    printf("Replace string : \n");
    fflush(stdin);
    gets(rep);
    stringMatch();
}