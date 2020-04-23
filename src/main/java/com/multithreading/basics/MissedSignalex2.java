package main.java.com.multithreading.basics;


import java.util.concurrent.Semaphore;
class MissedSignaleEx2{
    public static void main(String[] args) throws InterruptedException {
        MissedSignale.example();
    }
}
class MissedSignale {
    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);
        Thread signaller = new Thread(new Runnable() {
            public void run() {
                semaphore.release();
                System.out.println("Sent signal");
            }
        });

        Thread waiter = new Thread(new Runnable() {
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("Received signal");
                } catch (InterruptedException ie) {
                    // handle interruption
                }
            }
        });

        signaller.start();
        signaller.join();
        Thread.sleep(5000);
        waiter.start();
        waiter.join();

        System.out.println("Program Exiting.");
    }
}

