package katas;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CharCollector {

    public static void main(String[] args) {

        countCharacters("Das darf nicht sein").forEach((key, value) -> System.out.println(key + ":" + value));
        display(countCharacters("Das darf nicht sein"));
    }

    private static void display(Map<Character, Integer> myMap) {

        Map<Character, Integer> treeMap = new TreeMap<>(myMap);
        StringBuilder sb = new StringBuilder();

        int maxValue = myMap.values().stream().max(Integer::compare).get();

        Character[][] charVisualiser = new Character[maxValue][treeMap.size()];

        for (int value = 0; value < maxValue; value++) {
            int loopNum = 0;
            for (Map.Entry<Character, Integer> entry : treeMap.entrySet()) {

                if (entry.getValue() >= maxValue - value) {
                    charVisualiser[value][loopNum] = '*';
                } else {
                    charVisualiser[value][loopNum] = ' ';
                }
                loopNum++;
            }
        }

        for (Character[] characters : charVisualiser) {
            for (Character character : characters) {
                sb.append(character).append(" ");
            }
            sb.append(System.getProperty("line.separator"));
        }

        for (int i = 0; i < treeMap.size() * 2; i++) {
            sb.append("-");
        }
        sb.append(System.getProperty("line.separator"));

        for (Map.Entry<Character, Integer> entry : treeMap.entrySet()) {
            sb.append(entry.getKey()).append(" ");
        }

        sb.append(System.getProperty("line.separator"));

        for (Map.Entry<Character, Integer> entry : treeMap.entrySet()) {
            sb.append(entry.getValue()).append(" ");
        }

        System.out.println(sb);

    }

    private static Map<Character, Integer> countCharacters(String input) {

        input = input.toUpperCase();
        input = input.replaceAll(" ", "_");
        Map<Character, Integer> mapOfChars = new HashMap<>();

        char[] charArray = input.toCharArray();

        for (char c : charArray) {

            if (mapOfChars.containsKey(c)) {
                mapOfChars.replace(c, mapOfChars.get(c) + 1);
            } else {
                mapOfChars.put(c, 1);
            }
        }
        return mapOfChars;
    }


}
