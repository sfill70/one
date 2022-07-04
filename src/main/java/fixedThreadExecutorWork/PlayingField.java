package fixedThreadExecutorWork;

import javax.swing.*;

public class PlayingField {
    private static volatile PlayingField instance;
    static int SIZE = 9;
    static Cell[][] arrayCell = new Cell[SIZE][SIZE];

    public static PlayingField getInstance() {
        if (instance == null) {
            synchronized (PlayingField.class) {
                if (instance == null) {
                    instance = new PlayingField();

                }
            }
        }
        return instance;
    }

    void initialization() {
        for (int i = 0; i < arrayCell.length; i++) {
            for (int j = 0; j < arrayCell[0].length; j++) {
                arrayCell[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getArrayCell() {
        return arrayCell;
    }

    public void setArrayCell(Cell[][] arrayCell) {
        PlayingField.arrayCell = arrayCell;
    }

    public static void add(int x, int y, String st) {
        arrayCell[x][y].getSetString().add(st);
    }
}
