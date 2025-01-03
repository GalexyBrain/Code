import java.util.Scanner;

public class Exp1 {

    static void  matrixEntry(int[][] matrix, int n, Scanner sc){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(args[0]);

        int[][] matrix1 = new int[n][n];
        int[][] matrix2 = new int[n][n];

        System.out.println("Enter the values for matrix one : ");
        Exp1.matrixEntry(matrix1, n, sc);

        System.out.println("Enter the values for matrix two : ");
        Exp1.matrixEntry(matrix2, n, sc);

        System.out.println("The result of matrix addition is : ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(matrix1[i][j] + matrix2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
