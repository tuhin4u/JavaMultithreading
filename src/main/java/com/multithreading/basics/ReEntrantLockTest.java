package main.java.com.multithreading.basics;


import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLockTest {

    public static void main(String args[]) throws Exception {
//        NonReentrantLock nreLock = new NonReentrantLock();
//
//        // First locking would be successful
//        nreLock.lock();
//        System.out.println("Acquired first lock");
//        // Second locking results in a self deadlock
//        System.out.println("Trying to acquire second lock");
//        nreLock.lock();
//        System.out.println("Acquired second lock");

        ReentrantLock lock=new ReentrantLock();

        lock.lock();
        System.out.println("Acquired first ReentrantLock");
        // Second locking results in a self deadlock
        System.out.println("Trying to acquire second ReentrantLock");
        lock.lock();
        System.out.println("Acquired second ReentrantLock");
    }
}

class NonReentrantLock {

    boolean isLocked;

    public NonReentrantLock() {
        isLocked = false;
    }

    public synchronized void lock() throws InterruptedException {

        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

