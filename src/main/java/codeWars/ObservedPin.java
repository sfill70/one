package codeWars;

import java.util.*;

public class ObservedPin {
    public static void main(String[] args) {

        System.out.println(getPINs("1234"));
        System.out.println(getPINs("8"));
        System.out.println(getPINs("369"));
        String[] arr = new String[]{"236", "238", "239", "256", "258", "259", "266", "268", "269", "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368", "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666", "668", "669", "696", "698", "699"};
    }

    /*public static List<String> getPINs(String observed) {
        int[][] replacement = {{8, 0}, {1, 2, 4}, {1, 2, 3, 5}, {2, 3, 6}, {1, 4, 5, 7}, {2, 4, 5, 6, 8}, {3, 5, 6, 9}, {4, 7, 8}, {5, 7, 8, 9, 0}, {6, 8, 9}};

        List<Integer> numberList = new ArrayList<>();
        observed = observed.trim().replaceAll("\\s+", "");
        for (int i = 0; i < observed.length(); i++) {
            numberList.add(Integer.parseInt(observed.substring(i, i + 1)));
        }
        System.out.println(numberList);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numberList.size(); i++) {
            for (int j = 0; j < replacement[numberList.get(i)].length; j++) {
                if (i == 0) {
                    List<Integer> integerList = new ArrayList<>();
                    integerList.add(replacement[numberList.get(i)][j]);
                    result.add(integerList);
                }
            }
        }
        for (int i = 1; i < numberList.size(); i++) {
            List<List<Integer>> tmpList = new ArrayList<>();
            for (int j = 0; j < replacement[numberList.get(i)].length; j++) {
                for (List<Integer> list : result) {
                    List<Integer> tmpInt = new ArrayList<>(list);
                    tmpInt.add(replacement[numberList.get(i)][j]);
                    tmpList.add(tmpInt);
                }
            }
            result = tmpList;
        }
//        System.out.println(result);
        List<String> resultString = new ArrayList<>();
        for (List<Integer> list : result) {
//            System.out.println(list.toString().replaceAll("[]\\[,\\s]",""));
            resultString.add(list.toString().replaceAll("[]\\[,\\s]",""));
        }
        return resultString;
    }*/

    private static final Map<Character, String> ADJACENTS = new HashMap<Character, String>() {{
        put('1', "124");
        put('2', "2135");
        put('3', "326");
        put('4', "4157");
        put('5', "54268");
        put('6', "6953");
        put('7', "748");
        put('8', "87590");
        put('9', "986");
        put('0', "08");
    }};

    public static List<String> getPINs(String observed) {
        List<String> ans = Arrays.asList("");
        for (char c : observed.toCharArray()) {
            List<String> tmp = new ArrayList<String>();
            for (char cc : ADJACENTS.get(c).toCharArray()) {
                for (String s : ans) {
                    tmp.add(s + cc);
                }
            }
            ans = tmp;
        }
        return ans;
    }

}
