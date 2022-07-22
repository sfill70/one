package fixedThreadExecutorWork;

public class PlayingFieldNoSingleton {
     int SIZE = 9;
    Cell[][] arrayCell = new Cell[SIZE][SIZE];

    public PlayingFieldNoSingleton() {
        initialization();
    }

    void initialization() {
        for (int i = 0; i < this.arrayCell.length; i++) {
            for (int j = 0; j < this.arrayCell[0].length; j++) {
                this.arrayCell[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getArrayCell() {
        return arrayCell;
    }

    public void setArrayCell(Cell[][] arrayCell) {
        this.arrayCell = arrayCell;
    }

    public void add(int x, int y, String st) {
        arrayCell[x][y].getSetString().add(st);
    }
}
