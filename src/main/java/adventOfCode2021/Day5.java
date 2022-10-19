package adventOfCode2021;

import adventOfCode2021.helpers.Line;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class Day5 {
    private final List<Line> lineListWithoutDiagonals;
    private final List<Line> lineListWithDiagonals;
    private final String filePath;

    public Day5(String filePath) {
        this.filePath = filePath;
        this.lineListWithoutDiagonals = new ArrayList<>();
        this.lineListWithDiagonals = new ArrayList<>();
    }

    public int countOverlapsWithoutDiagonals() {
        readFileToArrayLists(false);
        int[][] diagram = new int[getMaxValue(lineListWithoutDiagonals, Line::getXEnd) + 1][getMaxValue(lineListWithoutDiagonals, Line::getYEnd) + 1];
        updateDiagramValues(diagram);
        return countOverlapsInDiagram(diagram);
    }

    public int countOverlapsWithDiagonals() {
        readFileToArrayLists(true);

        List<Line> joinedList = new ArrayList<>();
        joinedList.addAll(lineListWithoutDiagonals);
        joinedList.addAll(lineListWithDiagonals);
        int[][] diagram = new int[getMaxValue(joinedList, Line::getXEnd) + 1][getMaxValue(joinedList, Line::getYEnd) + 1];

        updateDiagramValues(diagram);
        updateDiagramValuesWithDiagonalLines(diagram);
        return countOverlapsInDiagram(diagram);
    }

    private int getMaxValue(List<Line> lineList, Function<Line, Integer> func) {
        return Objects.requireNonNull(lineList.stream()
                .collect(groupingBy(func))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByKey())
                .orElse(null)).getKey();
    }

    private void updateDiagramValues(int[][] diagram) {
        for (Line line : lineListWithoutDiagonals) {
            for (int row = line.getXStart(); row <= line.getXEnd(); row++) {
                for (int col = line.getYStart(); col <= line.getYEnd(); col++) {
                    diagram[row][col]++;
                }
            }
        }
    }

    private void updateDiagramValuesWithDiagonalLines(int[][] diagram) {
        for (Line line : lineListWithDiagonals) {
            int col = line.getYStart();

            if (line.getXEnd() > line.getXStart() && line.getYEnd() < line.getYStart()) {
                for (int row = line.getXStart(); row <= line.getXEnd(); row++) {
                    diagram[row][col]++;
                    col--;
                }

            } else {
                for (int row = line.getXStart(); row <= line.getXEnd(); row++) {
                    diagram[row][col]++;
                    col++;
                }
            }
        }
    }

    private int countOverlapsInDiagram(int[][] diagram) {
        return (int) Arrays.stream(diagram)
                .flatMapToInt(Arrays::stream)
                .filter(num -> num > 1)
                .count();
    }

    private void readFileToArrayLists(boolean readDiagonals) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                int[] splitLine = Arrays.stream(line.replace(" -> ", ",").split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int x1 = splitLine[0];
                int y1 = splitLine[1];
                int x2 = splitLine[2];
                int y2 = splitLine[3];

                if (x1 == x2 || y1 == y2) {                                             //Vertical and horizontal lines
                    lineListWithoutDiagonals.add(orderCoordinates(x1, y1, x2, y2));
                } else if (readDiagonals) {
                    if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {                 //Diagonal lines: \
                        lineListWithDiagonals.add(orderCoordinates(x1, y1, x2, y2));
                    } else if (x2 < x1) {                                               //Diagonal lines: /
                        lineListWithDiagonals.add(new Line(x2, y2, x1, y1));
                    } else {
                        lineListWithDiagonals.add(new Line(x1, y1, x2, y2));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static Line orderCoordinates(int x1, int y1, int x2, int y2) {
        return new Line(
                Math.min(x1, x2),
                Math.min(y1, y2),
                Math.max(x1, x2),
                Math.max(y1, y2));
    }
}
