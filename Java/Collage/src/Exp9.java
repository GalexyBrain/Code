import java.util.Scanner;

class DivideByZeroException extends Exception{
    DivideByZeroException(String message){
        super(message);
    }
}

public class Exp9 {
    static double divide(int num, int den) throws DivideByZeroException{
        if (den == 0)
            throw new DivideByZeroException("Cannot divide by zero");
        return num / den;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numerator and denominator : ");
        int num = sc.nextInt();
        int den = sc.nextInt();
        try {
            System.out.println("Result of division is " + Exp9.divide(5, 0));
        }catch (DivideByZeroException e){
            System.out.println(e);
        }finally {
            sc.close();
        }
    }
}
