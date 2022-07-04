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
        int x = cell.getX();
        int y = cell.getY();
        Integer w = y;
        for (int i = 1; i <= 10; i++) {
            cell.setX(cell.getX() + 10);
            cell.setY(cell.getY() + 10);
        }
        y = (8 - (7 - y)) % 9;
        System.out.println(w + " - " + y);
        if (0 <= y && y < 9) {
            PlayingField.add(x, y, cell.toString());
        }

        System.out.println("Stop InnerWorker-" + Thread.currentThread().getName());
    }
}
