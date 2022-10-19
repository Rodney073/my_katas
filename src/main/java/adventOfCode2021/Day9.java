package adventOfCode2021;


import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    private int[][] field;
    private List<String> listOfFieldLines;
    private Map<MutablePair<Integer, Integer>, Boolean> fieldMap;
    private int fieldRowSize;
    private int fieldColSize;

    public Day9() throws IOException {
        readFile();
        field_init();
    }

    public Integer findLargestBasin() {
        List<List<Integer>> basinList = floodFill();
        int sizeOfList = basinList.size();

        return basinList.stream().mapToInt(List::size).sorted().skip(sizeOfList-3).reduce(1, Math::multiplyExact);
    }

    public List<List<Integer>> floodFill() {
        map_init();

        List<List<Integer>> listOfBasins = new ArrayList<>();

        for (int row = 0; row < fieldRowSize; row++) {
            for (int col = 0; col < fieldColSize; col++) {
                List<Integer> basinPoints = new ArrayList<>();

                if (field[row][col] != 9 && !fieldMap.get(new MutablePair<>(row, col))) {
                    helper(row, col, basinPoints);
                }
                else {
                    fieldMap.put(new MutablePair<>(row, col), true);
                }
                if (!basinPoints.isEmpty()){
                    listOfBasins.add(basinPoints);
                }
            }
        }
        return listOfBasins;
    }

    public void helper(int row, int col, List<Integer> basinPoints) {
        if (field[row][col] == 9) {
            return;
        }
        if (fieldMap.get(new MutablePair<>(row, col))) {
            return;
        }

        basinPoints.add(field[row][col]);
        fieldMap.put(new MutablePair<>(row, col), true);

        if (row != fieldRowSize-1) {
            helper(row + 1, col, basinPoints);
        }
        if (row != 0) {
            helper(row - 1, col, basinPoints);
        }
        if (col != 0) {
            helper(row, col - 1, basinPoints);
        }
        if (col != fieldColSize-1) {
            helper(row, col + 1, basinPoints);
        }
    }


    public long findLowestPoint() {
        List<Integer> listOfLowestPoints = new ArrayList<>();

        for (int row = 0; row < fieldRowSize; row++) {
            for (int col = 0; col < fieldColSize; col++) {

                int numOfHigherNeighbours = countHigherNeighbours(row, col);
                identifyLowestPoints(listOfLowestPoints, row, col, numOfHigherNeighbours);

            }
        }
        increaseLowestPointsWithOne(listOfLowestPoints);

        return listOfLowestPoints.stream()
                .mapToLong(Integer::longValue)
                .sum();
    }

    private int countHigherNeighbours(int row, int col) {
        int rowStart = Math.max(row - 1, 0);
        int rowFinish = Math.min(row + 1, fieldRowSize - 1);
        int colStart = Math.max(col - 1, 0);
        int colFinish = Math.min(col + 1, fieldColSize - 1);

        int numOfHigherPoints = 0;

        for (int curRow = rowStart; curRow <= rowFinish; curRow++) {
            if (curRow == row) {
                continue;
            }
            if (field[curRow][col] > field[row][col]) {
                numOfHigherPoints++;
            }
        }

        for (int curCol = colStart; curCol <= colFinish; curCol++) {
            if (curCol == col) {
                continue;
            }
            if (field[row][curCol] > field[row][col]) {
                numOfHigherPoints++;
            }
        }
        return numOfHigherPoints;
    }

    private void identifyLowestPoints(List<Integer> listOfLowPoints, int row, int col, int numOfHigherPoints) {
        if (isFieldCorner(row, col) && numOfHigherPoints == 2) {
            listOfLowPoints.add(field[row][col]);

        } else if (isFieldOnEdgeButNotCorner(row, col) && numOfHigherPoints == 3) {
            listOfLowPoints.add(field[row][col]);

        } else {
            if (numOfHigherPoints == 4) {
                listOfLowPoints.add(field[row][col]);
            }
        }
    }

    private static void increaseLowestPointsWithOne(List<Integer> listOfLowestPoints) {
        listOfLowestPoints.replaceAll(i -> i + 1);
    }

    private boolean isFieldCorner(int row, int col) {
        return (row == 0 && col == 0) ||
                (row == fieldRowSize - 1 && col == fieldColSize - 1) ||
                (row == 0 && col == fieldColSize - 1) ||
                (row == fieldRowSize - 1 && col == 0);
    }

    private boolean isFieldOnEdgeButNotCorner(int row, int col) {
        return ((row == 0 || row == fieldRowSize - 1 || col == 0 || col == fieldColSize - 1)
                && !isFieldCorner(row, col));
    }

    private void map_init() {
        this.fieldColSize = listOfFieldLines.get(0).length();
        this.fieldRowSize = listOfFieldLines.size();

        this.fieldMap = new HashMap<>();

        for (int i = 0; i < fieldRowSize; i++) {
            for (int j = 0; j < fieldColSize; j++) {
                this.fieldMap.put(new MutablePair<>(i, j), false);
            }
        }
    }

    private void field_init() {
        this.fieldColSize = listOfFieldLines.get(0).length();
        this.fieldRowSize = listOfFieldLines.size();

        this.field = new int[fieldRowSize][fieldColSize];

        for (int i = 0; i < fieldRowSize; i++) {
            for (int j = 0; j < fieldColSize; j++) {
                this.field[i][j] = Character.getNumericValue(listOfFieldLines.get(i).charAt(j));
            }
        }
    }

    private void readFile() throws IOException {
        this.listOfFieldLines = Files.lines(Paths.get("src/main/resources/AdventOfCode/9.txt"))
                .flatMap(s -> Arrays.stream(s.split("\\n"))).map(Object::toString)
                .collect(Collectors.toList());
    }
}
