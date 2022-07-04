package fixedThreadExecutorWork;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Cell {
    int x;
    int y;

/*Они все работают по разному */
//    Set<String> setString = new ConcurrentSkipListSet<>();

    Set<String>setString = ConcurrentHashMap.newKeySet();
//    CopyOnWriteArraySet <String>setString = new CopyOnWriteArraySet();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        setString.add(this.toString());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Set<String> getSetString() {
        return setString;
    }

    public void add(String st) {
        setString.add(st);
    }

    public void setSetString(Set<String> setString) {
        this.setString = setString;
    }

    /*public void setSetString(CopyOnWriteArraySet <String>setString ) {
        this.setString = setString;
    }*/

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", setString=" + setString +
                '}';
    }
}
