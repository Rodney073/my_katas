package adventOfCode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    private List<String> commandList = new ArrayList<>();

    public Day2() {
        readFile();
    }

    int calculateFinalPosition() {
        int position = 0;
        int depth = 0;

        for (String command : commandList) {
            int unit = Integer.parseInt(command.split(" ")[1]);

            if (command.contains("forward")) {
                position = position + unit;
            }
            if (command.contains("down")) {
                depth = depth + unit;
            }
            if (command.contains("up")) {
                depth = depth - unit;
            }
        }
        return position * depth;
    }

    int calculateFinalPosition2() {
        int position = 0;
        int depth = 0;
        int aim = 0;

        for (String command : commandList) {
            int unit = Integer.parseInt(command.split(" ")[1]);

            if (command.contains("forward")) {
                position = position + unit;
                depth = depth + (aim * unit);
            }
            if (command.contains("down")) {
                aim = aim + unit;
            }
            if (command.contains("up")) {
                aim = aim - unit;
            }
        }
        return position * depth;
    }

    private void readFile() {
        try {
            this.commandList = Files.lines(Paths.get("src/main/resources/AdventOfCode/2.txt"))
                    .flatMap(s -> Arrays.stream(s.split("\\n")))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
