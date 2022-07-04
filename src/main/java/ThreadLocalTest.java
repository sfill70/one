public class ThreadLocalTest implements Runnable {

    int counter;
    ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<>();

    /*@Override
    public void run() {
        counter++;

        if(threadLocalCounter.get() != null) {
            threadLocalCounter.set(threadLocalCounter.get() + 1);
        } else {
            threadLocalCounter.set(0);
        }
        printCounters();
    }*/

    @Override
    public void run() {
        counter++;

        if(threadLocalCounter.get() != null) {
            threadLocalCounter.set(threadLocalCounter.get() + 1);
        } else {
            if (counter % 2 == 0) {
                threadLocalCounter.remove();
            } else {
                threadLocalCounter.set(0);
            }
        }
        printCounters();
    }



    public void printCounters(){
        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
    }

    public static void main(String[] args) {
        ThreadLocalTest threadDemo = new ThreadLocalTest();

        Thread t1 = new Thread(threadDemo);
        Thread t2 = new Thread(threadDemo);
        Thread t3 = new Thread(threadDemo);

        t1.start();
        t2.start();
        t3.start();

    }
}
