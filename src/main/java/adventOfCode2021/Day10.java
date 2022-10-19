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
        return illegalCharList;
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
