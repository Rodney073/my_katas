package adventOfCode2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {

    List<String> outputValueList = new ArrayList<>();
    List<String> configReference = new ArrayList<>();
    Map<Integer, Map<String, Character>> configurationMap = new HashMap<>();
    Map<Integer, Map<String, Integer>> decodedDigitMap = new HashMap<>();

    public Day8() {
        lists_init();
        configurationMap_init();
    }

    public long sumAllOutputDigits() {
        decodedDigitMap_init();

        List<Integer> digitList = new ArrayList<>();

        int line = 1;
        StringBuilder actualNum = new StringBuilder();
        for (int i = 1; i <= outputValueList.size(); i++) {

            actualNum.append(Integer.parseInt(String.valueOf(decodeDigits(line, outputValueList.get(i - 1)))));

            if (i % 4 == 0) {
                digitList.add(Integer.valueOf(actualNum.toString()));
                actualNum = new StringBuilder();
                line++;
            }
        }
        return digitList.stream()
                .mapToLong(Integer::longValue)
                .sum();

    }

    public long sumAllOutputDigits2() {
        decodedDigitMap_init();

        List<Integer> digitList = new ArrayList<>();

        int line = 1;
        StringBuilder actualNum = new StringBuilder();
        for (int i = 1; i <= outputValueList.size(); i++) {

            actualNum.append(decodedDigitMap.get(line).get(Stream.of(outputValueList.get(i - 1).split(""))
                    .sorted()
                    .collect(Collectors.joining())));

            if (i % 4 == 0) {
                digitList.add(Integer.valueOf(actualNum.toString()));
                actualNum = new StringBuilder();
                line++;
            }
        }
        return digitList.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    public int decodeDigits(int line, String input) {

        Map<String, Character> digitMap = configurationMap.get(line);

        if (input.length() == 2) {
            return 1;
        }
        if (input.length() == 3) {
            return 7;
        }
        if (input.length() == 4) {
            return 4;
        }
        if (input.length() == 7) {
            return 8;
        }
        if (!input.contains(String.valueOf(digitMap.get("BOTTOM_LEFT"))) && !input.contains(String.valueOf(digitMap.get("TOP_RIGHT")))) {
            return 5;
        }
        if (!input.contains(String.valueOf(digitMap.get("TOP_LEFT"))) && !input.contains(String.valueOf(digitMap.get("BOTTOM_RIGHT")))) {
            return 2;
        }
        if (!input.contains(String.valueOf(digitMap.get("TOP_LEFT"))) && !input.contains(String.valueOf(digitMap.get("BOTTOM_LEFT")))) {
            return 3;
        }
        if (!input.contains(String.valueOf(digitMap.get("MIDDLE")))) {
            return 0;
        }
        if (!input.contains(String.valueOf(digitMap.get("BOTTOM_LEFT")))) {
            return 9;
        } else {
            return 6;
        }
    }

    public void configurationMap_init() {

        Map<Integer, Map<String, Character>> configMap = new HashMap<>();

        for (int i = 1; i <= configReference.size() / 10; i++) {
            Map<String, Character> digitConfig = new HashMap<>();
            List<String> actualDigits = configReference.subList(i * 10 - 10, i * 10);

            String one = actualDigits.stream()
                    .filter(digit -> digit.length() == 2).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            String four = actualDigits.stream()
                    .filter(digit -> digit.length() == 4).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            String seven = actualDigits.stream()
                    .filter(digit -> digit.length() == 3).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            String eight = actualDigits.stream()
                    .filter(digit -> digit.length() == 7).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            String fourExcludeOne = findNotSubset(four, one);

            List<String> sixLongDigits = actualDigits.stream()
                    .filter(digit -> digit.length() == 6)
                    .collect(Collectors.toList());

            String six = sixLongDigits.stream()
                    .filter(digit -> !digit.contains(String.valueOf(one.charAt(0))) || !digit.contains(String.valueOf(one.charAt(1))))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            assert six != null;
            if (six.contains(String.valueOf(one.charAt(0)))) {
                digitConfig.put("BOTTOM_RIGHT", one.charAt(0));
                digitConfig.put("TOP_RIGHT", one.charAt(1));
            } else {
                digitConfig.put("TOP_RIGHT", one.charAt(0));
                digitConfig.put("BOTTOM_RIGHT", one.charAt(1));
            }

            digitConfig.put("TOP", findNotSubset(seven, one).charAt(0));

            sixLongDigits.remove(six);

            String nine = sixLongDigits.stream()
                    .filter(digit -> digit.contains(String.valueOf(fourExcludeOne.charAt(0))) && digit.contains(String.valueOf(fourExcludeOne.charAt(1))))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            digitConfig.put("BOTTOM_LEFT", findNotSubset(eight, nine).charAt(0));

            sixLongDigits.remove(nine);

            String zero = sixLongDigits.get(0);

            digitConfig.put("MIDDLE", findNotSubset(eight, zero).charAt(0));

            digitConfig.put("TOP_LEFT", findNotSubset(fourExcludeOne, String.valueOf(digitConfig.get("MIDDLE"))).charAt(0));

            digitConfig.put("BOTTOM", findNotSubset(eight, digitConfig.values().stream().map(Object::toString).collect(Collectors.joining())).charAt(0));

            configMap.put(i, digitConfig);
        }
        this.configurationMap = configMap;
    }

    public String findNotSubset(String string, String sub) {

        StringBuilder notSub = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (!sub.contains(String.valueOf(string.charAt(i)))) {
                notSub.append(string.charAt(i));
            }
        }
        return notSub.toString();
    }


    public int countUniqueDigits() {
        List<Integer> uniqueSegmentNumbers = Arrays.asList(2, 3, 4, 7);
        return (int) outputValueList.stream().filter(num -> uniqueSegmentNumbers.contains(num.length())).count();
    }

    private void lists_init() {

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/AdventOfCode/8.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String outputValues = line.substring(line.indexOf("|") + 2);
                this.outputValueList.addAll(Arrays.asList(outputValues.split("\\s")));

                String reference = line.substring(0, line.indexOf("|") - 1);
                this.configReference.addAll(Arrays.asList(reference.split("\\s")));

            }
        } catch (IOException ex) {
            System.out.println("Ooops, something went wrong: " + ex.getMessage());
        }

    }

    public void decodedDigitMap_init() {

        Map<Integer, Map<String, Integer>> configMap = new HashMap<>();


        for (int i = 1; i <= configReference.size() / 10; i++) {

            Map<String, Integer> decodedDigitMap = new HashMap<>();
            List<String> actualDigits = configReference.subList(i * 10 - 10, i * 10);

            //    ============= 1 =============

            String one = actualDigits.stream()
                    .filter(digit -> digit.length() == 2).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(one.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 1);

            //    ============= 4 =============

            String four = actualDigits.stream()
                    .filter(digit -> digit.length() == 4).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(four.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 4);

            //    ============= 7 =============

            String seven = actualDigits.stream()
                    .filter(digit -> digit.length() == 3).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(seven.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 7);

            //    ============= 8 =============

            String eight = actualDigits.stream()
                    .filter(digit -> digit.length() == 7).findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(eight.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 8);

            //    ============= 6 =============

            List<String> sixLongDigits = actualDigits.stream()
                    .filter(digit -> digit.length() == 6)
                    .collect(Collectors.toList());

            String six = sixLongDigits.stream()
                    .filter(digit -> !digit.contains(String.valueOf(one.charAt(0))) || !digit.contains(String.valueOf(one.charAt(1))))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(six.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 6);

            String TOP_RIGHT;
            String BOTTOM_RIGHT;
            if (six.contains(String.valueOf(one.charAt(0)))) {
                BOTTOM_RIGHT = String.valueOf(one.charAt(0));
                TOP_RIGHT = String.valueOf(one.charAt(1));

            } else {
                TOP_RIGHT = String.valueOf(one.charAt(0));
                BOTTOM_RIGHT = String.valueOf(one.charAt(1));
            }

            sixLongDigits.remove(six);

            //    ============= 9 =============

            String fourExcludeOne = findNotSubset(four, one);

            String nine = sixLongDigits.stream()
                    .filter(digit -> digit.contains(String.valueOf(fourExcludeOne.charAt(0))) && digit.contains(String.valueOf(fourExcludeOne.charAt(1))))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(nine.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 9);

            String BOTTOM_LEFT = String.valueOf(findNotSubset(eight, nine).charAt(0));

            //    ============= 0 =============

            sixLongDigits.remove(nine);

            String zero = sixLongDigits.get(0);
            decodedDigitMap.put(Stream.of(zero.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 0);

            //    ============= 3 =============

            List<String> fiveLongDigits = actualDigits.stream()
                    .filter(digit -> digit.length() == 5)
                    .collect(Collectors.toList());

            String finalTOP_RIGHT = TOP_RIGHT;
            String finalBOTTOM_RIGHT = BOTTOM_RIGHT;

            String three = fiveLongDigits.stream()
                    .filter(digit -> digit.contains(finalTOP_RIGHT) && digit.contains(finalBOTTOM_RIGHT))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(three.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 3);
            fiveLongDigits.remove(three);

            //    ============= 5 =============

            String five = fiveLongDigits.stream()
                    .filter(digit -> !digit.contains(BOTTOM_LEFT) && !digit.contains(finalTOP_RIGHT))
                    .findFirst()
                    .map(Objects::toString)
                    .orElseThrow(() -> new InvalidParameterException("This can't happen"));

            decodedDigitMap.put(Stream.of(five.split(""))
                    .sorted()
                    .collect(Collectors.joining()), 5);

            //    ============= 2 =============

            fiveLongDigits.remove(five);

            decodedDigitMap.put(Stream.of(fiveLongDigits.get(0).split(""))
                    .sorted()
                    .collect(Collectors.joining()), 2);

            configMap.put(i, decodedDigitMap);
        }

        this.decodedDigitMap = configMap;
    }

}
