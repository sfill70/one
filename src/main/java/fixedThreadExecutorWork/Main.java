package fixedThreadExecutorWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cell[][] arrayCell = new Cell[9][9];

        for (int i = 0; i < arrayCell.length; i++) {
            for (int j = 0; j < arrayCell[0].length; j++) {
                arrayCell[i][j] = new Cell(i, j);
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Worker> workerList = new ArrayList<>();
        for (int i = 0; i < 9; i = i + 3) {
            Cell[][] arrayCellTmp = new Cell[3][arrayCell.length];
            for (int j = 0; j < 3; j++) {
                arrayCellTmp[j] = Arrays.copyOf(arrayCell[i + j], arrayCell.length);
            }
            Worker worker = new Worker(arrayCellTmp);
            executorService.execute(worker);
            workerList.add(worker);
        }
        List<Runnable> list = executorService.shutdownNow();
//        System.out.println(Arrays.deepToString(arrayCell));
//        executorService.shutdown();
        Thread.sleep(200);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(Arrays.deepToString(arrayCell));

        System.out.println(executorService.isTerminated());
        System.out.println(executorService.isShutdown());

//        Thread.sleep(5000);
//        Останавливается без этого
        if (executorService.isShutdown() && executorService.isTerminated()) {
            for (Worker worker : workerList
            ) {
                worker.stop();
                System.out.println("Stop");
                System.out.println(list);
                worker.stop();
            }
        }
       /* for (Worker worker : workerList
        ) {
            worker.stop();
        }*/


       /* Cell[][] arrayCellPart1 = new Cell[3][9];
        Cell[][] arrayCellPart2 = new Cell[3][9];
        Cell[][] arrayCellPart3 = new Cell[3][9];
       for (int i = 0; i < 3; i++) {
            System.out.println(i);
            arrayCellPart1[i] = Arrays.copyOf(arrayCell[i], arrayCell.length);
            arrayCellPart2[i] = Arrays.copyOf(arrayCell[i + 3], arrayCell.length);
            arrayCellPart3[i] = Arrays.copyOf(arrayCell[i + 6], arrayCell.length);
        }
        System.out.println(Arrays.deepToString(arrayCellPart3));

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Worker(arrayCellPart1));
        executorService.execute(new Worker(arrayCellPart2));
        executorService.execute(new Worker(arrayCellPart3));
        List<Runnable> list = executorService.shutdownNow();*/
    }
}
