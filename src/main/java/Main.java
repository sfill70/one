import java.math.BigInteger;
import java.util.*;

public class Main {
    static boolean a = true;
    static boolean b = false;
    static boolean c = false;
    static boolean d = true;

    public static void main(String[] args) {
        System.out.print("«Амиго очень умный»".toLowerCase());
        System.out.println("It's alive! It's alive!");
        System.out.println(booleanExpression(a, b, c, d));
        System.out.println(d ^ a);
        System.out.println(leapYearCount(2000));
        System.out.println(2004);
        System.out.println(doubleExpression(2.5, 3.0, 5.5));
        System.out.println(flipBit(420, 2));
        System.out.println((char) ((int) '\u0068' + 4000));
        System.out.println(isPowerOfTwo(129));
        // сравнение массивов
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        System.out.println(Arrays.equals(array1, array2)); //многомерные массивы Arrays.deepEquals(array1,array2);
        System.out.println("A" + ('\t' + '\u0003'));
        System.out.println(isPalindrome("Madam, I'm Adam!"));
        System.out.println(factorial(10));
        //System.out.println(new int[new int[2].length + new int[3].length].length);
        int[] one = {1, 2, 3};
        int[] two = {1, 3, 4};
        System.out.println(Arrays.toString(mergeArrays(one, two)));

        String[] roles = {"Городничий", "Аммос Федорович", "Артемий Филиппович", "Лука Лукич"};
        String[] textLines = {"Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.", "Аммос Федорович: Как ревизор?", "Артемий Филиппович: Как ревизор?", "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.", "Аммос Федорович: Вот те на!", "Артемий Филиппович: Вот не было заботы, так подай!", "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};
        String st = printTextPerRole(roles, textLines);
        System.out.println(printTextPerRole(roles, textLines));

        System.out.println(Arrays.toString(arrayDiff(new int[]{1, 2, 2, 2, 3}, new int[]{2})));


        System.out.println(compute(30, 12));
        System.out.println(compute(8, 9));
        System.out.println(compute(1, 1));
        System.out.println(compute(3, 5));

        System.out.println(Math.ceil((double) 9 / 2));
        System.out.println(Arrays.toString(solution("qwertyuio")));
        System.out.println(Arrays.toString(solution("й1укенгшщ")));


    }

    public static boolean booleanExpression(boolean a, boolean b, boolean c, boolean d) {
        //return ((a & b) | (a & c) | (a & d) | (b & c) | (b & d) | (c & d)) ;
        return (((a | b) & (c | d)) & ((a ^ b) & (c ^ d))) | (((a | c) & (b | d)) & ((a ^ c) & (b ^ d)));
        //return ((a != b) && (c != d)) || ((a != c) && (b != d));
        // return a ^ b ? c ^ d : a ^ c && b ^ d;
    }

    public static int leapYearCount(int year) {
        return (year / 4 - year / 100 + year / 400);
    }

    public static boolean doubleExpression(double a, double b, double c) {
        return Math.abs(c - a - b) < 0.0001;
    }

    // Реализуйте метод flipBit, изменяющий значение одного бита заданного целого числа на противоположное.

    public static int flipBit(int value, int bitIndex) {
        return value ^ (1 << (bitIndex - 1)); // put your implementation here
    }

    //Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.
    public static boolean isPowerOfTwo(int value) {
        return Integer.bitCount(Math.abs(value)) == 1;
    }

    public static boolean isPalindrome(String text) {
        StringBuilder palindrome = new StringBuilder(text.replaceAll("[^a-zA-Z0-9]", "").toUpperCase());
        return palindrome.toString().equals(palindrome.reverse().toString());
    }

    public static BigInteger factorial(int value) {
        BigInteger factorial = new BigInteger("1");
        for (int i = 1; i <= value; i++) {
            factorial = factorial.multiply(new BigInteger(i + ""));
        }
        return factorial;

        //Тернарый + рекурсия
        // return  value == 0 ? BigInteger.ONE : BigInteger.valueOf(value).multiply(factorial (value-1));
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] arrayMerger = new int[a1.length + a2.length];

        //Короткое решение
        /*int i = 0, j = 0;
        while (i < n || j < m) {
            arrayMerger[i + j] = (i < n && (j == m || a1[i] < a2[j])) ? a1[i++] : a2[j++];
        }*/

        for (int i = 0, j = 0, k = 0; k < arrayMerger.length; k++) {
            if (i > a1.length - 1) {
                int a = a2[j];
                arrayMerger[k] = a;
                j++;
            } else if (j > a2.length - 1) {
                int a = a1[i];
                arrayMerger[k] = a;
                i++;
            } else if (a1[i] < a2[j]) {
                int a = a1[i];
                arrayMerger[k] = a;
                i++;
            } else {
                int b = a2[j];
                arrayMerger[k] = b;
                j++;
            }
        }
        return arrayMerger;
    }

