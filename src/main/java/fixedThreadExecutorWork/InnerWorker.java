package fixedThreadExecutorWork;

public class InnerWorker implements Runnable {

    Cell cell;
    PlayingFieldNoSingleton playingField;
//    PlayingField playingField;

    public InnerWorker(Cell cell, PlayingFieldNoSingleton pf) {
        this.cell = cell;
        this.playingField = pf;
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
        int size = playingField.getArrayCell()[0].length;
//        y = (8 - (7 - y)) % 9;
        y = (size - 1 - (size - 2 - y)) % size;
//        System.out.println(w + " - " + y);
//        playingField.getArrayCell()[x][y].add( cell.toString());
        //Можно без if условие всегда выполняется
        if (0 <= y && y < size) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            playingField.add(x, y, cell.toString());
            playingField.getArrayCell()[x][y].add( cell.toString());
        }

        System.out.println("Stop InnerWorker-" + Thread.currentThread().getName());
    }
}
