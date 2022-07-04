package fixedThreadExecutorWork;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker implements Runnable {
    static int count;
    String name;
    Cell[][] arrayCell;
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    public Worker(Cell[][] arrayCell) {
        this.arrayCell = arrayCell;
        this.name = "Worker-" + count;
    }

    @Override
    public void run() {
        System.out.println("Start-" + Thread.currentThread().getName());
        for (int i = 0; i < arrayCell.length; i++) {
            for (int j = 0; j < arrayCell[0].length; j++) {
                System.out.println(arrayCell[i][j]);
                executorService.execute(new InnerWorker(arrayCell[i][j]));
            }
        }
//        stop();
        executorService.shutdown();
        /*for (Cell[] cells : arrayCell) {
            for (int j = 0; j < arrayCell[0].length; j++) {
                System.out.println(cells[j]);
            }
        }*/
        System.out.println("Stop-" + Thread.currentThread().getName());
    }

    void stop (){
        List<Runnable>runnableList = executorService.shutdownNow();
        System.out.println(runnableList);

    }
}
