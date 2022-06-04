import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;

public class CodeWar3 {

    public static void main(String[] args) {

        System.out.println(pigIt("Pig latin is cool ![]"));

        String str = "I2 Pig latin is cool ![]";
        str = str.replaceAll("(\\w)(\\w*)", "$2$1ay");
        str = str.replaceAll("(\\w)(\\p{Alpha}*)", "$2$1ay");
        System.out.println(str);
        String bin = "10000000001000000000101000000001";
        String bin1 = "11000000010";
        System.out.println(longToIP(2149583361L));
        System.out.println(longToIP(0));
        System.out.println(longToIP(1));

        System.out.println(Integer.parseInt("10000000", 2));
        String bin2 = new StringBuilder(bin1).reverse().toString();
        String[] stArr = bin1.split("(?<=\\G.{1,8}+)");
        String[] stArr1 = bin2.split("(?=.{8}\\G)");
        System.out.println(Arrays.toString(stArr));
        System.out.println(Arrays.toString(stArr1));

        System.out.println(factors(86240));
        System.out.println(factors(77433082));

        System.out.println(getPeaks(new int[]{3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 2, 2, 1}));
        System.out.println(getPeaks(new int[5]));

        int[][] array = new int[5][5];
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
//                array[i][j]=i+j+2;
                array[i][j] = r.nextInt(100);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(determinant(array));

        System.out.println(isInteresting(1111, new int[]{1000, 3000}));
        System.out.println(isInteresting(6111, new int[]{1000, 3000}));
        System.out.println(isInteresting(11208, new int[]{1337, 256}));
        System.out.println(isInteresting(123321, new int[]{1000, 10000000}));
        System.out.println(isInteresting(12321, new int[]{1000, 10000000}));
        System.out.println(isInteresting(567890, new int[]{1000, 10000000}));
        System.out.println(isInteresting(6543210, new int[]{1000, 10000000}));
        System.out.println(isInteresting(11209, new int[]{1337, 256}));
        System.out.println(isInteresting(11211, new int[]{1337, 256}));
        System.out.println(isInteresting(1337, new int[]{1337, 256}));


    }

    /*Переместите первую букву каждого слова в конец, а затем добавьте «ay» в конец слова.
    Оставьте знаки препинания нетронутыми.
Примеры
pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
pigIt('Hello world !');     // elloHay orldway !*/

    /*
    * public static String pigIt(String str) {
        return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
    }*/

    /*public static String pigIt(String str) {
        return Arrays.stream(str.trim().split(" "))
                .map(v -> v.matches("[a-zA-Z]+") ? v.substring(1).concat(v.substring(0, 1)).concat("ay") : v)
                .collect(Collectors.joining(" "));
    }*/
    /* public static String pigIt(String str) {
        return Arrays.stream(str.split(" ")).map(PigLatin::pigify).collect(Collectors.joining(" "));
    }

    private static String pigify(String word){
        return word.length() > 1 || Character.isLetter(word.charAt(0)) ? word.substring(1) + word.charAt(0) + "ay" : word;

    }*/

   /* public class PigLatin {
        private static final Pattern regex = Pattern.compile("(\\w)(\\w*)");

        public static String pigIt(String str) {
            return regex.matcher(str).replaceAll("$2$1ay");
        }*/

    /*public static String pigIt(String str) {
        if (str.isEmpty()) {
            return "";
        }
        str = str.trim();
        String[] input = str.split("\\s+");
        String[] output = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            String st = input[i];
            String out = "";
            if (st.matches("[^\\w]+")) {
                out = st;
                output[i] = out;
                continue;
            }
            for (int j = 0; j < st.length(); j++) {
                out = out + st.charAt((j + 1) % st.length());
            }
            out = out + "ay";
            output[i] = out;
        }
        return String.join(" ", output);
    }*/

