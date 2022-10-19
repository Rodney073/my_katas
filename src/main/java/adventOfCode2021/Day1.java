package adventOfCode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {

    private List<Integer> numList = new ArrayList<>();

    public Day1() {
        readFile();
    }

    Integer numOfLargerMeasurements() {

        int count = 0;
        for (int i = 0; i < numList.size() - 1; i++) {
            if (numList.get(i) < numList.get(i + 1)) {
                count++;
            }
        }
        return count;

    }

    Integer numOfLargerMeasurements2() {

        int count = 0;
        for (int i = 0; i < numList.size() - 3; i++) {

            int sum1 = numList.subList(i, i + 3).stream().mapToInt(Integer::intValue).sum();
            int sum2 = numList.subList(i + 1, i + 4).stream().mapToInt(Integer::intValue).sum();

            if (sum1 < sum2) {
                count++;
            }
        }
        return count;
    }

    private void readFile() {
        try {
            this.numList = Files.lines(Paths.get("src/main/resources/AdventOfCode/1.txt"))
                    .flatMap(s -> Arrays.stream(s.split(" "))).map(Integer::parseInt)
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
