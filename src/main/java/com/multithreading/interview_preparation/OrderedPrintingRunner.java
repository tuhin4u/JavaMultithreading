package main.java.com.multithreading.interview_preparation;

public class OrderedPrintingRunner {
    public static void main(String[] args) {
        OrderedPrinting obj = new OrderedPrinting();

        OrderedPrintingThread t1 = new OrderedPrintingThread(obj, "first");
        OrderedPrintingThread t2 = new OrderedPrintingThread(obj, "second");
        OrderedPrintingThread t3 = new OrderedPrintingThread(obj, "third");

        t2.start();
        t3.start();
        t1.start();
    }
}
class OrderedPrinting
{

    private volatile int flag;

    public OrderedPrinting()
    {
        flag = 1;
    }

    public void printFirst() throws InterruptedException
    {
        for(;;) {
            if (flag==1) {
                System.out.println("First");
                flag = 2;
                break;
            }
        }
    }

    public void printSecond() throws InterruptedException
    {
        for(;;)
        {
            if (flag==2)
            {
                System.out.println("Second");
                flag=3;
                break;
            }
        }
    }

    public void printThird() throws InterruptedException
    {
        for(;;)
        {
            if (flag==3)
            {
                System.out.println("Third");
                flag = 1;
                break;
            }
        }
    }
}

class OrderedPrintingThread extends Thread
{
    private OrderedPrinting obj;
    private String method;

    public OrderedPrintingThread(OrderedPrinting obj, String method)
    {
        this.method = method;
        this.obj = obj;
    }

    public void run()
    {
        //for printing "First"
        if ("first".equals(method))
        {
            try
            {
                obj.printFirst();
            }
            catch(InterruptedException e)
            {

            }
        }
        //for printing "Second"
        else if ("second".equals(method))
        {
            try
            {
                obj.printSecond();
            }
            catch(InterruptedException e)
            {

            }
        }
        //for printing "Third"
        else if ("third".equals(method))
        {
            try
            {
                obj.printThird();
            }
            catch(InterruptedException e)
            {

            }
        }
    }
}



