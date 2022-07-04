import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//Создаем ExecutorService на 2 потока
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//Создаем 5 задач
        MyRunnable task1 = new MyRunnable();
        MyRunnable task2 = new MyRunnable();
        MyRunnable task3 = new MyRunnable();
        MyRunnable task4 = new MyRunnable();
        MyRunnable task5 = new MyRunnable();

        final List<MyRunnable> tasks = List.of(task1, task2, task3, task4, task5);
//Отправляем на обработку список, который содержит 5 ранее созданных задач
        final List<Future<Void>> futures = executorService.invokeAll(tasks, 6, TimeUnit.SECONDS);
        System.out.println("got futures");
        System.out.println(futures.toString());

//Останавливаем ExecutorService
        executorService.shutdown();
        System.out.println(futures.toString());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());

        ExecutorService service = Executors.newFixedThreadPool(5);
        List.of(1, 2, 3, 4, 5, 6, 7, 8).forEach(i -> service.submit(() -> System.out.println(i)));
        service.shutdownNow();
        System.out.println(service.isTerminated());*/

        ExecutorService service = Executors.newFixedThreadPool(16);
        Callable<String> task = () -> {
            Thread.sleep(1);
            return "Done";
        };

        // добавляем в очередь на выполнение 10 тыс. заданий
        List<Future<String>> futures1 = IntStream.range(0, 10_000)
                .mapToObj(i -> service.submit(task))
                .collect(Collectors.toList());
        System.out.printf("На выполнение отправлено %d заданий.%n", futures1.size());

        // пробуем закрыть
        service.shutdown();
        // ждем окончания работы 100 миллисекунд
        if (service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
            System.out.println("Все задания выполнены!");
        } else {
            // принудительно останавливаем
            List<Runnable> notExecuted = service.shutdownNow();
            System.out.printf("Так и не запустилось %d заданий.%n", notExecuted.size());
        }

        System.out.printf("Всего выполнено %d заданий.%n", futures1.stream().filter(Future::isDone).count());


    }

    public static class MyRunnable implements Callable<Void> {

        @Override
        public Void call() {
// Добавляем 2 задержки времени. При остановке ExecutorService увидим какая из них отрабатывает при попытке остановить выполнение задачи
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println("sleep 1: " + e.getMessage());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("sleep 2: " + e.getMessage());
            }
            System.out.println("done");
            return null;
        }
    }
}
