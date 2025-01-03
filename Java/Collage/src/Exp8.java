
class OuterClass{
    void display(){
        System.out.println("Display in outer class");
    }

    class InnerClass{
        void display(){
            System.out.println("Display in inner class");
        }
    }
}
public class Exp8 {
    public static void main(String[] args) {
        OuterClass c1 = new OuterClass();
        c1.display();
        OuterClass.InnerClass ic1 = c1.new InnerClass();
        ic1.display();
    }
}
