package main.java.com.multithreading.practice;

public class DeadlockEx1 {
    public static void main(String[] args) throws InterruptedException {
        new DeadlockCounter().runTests();
    }

}
class DeadlockCounter{
    int counter=0;
    Object lock1=new Object();
    Object lock2=new Object();

    public void incrementCounter() throws InterruptedException {
        synchronized (lock1){
            System.out.println("Acquired Lock1");
            Thread.sleep(100);
            synchronized (lock2){
                counter++;
            }
        }
    }
    public void decrementCounter() throws InterruptedException {
        synchronized (lock2){
            System.out.println("Acquired Lock2");
            Thread.sleep(100);
            synchronized (lock1){
                counter++;
            }
        }
    }
    Runnable incrementer=new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    incrementCounter();
                    System.out.println("incrementin: "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Runnable decrementer=new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    decrementCounter();
                    System.out.println("decrementing: "+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public void runTests() throws InterruptedException {
        Thread thread1 =new Thread(incrementer);
        Thread thread2 =new Thread(decrementer);
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        thread1.join();
        thread2.join();

    }
}
