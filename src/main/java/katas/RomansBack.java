package katas;

import java.util.*;
import java.util.stream.Collectors;

public class RomansBack {

    public RomansBack() {
    }

    Map<Integer, Character> latinToRomanNum = new LinkedHashMap<Integer, Character>() {{
        put(1000, 'M');
        put(500, 'D');
        put(100, 'C');
        put(50, 'L');
        put(10, 'X');
        put(5, 'V');
        put(1, 'I');
    }};

    public String transform(int numToBeConverted) {

        if (numToBeConverted <= 0 || numToBeConverted > 3999) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> latinNumList = new ArrayList<>(latinToRomanNum.keySet());

        for (int i = 0; i < latinNumList.size(); i++) {

            int actualRomanNumValue = latinNumList.get(i);
            int howManyTimes = (int) Math.floor((double) numToBeConverted / latinNumList.get(i));
            int firstDigitOfReminder = Integer.parseInt(Integer.toString(numToBeConverted).substring(0, 1));

            if (firstDigitOfReminder == 9 && numToBeConverted > latinNumList.get(i)) {

                int prevRomanNumValue = latinNumList.get(i - 1);
                int nextRomanNumValue = latinNumList.get(i + 1);

                sb.append(latinToRomanNum.get(nextRomanNumValue)).append(latinToRomanNum.get(prevRomanNumValue));
                numToBeConverted = numToBeConverted - (prevRomanNumValue - nextRomanNumValue);
                continue;
            }


            if (firstDigitOfReminder == 4) {
                //If length of actual latinNum != numToBeConverted
                if (String.valueOf(numToBeConverted).length() != String.valueOf(actualRomanNumValue).length()) {
                    continue;
                }
                int nextRomanNumValue = latinNumList.get(i + 1);

                sb.append(latinToRomanNum.get(nextRomanNumValue)).append(latinToRomanNum.get(actualRomanNumValue));
                numToBeConverted = numToBeConverted - (actualRomanNumValue - nextRomanNumValue);
                continue;
            }

            if (howManyTimes >= 1 && howManyTimes < 4) {
                for (int j = 0; j < howManyTimes; j++) {
                    sb.append(latinToRomanNum.get(actualRomanNumValue));
                    numToBeConverted = numToBeConverted - actualRomanNumValue;
                }
            }
        }
        return sb.toString();
    }
}
