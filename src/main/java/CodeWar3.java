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
        /*System.out.println();
//        2149583361
        System.out.println(Long.toString(2149583361L, 2));
        System.out.println(Integer.parseInt("10000000", 2));
        System.out.println(Integer.parseInt("0", 2));
        double dol = 1f / 6f;
        System.out.println(Math.ceil(dol));
        System.out.println(dol);*/

        /*long dec = (long) Math.pow(2,0)+(long) Math.pow(2,9)+(long) Math.pow(2,11)+(long) Math.pow(2,21)+(long) Math.pow(2,31);
        System.out.println(Math.pow(2,0));
        System.out.println(dec);*/

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

}