    public static String pigIt(String str) {
        if (str.isEmpty()) {
            return "";
        }
        str = str.trim();
        String[] input = str.split("\\s+");
        String[] output = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            String st = input[i];
            String out = "";
            if (st.matches("[^\\w]+")) {
                out = st;
                output[i] = out;
                continue;
            }
            out = st.substring(1) + st.charAt(0) + "ay";
            output[i] = out;
        }
        return String.join(" ", output);
    }

    /*Возьмите следующий адрес IPv4: 128.32.10.1.
Этот адрес имеет 4 октета, где каждый октет представляет собой один байт (или 8 бит).
1-й октет 128 имеет двоичное представление: 10000000
2-й октет 32 имеет двоичное представление: 00100000.
3-й октет 10 имеет двоичное представление: 00001010.
4-й октет 1 имеет двоичное представление: 00000001.
Итак, 128.32.10.1 == 10000000.00100000.00001010.00000001
Поскольку приведенный выше IP-адрес имеет 32 бита, мы можем представить его как 32-битное
число без знака: 2149583361.
Завершите функцию, которая принимает 32-битное число без знака и возвращает строковое п
редставление его IPv4-адреса.
Примеры
2149583361 ==> "128.32.10.1"
32 ==> "0.0.0.32"
0 ==> "0.0.0.0"*/

    /*public class Kata {
  public static String longToIP(long ip) {
    return String.format("%d.%d.%d.%d", ip>>>24, (ip>>16)&0xff, (ip>>8)&0xff, ip&0xff);
  }
}*/

    /*public static String longToIP(long ip) {
    String[] e = new String[4];
    int i = 4;
    while (i-- != 0) {
      e[i] = "" + (ip % 256);
      ip /= 256;
    }
    return String.join(".",e);
  }*/

    public static String longToIP(long ip) {
        String LongToStBin = Long.toString(ip, 2);
        LongToStBin = new StringBuilder(LongToStBin).reverse().toString();
        String[] reverseStringArray = LongToStBin.split("(?<=\\G.{8})");
        String[] binStrArray = new String[]{"0", "0", "0", "0"};
        for (int i = 0; i < reverseStringArray.length; i++) {
            if (i < 4) {
                binStrArray[3 - i] = Integer.toString(Integer.parseInt(new StringBuilder(reverseStringArray[i])
                        .reverse().toString(), 2));
            }
        }
        return String.join(".", binStrArray); // do it!
    }

    /*Для положительного числа n > 1 найдите разложение n на простые множители. Результатом будет строка следующего вида:

  "(p1**n1)(p2**n2)...(pk**nk)"
с p (i) в порядке возрастания и n (i) пустым, если n (i) равно 1.

Пример: n = 86240 должно возвращать "(2**5)(5)(7**2)(11)"*/

    /* public static String factors(int lst) {
        String result = "";
        for (int fac = 2; fac <= lst; ++fac) {
            int count;
            for (count = 0; lst % fac == 0; ++count) {
                lst /= fac;
            }
            if (count > 0) {
                result += "(" + fac + (count > 1 ? "**" + count : "") + ")";
            }
        }
        return result;
    }*/

    public static String factors(int n) {
        if (n <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();

        int tmp = n;
        int count = 0;
        while (tmp % 2 == 0) {
            count++;
            tmp = tmp / 2;
        }
        writeSb(stringBuilder, 2, count);
        for (int i = 3; i <= n; i = i + 2) {
            count = 0;
            while (tmp % i == 0) {
//                System.out.println(i);
                count++;
                tmp = tmp / i;
            }
            writeSb(stringBuilder, i, count);
        }
        return stringBuilder.toString();
    }

    private static void writeSb(StringBuilder stringBuilder, int i, int count) {
        if (count != 0) {
            if (count == 1) {
                stringBuilder.append("(").append(i).append(")");
            } else {
                stringBuilder.append("(").append(i).append("**").append(count).append(")");
            }
        }
    }

    /*В этом ката вы напишете функцию, которая возвращает позиции и значения «пиков»
    (или локальных максимумов) числового массива.
Например, массив arr = [0, 1, 2, 5, 1, 0] имеет пик в позиции 3 со значением 5
(поскольку arr[3] равно 5).
Вывод будет возвращен как ``Map<String,List> с двумя парами ключ-значение: "pos" и "peaks".
Если в заданном массиве нет пика, просто верните {"pos" => [], "peaks" => []}`.
Пример: pickPeaks([3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3]) должен возвращать {pos: [3, 7],
Peaks: [6, 3]} ( или эквивалент на других языках)
Все входные массивы будут действительными целочисленными массивами (хотя они могут быть пустыми),
поэтому вам не нужно будет проверять ввод.
Первый и последний элементы массива не будут считаться пиками (в контексте математической функции мы
не знаем, что находится после, а что до, и, следовательно, мы не знаем, пик это или нет).
Также остерегайтесь плато!!! [1, 2, 2, 2, 1] имеет пик, а [1, 2, 2, 2, 3] и [1, 2, 2, 2, 2] — нет.
В случае плато-пика, пожалуйста, верните только положение и значение начала плато.
Например: pickPeaks([1, 2, 2, 2, 1]) возвращает {pos: [1],peaks: [2]} (или эквивалент на других языках).
put("pos",   put("pos",   Arrays.stream(p1).boxed().collect(Collectors.toList()));
             put("peaks", Arrays.stream(p2).boxed().collect(Collectors.toList()));
*/

    /*public static Map<String,List<Integer>> getPeaks(int[] arr) {

        Map<String, List<Integer>> result = new HashMap<>();
        result.put("pos", new ArrayList<>());
        result.put("peaks", new ArrayList<>());

        int peakPos = 0; // the position of the peak
        boolean increasing = false; // test to see if the numbers keep increasing

        for (int x = 1 ; x < arr.length ; x++) {
            if (arr[x-1] < arr[x]) { // if the numbers are still increasing
                increasing = true;
                peakPos = x;
            }
            if (increasing && arr[x-1] > arr[x]) { // if it has been increasing but is not anymore
                increasing = false;
                result.get("pos").add(peakPos);
                result.get("peaks").add(arr[peakPos]);
            }
        }
        return result;
    }*/

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Map<String, List<Integer>> result = new HashMap<>();
        List<Integer> posList = new ArrayList<>();
        List<Integer> peaksList = new ArrayList<>();
        int pos = -1;
        for (int i = 1; i < arr.length; i++) {
            if (i < arr.length - 1 && arr[i] > arr[i - 1] && arr[i] >= arr[i + 1]) {
                pos = i;
            }
            if (arr[i] < arr[i - 1] && pos > 0) {
                posList.add(pos);
                peaksList.add(arr[pos]);
                pos = -1;
            }
        }
        result.put("pos", posList);
        result.put("peaks", peaksList);
        return result;
    }

    /*Напишите функцию, которая принимает квадратную матрицу (массив N x N 2D) и возвращает
    определитель матрицы.*/

    /* public static int determinant(int[][] m) {
        int d = 0, size = m.length;
        if (size == 1) return m[0][0];

        for (int n = 0 ; n < size ; n++) {
            int[][] newM = new int[size-1][size-1];
            for (int x = 1 ; x < size ; x++) for (int y = 0 ; y < size ; y++) {
                if (y == n) continue;
                newM[x-1][y + (y>n ? -1 : 0)] = m[x][y];
            }
            d += (n%2!=0 ? -1 : 1) * m[0][n] * determinant(newM);
        }
        return d
        */

    /*
    public class Matrix{
  public static int determinant(int[][] m){
    if(m.length == 1) return m[0][0];
    int d = 0, k, l;
    for(int j = 0; j < m.length; j++){
      int[][] n = new int[m.length-1][m.length-1];
      for(k = 0; k < m.length; k++) for(l = 0; l < m.length; l++)
        if(k != 0 && l != j) n[k-((k>0)?1:0)][l-((l>j)?1:0)] = m[k][l];
      d += ((j % 2 == 0)?1:-1) * m[0][j] * determinant(n);
    }
    return d;
  }
}*/

    public static int determinant(int[][] matrix) {

        int n = matrix.length;
        if (n == 1) return matrix[0][0];
        int ans = 0;
        int B[][] = new int[n - 1][n - 1];
        int l = 1;
        for (int i = 0; i < n; ++i) {
            int x = 0, y = 0;
            for (int j = 1; j < n; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (i == k) continue;
                    B[x][y] = matrix[j][k];
                    ++y;
                    if (y == n - 1) {
                        y = 0;
                        ++x;
                    }
                }
            }
            ans += l * matrix[0][i] * determinant(B);
            l *= (-1);
        }
        return ans;
    }

    public static int isInteresting(int number, int[] awesomePhrases) {

        boolean isPoly = true;
        HashSet<Integer> set = new HashSet<>();
        for (int i:awesomePhrases) {
            set.add(i);
        }

        if(set.contains(number)){
            return 2;
        }

        if (number < 100 || number > 1000000000) {
            return 0;
        }
        if (awesomePhrases.length != 2 || awesomePhrases[0] == 0 || awesomePhrases[1] == 0) {
            return 0;
        }

        int min = Math.min(awesomePhrases[0], awesomePhrases[1]);
        int max = Math.max(awesomePhrases[0], awesomePhrases[1]);

        /*if(number < min || number > max){
            return 0;
        }*/

        List<Integer> numbers = getIntegers(number);
        Integer x = getInteger(numbers, awesomePhrases);
        if (x != null) {
            return (int) x;
        }


        for (int i = 0; i < numbers.size() / 2; i++) {
            if (numbers.get(i) != numbers.get(numbers.size() - 1 - i)) {
                isPoly = false;
                break;
            }
        }
        if (isPoly) {
            return 2;
        }

        for (int i = number + 1; i < number + 3; i++) {
            String st = Integer.toString(i);
            isPoly = true;
            for (int j = 0; j < st.length() / 2; j++) {
                if (st.charAt(j) != st.charAt(st.length() - j - 1)) {
                    isPoly = false;
                    break;
                }
            }
            if (isPoly) {
//                System.out.println(isPoly);
                return 1;
            }
        }


        for (int j = number + 1; j < number + 3; j++) {
            numbers = getIntegers(j);
            x = getInteger(numbers, awesomePhrases);
            if (x != null && x == 2) {
                return 1;
            }


        }
        return 0;
    }

    private static Integer getInteger(List<Integer> numbers, int[] awesomePhrases) {
        boolean isDec = true;
        boolean isIncrement = true;
        boolean isDecrement = true;
        boolean isCon = true;
//        System.out.println(number);
        for (int i = 1; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i) + 1 + " / " + numbers.get(i - 1));
            if (numbers.get(i) != 0) {
                isDec = false;
            }
            if ((10 + (numbers.get(i) - 10) % 10) != 11 + (numbers.get(i - 1) - 10) % 10) {
                isIncrement = false;
            }
            if (!Objects.equals(numbers.get(i) + 1, numbers.get(i - 1))) {
                isDecrement = false;
            }
        }
        if (isDec || isDecrement || isIncrement) {
//            System.out.println("isDec = " + isDec + " / isIncrement = " + isIncrement + " / isDecrement = " + isDecrement);
            return 2;
        }
        return null;
    }

    private static List<Integer> getIntegers(int number) {
        List<Integer> numbers = new ArrayList<>();
        int tmp = number;
        while (tmp > 0) {
            numbers.add(0, tmp % 10);
            tmp = tmp / 10;
        }
        return numbers;
    }


   /* public static int isInteresting(int number, int[] awesomePhrases) {
        boolean isDec = true;
        boolean isIncrement = true;
        boolean isDecrement = true;
        boolean isPoly = true;
        if (number < 100 || number > 1000000000) {
            return 0;
        }
        if (awesomePhrases.length != 2 || awesomePhrases[0] == 0 || awesomePhrases[1] == 0) {
            return 0;
        }

        int min = Math.min(awesomePhrases[0], awesomePhrases[1]);
        int max = Math.max(awesomePhrases[0], awesomePhrases[1]);

        *//*if(number < min || number > max){
            return 0;
        }*//*

        List<Integer> numbers = new ArrayList<>();
        int tmp = number;
        while (tmp > 0) {
            numbers.add(0, tmp % 10);
            tmp = tmp / 10;
        }
//        System.out.println(number);
        for (int i = 1; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i) + 1 + " / " + numbers.get(i - 1));
            if (numbers.get(i) != 0) {
                isDec = false;
            }
            if ((10 + (numbers.get(i) - 10) % 10) != 11 + (numbers.get(i - 1) - 10) % 10) {
                isIncrement = false;
            }
            if (!Objects.equals(numbers.get(i) + 1, numbers.get(i - 1))) {
                isDecrement = false;
            }
        }
        if (isDec || isDecrement || isIncrement) {
//            System.out.println("isDec = " + isDec + " / isIncrement = " + isIncrement + " / isDecrement = " + isDecrement);
            return 2;
        }

        for (int i = 0; i < numbers.size() / 2; i++) {
            if (numbers.get(i) != numbers.get(numbers.size() - 1 - i)) {
                isPoly = false;
                break;
            }
        }
        if (isPoly) {
            return 2;
        }

        for (int i = number + 1; i < number + 3; i++) {
            String st = Integer.toString(i);
            isPoly = true;
            for (int j = 0; j < st.length() / 2; j++) {
                if (st.charAt(j) != st.charAt(st.length() - j - 1)) {
                    isPoly = false;
                    break;
                }
            }
            if (isPoly) {
//                System.out.println(isPoly);
                return 1;
            }
        }


        for (int j = number + 1; j < number + 3; j++) {
            isDec = true;
            isIncrement = true;
            isDecrement = true;
            numbers = new ArrayList<>();
            tmp = j;
            while (tmp > 0) {
                numbers.add(0, tmp % 10);
                tmp = tmp / 10;
            }
            for (int i = 1; i < numbers.size(); i++) {
//            System.out.println(numbers.get(i) + 1 + " / " + numbers.get(i - 1));
                if (numbers.get(i) != 0) {
                    isDec = false;
                }
                if ((10 + (numbers.get(i) - 10) % 10) != 11 + (numbers.get(i - 1) - 10) % 10) {
                    isIncrement = false;
                }
                if (!Objects.equals(numbers.get(i) + 1, numbers.get(i - 1))) {
                    isDecrement = false;
                }
            }
            if (isDec || isDecrement || isIncrement) {
//            System.out.println("isDec = " + isDec + " / isIncrement = " + isIncrement + " / isDecrement = " + isDecrement);
                return 1;
            }

        }
        return 0;
    }*/


}
