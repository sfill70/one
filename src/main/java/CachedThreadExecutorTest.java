import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadExecutorTest implements Runnable {
    int taskNumber;

    public CachedThreadExecutorTest(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        System.out.println("Обработан запрос пользователя №" + taskNumber + " на потоке " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.submit(new CachedThreadExecutorTest(i));
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(executorService);	//(1)

        TimeUnit.SECONDS.sleep(30);

        executorService.submit(new CachedThreadExecutorTest(3));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(executorService);	//(2)

        TimeUnit.SECONDS.sleep(70);

        System.out.println(executorService);	//(3)

        for (int i = 4; i < 7; i++) {
            executorService.submit(new CachedThreadExecutorTest(i));
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(executorService);	//(4)
        executorService.shutdown();
        
    }

}
