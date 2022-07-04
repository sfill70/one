import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest implements  Runnable{

    @Override
    public void run() {
        System.out.println("Проверяем почту...");
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new ScheduledThreadPoolTest(), 0, 5, TimeUnit.SECONDS);
    }






}
