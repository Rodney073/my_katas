package katas;

import java.util.*;

public class ROT13 {

    static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static void encryptText(String text, int shiftCode) {

        text = cleanUpText(text);

        ArrayList<Character> alphabetList = new ArrayList<>();

        for (char c : alphabet) {
            alphabetList.add(c);
        }

        for (int charIndex = 0; charIndex < text.length(); charIndex++) {

            if (alphabetList.contains(text.charAt(charIndex))) {

                int indexOfLetterInList = alphabetList.indexOf(text.charAt(charIndex));
                if (indexOfLetterInList < shiftCode) {

                    text = doShift(text, charIndex, alphabetList, alphabetList.size() - (shiftCode - indexOfLetterInList));

                } else {

                    text = doShift(text, charIndex, alphabetList, indexOfLetterInList - shiftCode);
                }
            }
        }
        System.out.println(text);
    }

    private static String cleanUpText(String text) {

        text = text.toUpperCase();

        Map<String, String> umlautsToEnglishLetters = new HashMap<String, String>() {{
            put("Ö", "OE");
            put("Ä", "AE");
            put("Ü", "UE");
            put("ß", "SS");
        }};

        for (int i = 0; i < text.length(); i++) {

            for (Map.Entry<String, String> entry : umlautsToEnglishLetters.entrySet()) {
                text = text.replaceAll(entry.getKey(), entry.getValue());
            }
        }

        return text;

    }

    private static String doShift(String text, int charIndex, ArrayList<Character> alphabetList, int shiftWith) {

        StringBuilder textBuilder = new StringBuilder(text);
        textBuilder.setCharAt(charIndex, alphabetList.get(shiftWith));
        return textBuilder.toString();
    }

    public static void main(String[] args) {
        encryptText("Hello World!", 13);
        encryptText("Hello World!ÜßÄ", 13);
    }

}
