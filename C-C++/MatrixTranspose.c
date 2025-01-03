#include <stdio.h>

int main(void) {
   int p,q;
   //get the row and columns of the matrix
   printf("Enter the rows and columns of the first matrix : ");
   scanf("%d %d",&p,&q);
   int m[p][q], n[q][p];
   //first matrix
   printf("Enter the value of the matrix : \n");
   for (int i=0; i< p; i++) {
        for (int j=0; j< q; j++) {
            printf("Enter the value for %d %d : ",i,j);
            scanf("%d",&m[i][j]);
        }
   }
   //multiplay the matrix
   for (int i = 0; i < p; i++){
        for (int j = 0; j < q; j++){
            n[j][i] = m[i][j];
        }
   }
   //print the result
    printf("The result is :\n");
    for (int i=0; i< q; i++) {
        for (int j=0; j< p; j++) {
            printf("%5d",n[i][j]);
        }
        printf("\n");
   }
}