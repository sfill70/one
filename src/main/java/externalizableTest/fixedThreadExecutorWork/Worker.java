package externalizableTest.fixedThreadExecutorWork;

public class Worker implements Runnable {
    static int count;
    Cell[][] arrayCell;
    String name;

    public Worker(Cell[][] arrayCell) {
        this.arrayCell = arrayCell;
        this.name = "Worker-" + count;
    }

    @Override
    public void run() {
        System.out.println("Start - " + name);
        if (arrayCell.length > 0) {
            for (int i = 0; i < arrayCell.length; i++) {
                for (int j = 0; j < arrayCell[0].length; j++) {
                    System.out.println(arrayCell[i][j]);
                }
            }
           /* for (Cell[] cells : arrayCell) {
                for (int j = 0; j < arrayCell[0].length; j++) {
                    System.out.println(cells[j]);
                }
            }*/
        }
        System.out.println("Stop - " + name);
    }
}

