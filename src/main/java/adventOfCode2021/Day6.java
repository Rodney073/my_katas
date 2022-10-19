package adventOfCode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {

    public Day6() {

    }

    public long countNumberOfFishIn256 () throws IOException {
        List <Integer> fishList = readFile();

        int days = 0;
        while (days != 80) {

            int numOfNull = (int) fishList.stream().filter(num -> num.equals(0)).count();

            for (int i = 0; i < fishList.size() ; i++) {

                if (fishList.get(i) == 0) {
                    fishList.set(i, 6);
                }
                else {
                    fishList.set(i, fishList.get(i)-1);
                }
            }

            for (int i = 0; i < numOfNull; i++) {
                fishList.add(8);
            }

            days++;
        }

        return fishList.size();
    }

    public int countNumberOfFishIn80 () throws IOException {
        List <Integer> fishList = readFile();

        int days = 0;
        while (days != 80) {

            int numOfNull = (int) fishList.stream().filter(num -> num.equals(0)).count();

            for (int i = 0; i < fishList.size() ; i++) {

                if (fishList.get(i) == 0) {
                    fishList.set(i, 6);
                }
                else {
                    fishList.set(i, fishList.get(i)-1);
                }
            }

            for (int i = 0; i < numOfNull; i++) {
                fishList.add(8);
            }

            days++;
        }

        return fishList.size();
    }

    private List<Integer> readFile () throws IOException {

        return Files.lines(Paths.get("src/main/resources/AdventOfCode/6.txt"))
                .flatMap(s -> Arrays.stream(s.split(","))).map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
