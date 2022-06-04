import java.util.*;
//Ход конем, сощитать количество ходов конем от одной шахматной клетки до другой


public class Chess {
   /* public static int knight(String start, String  finish) {
        int[] add= {1,2,2,1,-1,-2,-2,-1,1,2};
        HashSet<String> st = new HashSet<String>();
        st.add(start);

        int L=0;
        while(true) {
            HashSet<String> next = new HashSet<String>();
            L++;
            for(String s:st) {
                for(int i=0;i<8;i++) {
                    String temp= String.format("%c%c",s.charAt(0)+add[i],s.charAt(1)+add[i+2]);
                    if(temp.charAt(0)>=97&&temp.charAt(0)<=104
                            &&temp.charAt(1)>=49&&temp.charAt(1)<=56) {
                        next.add(temp);
                        if(temp.equals(finish)) {
                            return L;
                        }
                    }
                }
            }
            st=next;
        }
    }*/

    //размер игрового поляж
    static int n = 8;
    final static int[] MOVE_X = {-2, -2, -1, -1, 1, 1, 2, 2};
    final static int[] MOVE_Y = {-1, 1, -2, 2, -2, 2, -1, 1};
    //Если размер игрового поля > 8 увеличить длинну новыми буквами
    final static String COORDINATE_X = "abcdefgh";

    public static void main(String[] args) {
        System.out.println(knight("a4", "g8"));

    }


    public static int knight(String start, String finish) {
        if (start.length() > 2 || finish.length() > 2) {
            throw new ArithmeticException();
        }
        Point begin = getPoint(start);
        Point end = getPoint(finish);
        int[][] fieldArray = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fieldArray[i][j] = -1;
            }
        }
        fieldArray[begin.x][begin.y] = 0;
        ArrayDeque<Point> moveDengue = new ArrayDeque<Point>();
        moveDengue.add(begin);
        while (!moveDengue.isEmpty()) {
            Point cur = moveDengue.pollFirst();
            for (int i = 0; i < 8; i++) {
//                print(mas);
                int x = cur.x + MOVE_X[i];
                int y = cur.y + MOVE_Y[i];
                if (correct(x, y, n) && fieldArray[x][y] == -1) {
                    fieldArray[x][y] = fieldArray[cur.x][cur.y] + 1;
                    if ((end.x == x && end.y == y)) {
//                        System.out.println(fieldArray[end.x][end.y]);
                        return fieldArray[end.x][end.y];
                    }
                    moveDengue.add(new Point(x, y));
                }
            }
        }
        return -1;
    }


    static class Point {
        int x;
        int y;

        Point(int X, int Y) {
            x = X;
            y = Y;
        }
    }

    static Point getPoint(String st) {
        int x = COORDINATE_X.indexOf(st.charAt(0));
        int y = Integer.parseInt(st.substring(1)) - 1;
        Point point = new Point(x, y);
        if (!correct(point.x, point.y, n)) {
            throw new ArithmeticException();
        }
        return point;
    }
    static boolean correct(int x, int y, int n) {
        if (x < 0 || y < 0)
            return false;
        return x < n && y < n;
    }

    /*public static int knight(String...pos) {
        int[][] ab=Stream.of(pos).map(s->new int[]{"abcdefgh".indexOf(s.charAt(0)),s.charAt(1)-48}).toArray(int[][]::new);
        int[] dxy=IntStream.range(0,2).map(i->Math.abs(ab[0][i]-ab[1][i])).sorted().toArray();
        if(dxy[0]==0&&dxy[1]==1) return 3;
        if(dxy[0]==2&&dxy[1]==2||dxy[0]==1&&dxy[1]==1&&(pos[0]+pos[1]).matches(".*?(a1|h1|a8|h8).*")) return 4;
        int delta=dxy[1]-dxy[0];
        return delta-2*(int)Math.floor(1.0*(delta-dxy[0])/(dxy[0]>delta?3:4));
    }*/
}
