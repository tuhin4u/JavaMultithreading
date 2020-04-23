package main.java.com.multithreading.basics;

import java.util.concurrent.Semaphore;

class SemaphoreEx {

    public static void main(String args[]) throws InterruptedException {
        IncorrectSemaphoreExample.example();
    }
}

class IncorrectSemaphoreExample {

    public static void example() throws InterruptedException {

        final Semaphore semaphore = new Semaphore(1);

        Thread badThread = new Thread(new Runnable() {

            public void run() {
            int i=0;
                while (true) {

                    try {
                        semaphore.acquire();
                        System.out.println("--bad thread--acquired: "+ ++i);
                        if(i==5)
                            throw new RuntimeException("--exception thrown to stop the bad thread");
                    } catch (InterruptedException ie) {
                        // handle thread interruption
                    }

                    // Thread was meant to run forever but runs into an
                    // exception that causes the thread to crash.
//                    throw new RuntimeException("exception happens at runtime.");

                    // The following line to signal the semaphore is never reached
                    finally {

                        semaphore.release();
                    }
                }
            }
        });

        badThread.start();

        // Wait for the bad thread to go belly-up
        Thread.sleep(1000);

        final Thread goodThread = new Thread(new Runnable() {

            public void run() {
                System.out.println("Good thread patiently waiting to be signalled.");
                try {
                    semaphore.acquire();
                } catch (InterruptedException ie) {
                    // handle thread interruption
                }
            }
        });

        goodThread.start();
        badThread.join();
        goodThread.join();
        System.out.println("Exiting Program");
    }
}
