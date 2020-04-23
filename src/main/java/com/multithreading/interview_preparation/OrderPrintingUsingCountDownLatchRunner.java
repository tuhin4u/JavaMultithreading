package main.java.com.multithreading.interview_preparation;
import java.util.concurrent.CountDownLatch;

public class OrderPrintingUsingCountDownLatchRunner {
    public static void main(String[] args)
    {
        OrderedPrintingCountDownLatch obj = new OrderedPrintingCountDownLatch();

        OrderedPrintingCountDownLatchThread t1 = new OrderedPrintingCountDownLatchThread(obj, "first");
        OrderedPrintingCountDownLatchThread t2 = new OrderedPrintingCountDownLatchThread(obj, "second");
        OrderedPrintingCountDownLatchThread t3 = new OrderedPrintingCountDownLatchThread(obj, "third");

        t3.start();
        t2.start();
        t1.start();

    }
}

class OrderedPrintingCountDownLatch
{
    CountDownLatch latch1;
    CountDownLatch latch2;

    public OrderedPrintingCountDownLatch()
    {
        latch1 = new CountDownLatch(1);
        latch2 = new CountDownLatch(1);
    }

    public void printFirst() throws InterruptedException
    {
        System.out.println("First");
        latch1.countDown();
    }

    public void printSecond() throws InterruptedException
    {
        latch1.await();
        System.out.println("Second");
        latch2.countDown();
    }

    public void printThird() throws InterruptedException
    {
        latch2.await();
        System.out.println("Third");
    }
}

class OrderedPrintingCountDownLatchThread extends Thread
{
    private OrderedPrintingCountDownLatch obj;
    private String method;

    public OrderedPrintingCountDownLatchThread(OrderedPrintingCountDownLatch obj, String method)
    {
        this.method = method;
        this.obj = obj;
    }

    public void run()
    {
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


