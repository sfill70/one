package codeWars;

import java.util.*;
import java.util.stream.Collectors;
//import org.apache.commons.lang3.text.WordUtils;

public class CodeWar2 {

    public static void main(String[] args) {

        System.out.println(camelCase(" camel case word"));
        System.out.println(camelCase("camel case method"));
        System.out.println(camelCase("say hello "));
        System.out.println(camelCase("z"));
        System.out.println(camelCase(""));
        System.out.println(check("The quick brown fox jumps over the lazy dog."));
        System.out.println(check("You shall not pass!"));
        System.out.println(check("You"));
        List<Integer> list = sqInRect(5, 3);
        System.out.println(list);
        System.out.println(digPow(695, 2));
        System.out.println(digPow(46288, 3));
        System.out.println(digPow(92, 1));
        System.out.println(order("is2 Thi1s T4est 3a"));
        System.out.println(order("4of Fo1r pe6ople g3ood th5e the2"));
        System.out.println(order(""));
        System.out.println(sumDigPow(1, 100));
        System.out.println(sumDigPow(10, 150));
        System.out.println(sumDigPow(134,135));
        System.out.println(sumDigPow(50,50000));


    }

    /*
     * Напишите простой метод .camelCase
     * (функция camel_case в PHP, CamelCase в C# или camelCase в Java) для строк.
     * Во всех словах первая буква должна быть заглавной без пробелов.*/

   /* public static String camelCase(String str) {
        // your code here
        String[] strings = str.split(" ");
        StringBuilder stringBuilder=new StringBuilder();
        for (String string : strings) {
            if (string.length()>0){
                stringBuilder.append(string.replaceFirst(string.substring(0, 1), string.substring(0, 1).toUpperCase()));
            }

        }
        return new String(stringBuilder);

    }*/

    /*public static String camelCase(String str) {
        return (str == null || str.isEmpty()) ? "" : Arrays.stream(str.trim().split("\\s+"))
                .map(st -> st.replaceFirst(st.substring(0, 1), st.substring(0, 1).toUpperCase()))
                .collect(Collectors.joining());
    }*/


