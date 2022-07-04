package codeWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.script.*;
import java.util.regex.*;

public class CodeWar4 {
    public static void main(String[] args) {
        /*System.out.println(solveExpression("-123*-45?=5?088"));
        System.out.println(solveExpression("1+1=?"));*/
        System.out.println(solveExpression("?*11=??"));
        System.out.println(solveExpression("-?56373--9216=-?47157"));

    }

    /*Профессор даст вам простое математическое выражение вида

[число][op][число]=[число]
Он преобразовал все известные ему руны в цифры. Единственные операторы, которые он знает,
это сложение (+), вычитание (-) и умножение (*), так что только они появятся. Каждое число
будет находиться в диапазоне от -1000000 до 1000000 и будет состоять только из цифр 0-9,
возможно ведущего - и, возможно, нескольких ?s. Если в выражении есть ?s, они представляют
собой цифровую руну, которую профессор не знает (ни в коем случае не оператор и никогда не ведущий -).
Все знаки ? в выражении будут представлять одну и ту же цифру (0-9), и это не будет ни одна из других
заданных цифр в выражении. Ни одно число не будет начинаться с 0, если само число не равно 0,
поэтому 00 не будет допустимым числом.*/

    private static final Pattern NOT_ZERO = Pattern.compile("(^|[^\\d])[?][?\\d]");

    public static int solveExpression1(String runes) {
        for (char d = '0'; d <= '9'; d += 1) {
            if (runes.indexOf(d) >= 0) {
                continue;
            }
            if (d == '0' && NOT_ZERO.matcher(runes).find()) {
                continue;
            }
            if (isCorrect(runes.replace('?', d))) {
                return d - '0';
            }
        }
        return -1;
    }

    private static boolean isCorrect(String expression) {
        try {
            return (boolean) new ScriptEngineManager().getEngineByName("JavaScript")
                    .eval(expression.replace("--", "+").replace("=", "==="));
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    /* public static int solveExpression( final String expression ) {
    System.out.println(expression);
    for (int i = 0; i <= 9; ++i) {
      if (!expression.contains("" + i) && (i != 0 || !expression.matches(".*(^\\?[^\\+\\-\\*\\=]|[\\+\\-\\*\\=]\\?[^\\+\\-\\*\\=]).*"))) {
        String exp = expression.replaceAll("\\?", "" + i);
        char op = exp.contains("+") ? '+' : exp.contains("*") ? '*' : exp.contains("-") ? '-' : ' ';
        int number1 = Integer.parseInt(exp.substring(0, exp.indexOf(op, 1)));
        int number2 = Integer.parseInt(exp.substring(exp.indexOf(op, 1) + 1, exp.indexOf("=")));
        int result = Integer.parseInt(exp.substring(exp.indexOf("=") + 1));
        switch (op) {
          case '+' :
            if (number1 + number2 == result) return i;
            break;
          case '-' :
            if (number1 - number2 == result) return i;
            break;
          case '*' :
            if (number1 * number2 == result) return i;
            break;
        }
      }
    }
    return -1;
  }*/

    /*private static Set<String> DIGITS = new HashSet<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

  private static String NUMS = "[\\d?]+";
  private static Pattern TOKENIZER = Pattern.compile(String.format("(-?%1$s)([*+-])(-?%1$s)=(-?%1$s)", NUMS));
  private static String INVALID_0 = ".*?(?<!"+NUMS+")[?]"+NUMS+"+.*";

  public static int solveExpression(String runes) {

    Set<String> knownDigits = new HashSet<String>();
    for (int i = 0 ; i < runes.length() ; i++) knownDigits.add(runes.substring(i,i+1));
    knownDigits.retainAll(DIGITS);

    for (String d: DIGITS) {
        if (knownDigits.contains(d) || d.equals("0") && runes.matches(INVALID_0)) continue;

        Matcher m = TOKENIZER.matcher(runes);
        m.find();
        String a  = m.group(1).replace("?",d),
               op = m.group(2),
               b  = m.group(3).replace("?",d),
               c  = m.group(4).replace("?",d);
        int x = Integer.parseInt(a),
            y = Integer.parseInt(b),
            z = Integer.parseInt(c),
            digit = Integer.parseInt(d);
        switch (op) {
            case "+": if (x+y==z) return digit; break;
            case "-": if (x-y==z) return digit; break;
            case "*": if (x*y==z) return digit; break;
        }
    }
    return -1;
  }
*/

    /* private static boolean thisIsTrue(final String expr) {
    final Pattern p = Pattern.compile("(-?+[0-9]+)([*+-])(-?+[0-9]+)=(.*)"); // Expr always has form: (num)[op](num)=(num)
    final Matcher m = p.matcher(expr);
    if (m.matches()) {
      final String a = m.group(1), b = m.group(3), c = m.group(4);
      if (a.matches("-*0.+") || b.matches("-*0.+") || c.matches("-*0.+")) return false; // leading zeroes not allowed
      final int ai = Integer.valueOf(a), bi = Integer.valueOf(b), ci = Integer.valueOf(c);
      switch (m.group(2)) {
        case "+" : return ai + bi == ci;
        case "-" : return ai - bi == ci;
        case "*" : return ai * bi == ci;
      }
    }
    return false;
  }

  public static int solveExpression(final String expression ) {
    for (int i=0; i<10; i++) {
      if (expression.contains(""+i)) continue; // unknown digit will not be the same as any other digits
      if (thisIsTrue(expression.replace("?",""+i))) return i;
    }
    return -1;
  }*/

    public static int solveExpression(final String expression) {
        int missingDigit = -1;
        List<String> allNumberList = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        String[] arrayExpression = expression.trim().replaceAll("\\s+", "").split("[-=+*]");
        List<String> listExpression = new ArrayList<>();
        boolean isMinor = false;
        for (int i = 0; i < arrayExpression.length; i++) {
            if (arrayExpression[i].isEmpty() || arrayExpression[i].isBlank()) {
                isMinor = true;
                continue;
            }
            if (isMinor) {
                listExpression.add("-" + arrayExpression[i]);
                isMinor = false;
            } else {
                listExpression.add(arrayExpression[i]);
            }

        }
        if (listExpression.size() != 3) {
            return missingDigit;
        }
        String numbers = expression.replaceAll("\\D", "");
        HashSet<String> numberSet = new HashSet<>();
        for (int i = 0; i < numbers.length(); i++) {
            numberSet.add(numbers.substring(i, i + 1));
        }
        allNumberList.removeAll(numberSet);

        if (allNumberList.contains("0")) {
            for (String st : listExpression) {
                if (st.length() > 1) {
                    String tmp = st.replaceAll("[-]", "");
                    if (tmp.substring(0, 1).equals("?")) {
                        allNumberList.remove("0");
                        break;
                    }
                }
            }
        }
        for (String st : allNumberList) {
            int one = Integer.parseInt(listExpression.get(0).replaceAll("[?]", st));
            int two = Integer.parseInt(listExpression.get(1).replaceAll("[?]", st));
            int three = Integer.parseInt(listExpression.get(2).replaceAll("[?]", st));
//            System.out.println(one + " " + two + " " + three);
            if (expression.contains("*")) {
                if (one * two == three) {
                    return Integer.parseInt(st);
                }
            } else if (expression.contains("+")) {
                if (one + two == three) {
                    return Integer.parseInt(st);
                }
            } else {
                if (one - two == three) {
                    return Integer.parseInt(st);
                }
            }
        }
        return missingDigit;
    }
}
