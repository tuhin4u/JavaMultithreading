package main.java.com.multithreading.practice;

import java.util.Random;

public class CriticalSectionEx {
    public static void main(String[] args) {
        Worker1.runTest();
    }
}
class Worker1{
    int randInt;
    Random random=new Random(System.currentTimeMillis());
    public void printer(){
        for (int i = 1000; i >0 ; i++) {
            synchronized (this){
                if(randInt % 5== 0){
                    if (randInt % 5!=0)
                        System.out.println(randInt);
                }
            }

        }
    }
    public void modifier(){
        for (int i = 1000; i > 0; i++) {
            synchronized (this){

                randInt=random.nextInt();
            }
        }
    }
    public static void runTest(){
        final Worker1 worker=new Worker1();
        Thread t1=new Thread(()->worker.modifier());
        Thread t2=new Thread(()->worker.printer());
        t1.start();
        t2.start();

    }
}