    public static String camelCase(String str) {
        if (str.isEmpty()) {
            return "";
        }
        str = str.trim();
        String[] strings = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String st : strings) {
            st = st.replaceFirst(st.substring(0, 1), st.substring(0, 1).toUpperCase());
            sb.append(st);
        }
        return sb.toString();
    }

    /*Панграмма — это предложение, в котором каждая буква алфавита встречается хотя бы по одному разу.
    Например, предложение «Быстрая коричневая лиса перепрыгивает через ленивую собаку» является панграммой,
    потому что в нем хотя бы один раз используются буквы от A до Z (регистр значения не имеет).

Учитывая строку, определите, является ли она панграммой.
Возвращает True, если это так, False, если нет. Не обращайте внимания на цифры и знаки препинания.*/


    /*
    * class PangramChecker {
    boolean check(final String sentence) {
        return sentence.chars()
            .filter(Character::isLetter)
            .map(Character::toLowerCase)
            .distinct()
            .count() == 26;
    }
}*/
    public static boolean check(String sentence) {
        boolean isCheck = true;
        sentence = sentence.toLowerCase();
        for (char i = 'a'; i <= 'z'; i++) {
            if (!sentence.contains(String.valueOf(i))) {
                isCheck = false;
            }
        }
        return isCheck;
    }

    /*Рисунок ниже дает представление о том, как разрезать заданный «настоящий»
     прямоугольник на квадраты («настоящий» прямоугольник означает, что два измерения различны).*/

    /*public static List<Integer> sqInRect(int l, int w) {
    if(l == w)
      return null;
    else if(l > w)
      return sqInRect(w, l);

    List<Integer> answer = new ArrayList<>();
    answer.add(w);
    answer.addAll(sqInRect(w, l - w));
    return answer;
  }*/

    public static List<Integer> sqInRect(int lng, int wdth) {
        List<Integer> result = new ArrayList<>();
        if (lng == wdth) {
            return null;
        }
        int max = Math.max(lng, wdth);
        int min = Math.min(lng, wdth);
        while (min > 0) {
            result.add(min);
            max = max - min;
            int tmp = Math.max(max, min);
            min = Math.min(max, min);
            max = tmp;
        }
        return result;
    }

    /*Некоторые числа обладают забавными свойствами. Например:

89 --> 8¹ + 9² = 89 * 1
695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2
46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
Даны положительное целое число n, записанное в виде abcd... (a, b, c, d... являются цифрами),
и положительное целое число p
мы хотим найти положительное целое число k, если оно существует, такое, что сумма цифр n, взятых в
последовательных степенях p, равна k * n.
Другими словами:
Существует ли целое число k, такое как: (a ^ p + b ^ (p+1) + c ^ (p+2) + d ^ (p+3) + ...) = n * k
Если это так, мы вернем k, если нет, вернем -1.*/

    /* public static long digPow(int n, int p) {
    String intString = String.valueOf(n);
    long sum = 0;
    for (int i = 0; i < intString.length(); ++i, ++p)
      sum += Math.pow(Character.getNumericValue(intString.charAt(i)), p);
    return (sum % n == 0) ? sum / n : -1;
  }*/

    /*public static long digPow(int n, int p) {
    int[] digits = String.valueOf(n).chars().map(Character::getNumericValue).toArray();
    int sum = IntStream.range(0, digits.length).map(i -> (int) Math.pow(digits[i], i + p)).sum();
    return sum % n == 0 ? sum / n : -1;
  }*/
    public static long digPow(int n, int p) {
        int length = String.valueOf(n).length();
        long result = 0;
        int m = n;
        while (m > 0) {
            result = result + (long) Math.pow(m % 10, p + length - 1);
            m = m / 10;
            length--;
        }
        if (result % n == 0) {
            return result / n;
        } else {
            return -1;
        }
    }
    /*слово в строке будет содержать одно число. Это число и есть позиция, которую должно занимать слово в результате.
Примечание. Цифры могут быть от 1 до 9. Таким образом, первым словом будет 1 (а не 0).
Если входная строка пуста, вернуть пустую строку. Слова во входной строке будут содержать только допустимые последовательные числа.
Примеры
"is2 Thi1s T4est 3a" --> "Thi1s is2 3a T4est"
"4Fo1r pe6ople g3good th2" --> "Fo1r the2 g3od 4thpeople"
"" --> ""*/
/*public static String order(String words) {
    return Arrays.stream(words.split(" "))
      .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
      .reduce((a, b) -> a + " " + b).get();
  }*/

    /* public static String order(String words) {
    return Arrays.stream(words.split(" "))
                 .sorted((a, b) -> {
                    return a.replaceAll("\\D+", "").compareTo(b.replaceAll("\\D+", ""));
                 }).collect(Collectors.joining(" "));
  }*/
    public static String order(String words) {
        if (words.isEmpty() || words.isBlank()) {
            return "";
        }
        words = words.trim();
        String[] stringsArray = words.split("\\s+");
        String[] result = new String[stringsArray.length];
        for (String word : stringsArray) {
            int pos = 0;
            for (char ch : word.toCharArray()) {
                if (Character.isDigit(ch)) {
                    pos = Character.getNumericValue(ch);
                }
            }
            result[pos - 1] = word;
        }
        return String.join(" ", result);
    }

    /*Число 89 — это первое целое число, состоящее более чем из одной цифры, которое соответствует свойству, частично представленному в названии этого ката. Что толку говорить "Эврика"? Потому что эта сумма дает одно и то же число.
Фактически: 89 = 8^1 + 9^2
Следующее число, обладающее этим свойством, — 135.
Посмотрите еще раз на это свойство: 135 = 1^1 + 3^2 + 5^3.
Нам нужна функция для сбора этих чисел, которая может принимать два целых числа a, b, определяющих
диапазон [a, b] (включительно), и выводит список отсортированных чисел в диапазоне,
удовлетворяющем описанному выше свойству.
Давайте рассмотрим некоторые случаи:
sum_dig_pow(1, 10) == [1, 2, 3, 4, 5, 6, 7, 8, 9]
sum_dig_pow(1, 100) == [1, 2, 3, 4, 5, 6, 7, 8, 9, 89]
Если в диапазоне [a, b] таких чисел нет, функция должна вывести пустой список.
sum_dig_pow(90, 100) == []
Наслаждайся этим!!*/

    /*public static List<Long> sumDigPow(long a, long b) {
        return LongStream.rangeClosed(a, b)
          .filter(i -> isValid(i))
          .boxed()
          .collect(Collectors.toList());
    }

    private static boolean isValid(long x){
      String value = Long.toString(x);
      return IntStream.range(0, value.length())
         .mapToDouble(i -> Math.pow(Character.getNumericValue(value.charAt(i)), i + 1))
         .sum() == x;
    }*/

    public static List<Long> sumDigPow(long a, long b) {
        List<Long> numbers = new ArrayList<>();
        for (long i = a; i <= b; i++) {
            int length = String.valueOf(i).length();
            long j = i;
            long result = 0;
            while (j > 0) {
                result = result + (long) Math.pow(j % 10, length);
                j = j / 10;
                length--;
            }
            if (result == i) {
                numbers.add(i);
            }
        }
        return numbers;
    }


}
