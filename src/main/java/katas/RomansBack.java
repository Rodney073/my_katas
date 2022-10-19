package katas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class RomansBack {

    public RomansBack() {
    }

    Map<Integer, Character> latinToRomanNum = new TreeMap<Integer, Character>() {{
        put(1, 'I');
        put(5, 'V');
        put(10, 'X');
        put(50, 'L');
        put(100, 'C');
        put(500, 'D');
        put(1000, 'M');

    }};

    public String transform(int numToBeConverted) {

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> latinNumList = new ArrayList<>();

        for (Map.Entry<Integer, Character> entry : latinToRomanNum.entrySet()) {
            latinNumList.add(entry.getKey());
        }

        Collections.reverse(latinNumList);

        for (int i = 0; i < latinNumList.size(); i++) {

            if (numToBeConverted <= 0) {
                break;
            }

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