    public static String printTextPerRole(String[] roles, String[] textLines) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < roles.length; i++) {
            result.append(roles[i]);
            result.append(":");
            result.append('\n');
            for (int j = 0; j < textLines.length; j++) {
                if (textLines[j].startsWith(roles[i] + ":")) {
                    result.append(j + 1).append(")");
                    result.append(textLines[j].substring(textLines[j].indexOf(':') + 1));
                    result.append('\n');
                }
            }
            result.append('\n');
        }

        return result.toString();
    }


    public static int[] arrayDiff(int[] a, int[] b) {
        int[] result = new int[0];
        Arrays.sort(b);
        for (int i = 0; i < a.length; i++) {
            if (!(Arrays.binarySearch(b, a[i]) < 0)) {
                System.out.println(Arrays.binarySearch(b, a[i]));
                continue;
            }

            System.out.println(Arrays.binarySearch(b, a[i]) + " !");
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = a[i];
        }
        return result;
    }

    //    Наибольший общий делитель
//    Алгоритм Эвклида
/*static int compute(int x, int y) {
    return x % y == 0 ? y : compute(y, x % y);
}*/
    public static int compute(int a, int b) {
        while (b != 0) {
            int c = a;
            System.out.printf("b= %d c =%d a = %d ! ", b, c, a);
            a = b;
            b = c % a;
            System.out.printf("b= %d c =%d a = %d", b, c, a);
            System.out.println();
        }
        return a;
    }


    /*public static int compute(int x, int y) {

        int min = Math.min(x, y);
        int compute = 1;
        for (int i = 2; i <= min; i++) {
            if (x % i == 0 && y % i == 0) {
                compute = i;
            }
        }
        return compute;
    }*/

    /*Завершите решение так, чтобы оно разбило строку на пары из двух символов. Если строка содержит
    нечетное количество символов, то отсутствующий второй символ последней пары должен быть заменен
    символом подчеркивания ('_').*/

    public static String[] solution(String s) {
        s = (s.length() % 2 == 0) ? s : s + "_";
        return s.split("(?<=\\G.{2})");
    }
    /*public static String[] solution(String s) {
        String[] result = new String[0];
        if (!s.isEmpty()) {
            result = new String[(int) Math.ceil((double) s.length() / 2)];
            for (int i = 0; i < result.length - 1; i++) {
                result[i] = s.substring(0, 2);
                s = s.substring(2);
            }
            if (s.length() % 2 == 0) {
                result[result.length - 1] = s.substring(0, 2);
            } else {
                result[result.length - 1] = s + "_";
            }
        }
        return result;
    }*/
    /*public static String[] solution(String s) {
        s = (s.length() % 2 == 0) ? s : s + "_";
        String[] result = new String[0];
        if (!s.isEmpty()) {
            result = new String[(s.length() / 2)];
            for (int i = 0; i < result.length; i++) {
                result[i] = s.substring(0, 2);
                s = s.substring(2);
            }
        }
        return result;
    }*/

}
