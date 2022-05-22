import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class CodeWar {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(arrayDiff(new int[]{1, 2, 2, 2, 3}, new int[]{2})));


        System.out.println(compute(30, 12));
        System.out.println(compute(8, 9));
        System.out.println(compute(1, 1));
        System.out.println(compute(3, 5));


        System.out.println(Arrays.toString(solution("qwertyuio")));
        System.out.println(Arrays.toString(solution("й1укенгшщ")));


        System.out.println(find(new int[]{4, -2, 30, 4, 10, 6, 9, 8}));
        System.out.println(-10 % 2);

        System.out.println(GetSum(0, 3));
        System.out.println(GetSum(200, 400));

        System.out.println(findShort("bitcoin take over the      world maybe who knows perhaps"));

        System.out.println(camelCase("camelCasingTest"));

        System.out.println(countBits(1234));



        System.out.println(findIt(new int[]{20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5}));
        System.out.println(findIt(new int[]{1, 1, 2, -2, 5, 2, 4, 4, -1, -2, 5}));
        System.out.println(findIt(new int[]{20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5}));
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
//            System.out.printf("b= %d c =%d a = %d ! ", b, c, a);
            a = b;
            b = c % a;
//            System.out.printf("b= %d c =%d a = %d", b, c, a);
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

    /* public static String[] solution(String s) {
        //Write your code here
        if(s.length()%2==1) s+="_";
        int n=s.length()/2;


      String[] sub=new String[n];
      for(int i=0;i<n;++i)
      sub[i]=""+s.charAt(i*2)+s.charAt(1+i*2);

      return sub;
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

    /*Вам дан массив (который будет иметь длину не менее 3, но может быть очень большим),
    содержащий целые числа. Массив либо полностью состоит из нечетных целых чисел, либо полностью
    состоит из четных целых чисел, за исключением одного целого числа N. Напишите метод, который
    принимает массив в качестве аргумента и возвращает этот «выброс» N.*/

    /*
    public static int findShort(String s) {
        return Stream.of(s.split(" "))
          .mapToInt(String::length)
          .min()
          .getAsInt();
    }
    */
    static int find(int[] integers) {
        int[] tmp = new int[2];
        for (int i = 0; i < 3; i++) {
            tmp[Math.abs(integers[i] % 2)]++;
        }
        int target = tmp[0] > tmp[1] ? 0 : 1;
        int result;
        for (int num :
                integers) {
            if (Math.abs(num % 2) != target) {
                return num;
            }
        }
        return 0;
    }

    /*Даны два целых числа a и b, которые могут быть положительными или отрицательными,
    найдите сумму всех целых чисел между ними и включая их и верните ее. Если два числа равны,
    верните a или b.

Примечание: a и b не упорядочены!*/

    /* public int GetSum(int a, int b)
  {
    return (a + b) * (Math.abs(a - b) + 1) / 2;
  }*/

    public static int GetSum(int a, int b) {
        if (a == b) {
            return a;
        }
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int sum = 0;
        while (!(min > max)) {
            sum = sum + min;
            min++;
        }
        return sum;
    }

    /*Просто, учитывая строку слов, вернуть длину кратчайшего слова (слов).
    Строка никогда не будет пустой, и вам не нужно учитывать разные типы данных.*/

    public static int findShort(String s) {
        TreeSet<Integer> result = new TreeSet<>();
        String[] strings = s.split("\\s+");
        for (String st :
                s.split("\\s+")) {
            result.add(st.length());
        }
        return result.first();
    }

    /*Завершите решение, чтобы функция разбивала верблюжий регистр, используя пробелы между словами.*/

    /*
    public static String camelCase(String input) {
    String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = Character.isUpperCase(input.charAt(i)) ? output + " " + input.charAt(i) : output + input.charAt(i);
        }
        return output;
  }
    */
    public static String camelCase(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equals(input.substring(i, i + 1).toUpperCase())) {
                result.append(" ");
                result.append(input.charAt(i));
            } else {
                result.append(input.substring(i, i + 1));
            }
        }
        return result.toString();
    }

    /*
    Напишите функцию, которая принимает целое число в качестве входных данных и возвращает
    количество битов, равных единице в двоичном представлении этого числа. Вы можете гарантировать,
    что ввод неотрицательный.

ПриПример: двоичное представление числа 1234 равно 10011010010, поэтому в этом случае функция должна вернуть 5.*/
   /* public static int countBits(int n){

    return Integer.bitCount(n);
  }*/

    /*
     public static int countBits(int n){
    int ret = n % 2;
    while ((n /= 2) > 0) ret += n % 2;
    return ret;
    }
  */
    public static int countBits(int n) {
        int count = 0;
        String st = "";
        while (n > 0) {
            count = count + (n % 2);
//            st = n % 2 + st;
            n = n / 2;
        }
//        System.out.println(st);
        return count;
    }

    /*В этом ката вы должны написать простой декодер азбуки Морзе.*/

   /* public class MorseCodeDecoder {

        public static String decode(String morseCode) {
            return Arrays.stream(morseCode.trim().split("   "))
                    .map(MorseCodeDecoder::decodeWord)
                    .collect(Collectors.joining(" "));
        }

        private static String decodeWord(String word) {
            return Arrays.stream(word.split(" ")).map(MorseCode::get).collect(Collectors.joining());
        }
    }*/

    public static String decode(String morseCode) {
        // your brilliant code here, remember that you can access the preloaded Morse code
        // table through MorseCode.get(code)
//        String st = MorseCode.get(code);
        if (morseCode.isEmpty()) {
            return "";
        }
        String[] words = morseCode.split("\\s{3}");
        StringBuilder text = new StringBuilder();
        for (String w : words) {
            String[] word = w.split("\\s");
            StringBuilder wordSb = new StringBuilder();
            for (String l : word) {
                l.trim();
                if (!l.isEmpty()) {
//                wordSb.append(MorseCode.get(l));
                }
            }
            text.append(wordSb);
            text.append(" ");
        }
        return text.toString().trim();
    }

    /*Дан массив целых чисел, найдите то, которое встречается нечетное количество раз.

Всегда будет только одно целое число, которое встречается нечетное количество раз.

Примеры
[7] должен вернуть 7, потому что это происходит 1 раз (что нечетно).
[0] должен возвращать 0, потому что это происходит 1 раз (что нечетно).
[1,1,2] должен вернуть 2, потому что он встречается 1 раз (что нечетно).
[0,1,0,1,0] должен вернуть 0, потому что он встречается 3 раза (что нечетно).
[1,2,2,3,3,3,4,3,3,3,2,2,1] должен вернуть 4, потому что он появляется 1 раз (что нечетно).*/

    /*public static int findIt(int[] A) {
    int odd=0;
    for (int item: A)
      {
        odd = odd ^ item;// XOR will cancel out everytime you XOR with the same number so 1^1=0 but 1^1^1=1 so every pair should cancel out leaving the odd number out
      }

    return odd;
  }*/

    /*public static int findIt(int[] arr) {
    return stream(arr).reduce(0, (x, y) -> x ^ y);
  }*/
    public static int findIt(int[] a) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (int i : a) {
            if (result.containsKey(i)) {
                result.put(i, result.get(i) + 1);
            } else {
                result.put(i, 1);
            }
        }
        int number = 0;
        for (Integer key : result.keySet()) {
            if (result.get(key) % 2 == 1) {
                number = key;
            }
        }
        return number;
    }
}
