import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


public class FixedThreadExecutorTest implements Runnable {

    final static int cores = Runtime.getRuntime().availableProcessors();
    int taskNumber;

    public static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();

    public static int[] arrayInt;

    public FixedThreadExecutorTest(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        try {
            MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        /*System.out.println("Take array "+ arrayInt[0]+"; Обработан запрос пользователя №" + taskNumber +
                " на потоке " + Thread.currentThread().getName());*/
        if (!queue.isEmpty()) {
            int i = queue.poll();
            System.out.println("Получено - " + i + "; Обработан запрос пользователя №" + taskNumber +
                    " на потоке " + Thread.currentThread().getName());
        } else {
            System.out.println("Очередь кончилась - " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(cores);

        for (int i = 0; i < 150; i++) {
            queue.offer(i);
        }

        arrayInt = new int[1];
        arrayInt[0] = 234;
      /*  for (int i = 0; i < 30; i++) {
            executorService.execute(new FixedThreadExecutorTest(i));
        }

        executorService.shutdown();*/

       /* for (int i = 0; i < 150; i++) {
            executorService.execute(new FixedThreadExecutorTest(i));
        }*/
        while (!queue.isEmpty()) {
            executorService.execute(new FixedThreadExecutorTest(1));
        }
        /*try {
            executorService.awaitTermination(11, SECONDS);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        C*/
        System.out.println(executorService);
        List<Runnable> list = executorService.shutdownNow();
        executorService.awaitTermination(10, SECONDS);
//        System.out.println(list);
        System.out.println(list.size());
       /* for (Runnable rn:list
             ) {
            System.out.println(rn);
        }*/
    }


}
