package katas;

import java.util.HashMap;
import java.util.Map;

public class Romans {
    static Map<Character, Integer> romanNumberToLatinValue = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static int translateRomanNum(String romanNum) {

        romanNum = cleanUpString(romanNum);

        //Reverse string
        String reversedRomanNum = new StringBuilder(romanNum).reverse().toString();

        int latinNum = 0;

        for (int i = 0; i < reversedRomanNum.length(); i++) {

            int valueOfActualSymbol = romanNumberToLatinValue.get(reversedRomanNum.charAt(i));

            int valueOfNextSymbol = 0;
            if (i != reversedRomanNum.length() - 1) {
                valueOfNextSymbol = romanNumberToLatinValue.get(reversedRomanNum.charAt(i + 1));
            }

            if (valueOfActualSymbol <= valueOfNextSymbol || valueOfNextSymbol == 0) {
                latinNum += valueOfActualSymbol;
            } else {
                latinNum += valueOfActualSymbol - valueOfNextSymbol;
                i++;
            }
        }
        return latinNum;
    }

    private static String cleanUpString(String romanNum) {

        //Get rid of typos and spaces
        romanNum = romanNum.replaceAll("[^a-zA-Z]", "");
        //Convert string to uppercase
        romanNum = romanNum.toUpperCase();

        return romanNum;
    }

    public static void main(String[] args) {
        System.out.println(translateRomanNum("MCMXVIII."));
    }
}
