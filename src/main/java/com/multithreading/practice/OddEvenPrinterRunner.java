package main.java.com.multithreading.practice;


public class OddEvenPrinterRunner {
    public static void main(String[] args) {
        OddEvenPrinterThread.letsTry();
    }
}
class OddEvenPrinter{
    volatile int counter=1;
    int limit;

    public OddEvenPrinter(int limit) {
        this.limit = limit;
    }

    public void printOdd(){
        while (true){
            if (counter<=limit && counter%2!=0){
                System.out.println("--from printOdd");
                System.out.println(Thread.currentThread().getName()+" "+counter);
                counter++;
            }
        }
    }
    public void printEven(){
        while (true){
            if (counter<=limit && counter%2==0){
                System.out.println("--from printEven");
                System.out.println(Thread.currentThread().getName()+" "+counter);
                counter++;
            }
        }
    }
}
class OddEvenPrinterThread extends Thread{
    OddEvenPrinter printer;
    String threadName;

    public OddEvenPrinterThread(OddEvenPrinter printer, String threadName) {
        this.printer = printer;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        if (threadName.equals("odd"))
            printer.printOdd();
        if (threadName.equals("even"))
            printer.printEven();
    }

    public static void letsTry(){
        OddEvenPrinter printer=new OddEvenPrinter(10);
        Thread oddThread=new OddEvenPrinterThread(printer,"odd");
        Thread evenThread=new OddEvenPrinterThread(printer,"even");

        evenThread.start();
        oddThread.start();
    }
}
