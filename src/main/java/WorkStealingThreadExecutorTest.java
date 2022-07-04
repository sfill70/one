import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class WorkStealingThreadExecutorTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Collection<Callable<String>> tasks = new ArrayList<>();
        ExecutorService executorService = Executors.newWorkStealingPool(10);

        for (int i = 0; i < 20; i++) {
            int taskNumber = i;
            int finalI = i;
            Callable<String> callable = () -> {
                System.out.println("Обработан запрос пользователя №" + taskNumber +
                        " на потоке " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                return ": " + finalI;
            };
            tasks.add(callable);
        }

//        executorService.invokeAll(tasks);
        var results = executorService.invokeAll(tasks);

        for (Future<String> st:results
             ) {
            System.out.println(st.get());
        }
    }

}
