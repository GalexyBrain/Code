class Queue{
    int[] q;
    int front = -1;
    int rear = -1;
    int count = 0;
    Queue(){
        q = new int[15];
    }
    void produce(int i){
        if(count == 15){
            System.out.println("cannot produce");
        }
        rear = (rear + 1) % 15;
        q[rear] = i;
        count++;
    }

    int consume(){
        if(count == 0){
            System.out.println("Cannot consume");
            return -1;
        }
        front = (front + 1) % 15;
        return q[front];
    }
}

class ProducerThread extends Thread{
    Queue q;
    ProducerThread(Queue q){
        this.q = q;
        start();
    }
    public void run(){
        for(int i = 0; i < 15; i++){
            q.produce(i);
        }
    }
}

class ConsumerThread extends Thread{
    Queue q;
    ConsumerThread(Queue q){
        this.q = q;
        start();
    }
    public void run(){
        for(int i = 0; i < 15; i++)
            System.out.println("Consumed : " + q.consume());
    }
}

public class Sync {
    public static void main(String[] args) {
        Queue q = new Queue();
        synchronized (q){
            ProducerThread t = new ProducerThread(q);
            ConsumerThread c = new ConsumerThread(q);
        }
    }
}
