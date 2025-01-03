class MyThread extends Thread{
    MyThread(){
        start();
    }
    public void run(){
        sharedResource();
    }

    synchronized public void sharedResource(){
        for(int i = 0; i < 10 ;i ++)
            System.out.println(i);
    }
}
public class SyncTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
    }
}
