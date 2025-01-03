class MyTread implements Runnable{
    MyTread(){
        new Thread(this).start();
    }
    private static volatile boolean running = true;
    public void run(){
        while(running){
            System.out.println("Thread : " + Thread.currentThread().getId() + " is running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    void stop(){
        running = false;
    }
}

public class Exp11 {
    public static void main(String[] args) {
        MyTread t1 = new MyTread();
        MyTread t2 = new MyTread();

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        t1.stop();
        t2.stop();
    }
}
