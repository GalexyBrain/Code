import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var test = new B();
        test.show();
        test.hello();
    }

    public static int reverse(String s){
        if (s.length() <= 1)  return 1;
        if(s.charAt(0) != s.charAt(s.length() - 1)) return 0;
        return reverse(s.substring(1, s.length() - 1));
    }
}

interface A{
    static void hello(){
        System.out.println("In interface");
    }
}

class B implements A{
    void show(){
        A.hello();
    }

     static void hello(){
        System.out.println("In class");
    }
}