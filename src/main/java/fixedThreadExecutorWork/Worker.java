package fixedThreadExecutorWork;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker implements Runnable {
    static int count;
    String name;
    Cell[][] arrayCell;

    PlayingFieldNoSingleton playingField;

    /*PlayingField playingField;*/
    ExecutorService executorService = Executors.newFixedThreadPool(6);

    public Worker(Cell[][] arrayCell,PlayingFieldNoSingleton pf) {
        this.arrayCell = arrayCell;
        this.name = "Worker-" + count;
        this.playingField = pf;
    }

    @Override
    public void run() {
        System.out.println("Start-" + Thread.currentThread().getName());
        for (int i = 0; i < arrayCell.length; i++) {
            for (int j = 0; j < arrayCell[0].length; j++) {
//                System.out.println(arrayCell[i][j]);
                executorService.execute(new InnerWorker(arrayCell[i][j], playingField));
            }
        }
//        stop();
        executorService.shutdown();
        System.out.println("Stop-" + Thread.currentThread().getName());
    }

    void stop (){
        List<Runnable>runnableList = executorService.shutdownNow();
        System.out.println(runnableList);

    }
}
