package adventOfCode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day10 {
    private List<String> listOfChunkLines = new ArrayList<>();
    private final HashMap<Character, Character> charPairs = new HashMap<Character, Character>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
        put('<', '>');
    }};

    public Day10() {
        setListOfChunkLines_init();
    }

    long calculateMiddleScore() {
        final HashMap<Character, Integer> scoreMap = new HashMap<Character, Integer>() {{
            put(')', 1);
            put(']', 2);
            put('}', 3);
            put('>', 4);
        }};

        List <String> completionList = getCompletions();
        List <Long> scoreList = new ArrayList<>();

        for (String actualCompletion : completionList) {
            long score = 0;
            for (int j = 0; j < actualCompletion.length(); j++) {
                score = (score * 5) + scoreMap.get(actualCompletion.charAt(j));
            }
            scoreList.add(score);
        }
        scoreList.sort(null);
        return scoreList.get(scoreList.size()/2);
    }

    private List <String> getCompletions() {

        List <String> completionList = new ArrayList<>();
        List<String> incompleteLines = getIncompleteLines();

        for (String incompleteLine : incompleteLines) {
            String actualLineReversed = new StringBuilder(incompleteLine).reverse().toString();
            StringBuilder completion = new StringBuilder(actualLineReversed);

            for (int j = 0; j < actualLineReversed.length(); j++) {
                completion.setCharAt(j, charPairs.get(actualLineReversed.charAt(j)));
            }
            completionList.add(completion.toString());
        }
        return completionList;
    }

    private List<String> getIncompleteLines() {

        List<String> incompleteLines = new ArrayList<>();
        List<Character> illegalCharList = new ArrayList<>();

        for (String listOfChunkLine : listOfChunkLines) {

            int sizeOfIllegalCharListBefore = illegalCharList.size();

            StringBuilder chunk = new StringBuilder();
            observeLine(illegalCharList, listOfChunkLine, chunk);

            int sizeOfIllegalCharListAfter = illegalCharList.size();

            if (sizeOfIllegalCharListBefore == sizeOfIllegalCharListAfter) {
                incompleteLines.add(chunk.toString());
            }
        }
        return incompleteLines;
    }

    int calculateSyntaxErrorScore() {
        final HashMap<Character, Integer> scoreMap = new HashMap<Character, Integer>() {{
            put(')', 3);
            put(']', 57);
            put('}', 1197);
            put('>', 25137);
        }};

        int sysErrScore = 0;
        List<Character> illegalChars = getIllegalChars();

        for (Character illegalChar : illegalChars) {
            sysErrScore = sysErrScore + scoreMap.get(illegalChar);
        }
        return sysErrScore;
    }

    private List<Character> getIllegalChars() {

        List<Character> illegalCharList = new ArrayList<>();

        for (String listOfChunkLine : listOfChunkLines) {
            StringBuilder chunk = new StringBuilder();

            observeLine(illegalCharList, listOfChunkLine, chunk);
        }
        return illegalCharList;
    }

    private void observeLine(List<Character> illegalCharList, String listOfChunkLine, StringBuilder chunk) {
        for (int charIndex = 0; charIndex < listOfChunkLine.length(); charIndex++) {
            char actualChar = listOfChunkLine.charAt(charIndex);

            if (!charPairs.containsKey(actualChar) && chunk.toString().isEmpty()) {
                illegalCharList.add(actualChar);
                break;
            }
            if (!charPairs.containsKey(actualChar)) {
                if (!charPairs.containsKey(chunk.charAt(chunk.length() - 1)) || charPairs.get(chunk.charAt(chunk.length() - 1)) != actualChar) {
                    illegalCharList.add(actualChar);
                    break;
                }
                if (charPairs.get(chunk.charAt(chunk.length() - 1)) == actualChar) {
                    chunk.setLength(chunk.length() - 1);
                    continue;
                }
            }
            chunk.append(actualChar);
        }
    }
    private void setListOfChunkLines_init() {
        try {
            this.listOfChunkLines = Files.lines(Paths.get("src/main/resources/AdventOfCode/10.txt"))
                    .flatMap(s -> Arrays.stream(s.split("\\n")))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
