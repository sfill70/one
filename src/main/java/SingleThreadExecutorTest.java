import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorTest implements Runnable {
    private final int taskNumber;

    public SingleThreadExecutorTest(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        System.out.printf("Обработан запрос #%d в потоке id=%d\n", taskNumber, Thread.currentThread().getId());
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 1_000; i++) {
            executorService.execute(new SingleThreadExecutorTest(i));
        }
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        executorService.shutdownNow();
    }
}
