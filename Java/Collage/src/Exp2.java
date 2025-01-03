import java.util.Scanner;

class Stack{
    final int MAX = 10;
    int[] stack;
    int top;
    Stack(){
        stack = new int[MAX];
        top = -1;
    }

    void push(Scanner sc){
        if(top == MAX - 1){
            System.out.println("Stack overflow");
            return;
        }
        System.out.println("Enter the value to push : ");
        int element = sc.nextInt();
        stack[++top] = element;
    }

    void pop(){
        if(top == -1){
            System.out.println("Stack underflow");
            return;
        }
        System.out.println("Element deleted is " + stack[top--]);
    }

    void peek(){
        if(top == -1){
            System.out.println("Stack empty");
            return;
        }
        System.out.println("Element at top " + stack[top]);
    }

    void display(){
        if(top == -1){
            System.out.println("Stack empty");
            return;
        }
        System.out.println("Stack elements are : ");
        for(int i: stack){
            System.out.print(i + "  ");
        }
    }
}
public class Exp2 {
    public static void main(String[] args) {
        Stack s = new Stack();
        Scanner sc = new Scanner(System.in);
        int ch;
        System.out.println("Main Menu");
        System.out.println("1.Push\n2.Pop\n3.Peek\n4.Display\n5.Exit");
        do{
            System.out.println("Enter your choice : ");
            ch = sc.nextInt();
            switch (ch){
                case 1:
                    s.push(sc);
                    break;
                case 2:
                    s.pop();
                    break;
                case 3:
                    s.peek();
                    break;
                case 4:
                    s.display();
                    break;
                case 5:
                    break;
            }
        }while(ch != 5);
    }
}
