package adventOfCode2021;

import adventOfCode2021.helpers.CommonBits;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {

    private final List<String> reportList;

    public Day3() throws IOException {
        this.reportList = readFileToArrayList();
    }

    public int getPowerConsumption() {

        StringBuilder mostCommonBits = new StringBuilder();
        StringBuilder leastCommonBits = new StringBuilder();


        int lengthOfCode = reportList.get(0).length();

        for (int i = 0; i < lengthOfCode; i++) {
            int numOfNulls = 0;
            int numOfOnes = 0;
            for (String currentLine : reportList) {
                if (currentLine.charAt(i) == '0') {
                    numOfNulls++;
                } else {
                    numOfOnes++;
                }
            }
            if (numOfNulls > numOfOnes) {
                mostCommonBits.append("0");
                leastCommonBits.append("1");
            }
            if (numOfNulls < numOfOnes) {
                mostCommonBits.append("1");
                leastCommonBits.append("0");
            }
        }

        System.out.println(mostCommonBits);

        return Integer.parseInt(mostCommonBits.toString(), 2) * Integer.parseInt(leastCommonBits.toString(), 2);
    }

    public int calculate(String ratingType) {

        List<String> report = new ArrayList<>(this.reportList);
        List<String> reducedReport = new ArrayList<>();

        for (int i = 0; i < report.get(0).length(); i++) {
            String common = (ratingType.equals("O2") ? findCommonBits(report).getMostCommon() : findCommonBits(report).getLeastCommon());
            for (String s : report) {
                if (s.charAt(i) == common.charAt(i)) {
                    reducedReport.add(s);
                }
            }
            if (reducedReport.size() == 1) {
                break;
            }
            report.clear();
            report.addAll(reducedReport);
            reducedReport.clear();
        }
        return Integer.parseInt(reducedReport.get(0), 2);
    }

    public int findLifeSupportRating() {
        return calculate("O2") * calculate("CO2");
    }

    private CommonBits findCommonBits(List<String> codeList) {
        StringBuilder mostCommonBits = new StringBuilder();

        for (int i = 0; i < codeList.get(0).length(); i++) {
            int numOfNulls = 0;
            int numOfOnes = 0;
            for (String currentLine : codeList) {
                if (currentLine.charAt(i) == '0') {
                    numOfNulls++;
                } else {
                    numOfOnes++;
                }
            }
            if (numOfNulls > numOfOnes) {
                mostCommonBits.append("0");
            } else {
                mostCommonBits.append("1");
            }
        }

        String leastCommonBits = mostCommonBits
                .toString()
                .replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");

        return new CommonBits(mostCommonBits.toString(), leastCommonBits);
    }


    public List<String> readFileToArrayList() throws IOException {
        return Files.lines(Paths.get("src/main/resources/AdventOfCode/3.txt"))
                .flatMap(s -> Arrays.stream(s.split("\\n")))
                .collect(Collectors.toList());

    }

}
