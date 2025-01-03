class MyThread extends Thread{
    MyThread(){
        super();
        start();
    }
    public void run(){
        System.out.println("Running in child thread");
    }
}

public class Exp12 {
    public static void main(String[] args) {
        new MyThread();
        System.out.println("Running in parent thread");
    }
}
