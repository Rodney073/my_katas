package adventOfCode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@SuppressWarnings("all")
public class Day7 {

    public Long findCheapestPosition_part2() throws IOException {

        List<Integer> crabPositions = readFile();

        OptionalDouble average = crabPositions
                .stream()
                .mapToDouble(a -> a)
                .average();

        return crabPositions.stream().mapToLong(num -> sumNumbers(num, average.getAsDouble())).sum();
    }

    private int sumNumbers(int num, double avg) {
        int result = 0;
        for (int i = 0; i <= Math.abs(num - Math.floor(avg)); i++) {
            result = result + i;
        }
        return result;
    }

    public Long findCheapestPosition() throws IOException {

        List<Integer> crabPositions = readFile();
        DoubleStream sortedList = crabPositions.stream().mapToDouble(n -> n).sorted();

        double median = crabPositions.size() % 2 == 0 ?
                sortedList.skip(crabPositions.size() / 2 - 1).limit(2).average().getAsDouble() :
                sortedList.skip(crabPositions.size() / 2).findFirst().getAsDouble();

        return crabPositions.stream().mapToLong(num -> (long) Math.abs(num - median)).sum();
    }

    private List<Integer> readFile() throws IOException {

        return Files.lines(Paths.get("src/main/resources/AdventOfCode/7.txt"))
                .flatMap(s -> Arrays.stream(s.split(","))).map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
