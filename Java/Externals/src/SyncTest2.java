class Bank{
    int balance = 0;

    synchronized void deposit(int amount){
        balance += amount;
        System.out.println("Deposit of " + amount + " Successful");
        notify();
    }

    synchronized void withdraw(int amount){
        while(balance < amount){
            System.out.println("Balance is too less waiting for deposit");
            try {
                wait();
            }catch (InterruptedException e){Thread.currentThread().interrupt();}
        }
        balance -= amount;
        System.out.println("New balance after withdraw : " + balance);
    }
}


public class SyncTest2 {
    public static void main(String[] args) {
        Bank b = new Bank();
        Thread with = new Thread(() -> b.withdraw(15000));
        Thread dep = new Thread(() -> b.deposit(10000));
        Thread dep2 = new Thread(() -> b.deposit(700));
        Thread dep3 = new Thread(() -> b.deposit(7000));

        with.start();
        dep.start();
        dep2.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        dep3.start();
    }
}