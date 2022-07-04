package fixedThreadExecutorWork;

public class InnerWorker implements Runnable {

    Cell cell;

    public InnerWorker(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            cell.setX(cell.getX() + 10);
            cell.setY(cell.getY() + 10);
        }
        System.out.println("Stop InnerWorker-" + Thread.currentThread().getName());
    }
}
