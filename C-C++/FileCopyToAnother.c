//program to copy contents of one file into another
#include <stdio.h>
int main(){
    char file1[100], file2[100],c;

    printf("Enter filename for reading : ");
    scanf("%s", file1);

    printf("Enter filename for writing : ");
    scanf("%s", file2);

    FILE *f = fopen(file1, "r"), *f1 = fopen(file2, "w");

    while(fscanf(f, "%c", &c) != EOF){
        fprintf(f1, "%c", c);
    }

    fclose(f);
    fclose(f1);
    return 0;
}