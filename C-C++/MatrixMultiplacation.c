#include <stdio.h>

int main(void) {
   int p,q,r,s;
   //get the row and columns of the matrix
   printf("Enter the rows and columns of the first matrix : ");
   scanf("%d %d",&p,&q);
   printf("Enter the rows and columns of the second matrix : ");
   scanf("%d %d",&r,&s);
   //check for the eligblity of the matrix for multiplacation
   if (q != r){
        printf("The matrix is not eligible for multiplacation");
        return 0;
   }
   //get matrix values
   int m[p][q], n[r][s];
   //first matrix
   printf("Enter the value of the first matrix : \n");
   for (int i=0; i< p; i++) {
        for (int j=0; j< q; j++) {
            printf("Enter the value for %d %d : ",i,j);
            scanf("%d",&m[i][j]);
        }
   }
   //second matrix
   printf("Enter the value of the second matrix : \n");
   for (int i=0; i< r; i++) {
        for (int j=0; j< s; j++) {
            printf("Enter the value for %d %d : ",i,j);
            scanf("%d",&n[i][j]);
        }
   }
   //multiplay the matrix
   int res[p][s],result;
   for (int i = 0; i < p; i++){
        for (int j = 0; j < s; j++){
            result = 0;
            for (int k = 0; k < q; k++){
                result += m[i][k] * n[k][j];
            }
            res[i][j] = result;
        }
   }
   //print the result
    printf("The result is :\n");
    for (int i=0; i< p; i++) {
        for (int j=0; j< s; j++) {
            printf("%5d",res[i][j]);
        }
        printf("\n");
   }
}