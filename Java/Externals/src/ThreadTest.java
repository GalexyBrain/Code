public class ThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
          for(int i = 0; i < 10; i++) System.out.println(i);
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){}


        t1.start();
        t2.start();
    }
}
