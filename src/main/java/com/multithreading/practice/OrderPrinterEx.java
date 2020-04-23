package main.java.com.multithreading.practice;

public class OrderPrinterEx {
    public static void main(String[] args) {
        OrderPrinterThread.letsTry();
    }
}
class OrderPrinter{
    volatile int flag=1;

    void printOne(){
        while (true){
            if (flag==1){
                System.out.println("one");
                flag=2;
            }
        }
    }
    void printTwo(){
        while (true){
            if (flag==2){
                System.out.println("two");
                flag=3;
            }
        }
    }
    void printThree(){
        while (true){
            if (flag==3){
                System.out.println("three");
                flag=0;
            }
        }
    }
}
class OrderPrinterThread extends Thread{
    OrderPrinter orderPrinter;
    String name;
    OrderPrinterThread(OrderPrinter orderPrinter,String name){
        this.orderPrinter=orderPrinter;
        this.name=name;
    }
    @Override
    public void run() {
        if (name.equals("one"))
            orderPrinter.printOne();
        if (name.equals("two"))
            orderPrinter.printTwo();
        if (name.equals("three"))
            orderPrinter.printThree();
    }
    public static void letsTry(){
        OrderPrinter op=new OrderPrinter();
        Thread t1=new OrderPrinterThread(op,"one");
        Thread t2=new OrderPrinterThread(op,"two");
        Thread t3=new OrderPrinterThread(op,"three");

        t2.start();
        t1.start();
        t3.start();
    }

}
