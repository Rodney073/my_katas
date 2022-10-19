package adventOfCode2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Day4 {
    BingoData bingoData;

    public Day4(String filePath) {
        this.bingoData = readFileToArrayList(filePath);
    }

    public Integer findLastWinningTable() {

        List<Integer> listOfWinningTables = new ArrayList<>();
        List<Score> scoreList = new ArrayList<>();
        Integer winningTable = null;

        for (int numberIndex = 0; numberIndex < bingoData.getNumbers().size(); numberIndex++) {

            int actualNum = bingoData.getNumbers().get(numberIndex);
            int numOfBingoTables = bingoData.getListOfMatrixes().size();
            addToScoreList(actualNum ,numOfBingoTables, scoreList);

            if (numberIndex >= 4) {

                Map<Integer, List<Score>> scoresToTable = scoreList.stream().collect(groupingBy(Score::getTable));
                for (Map.Entry<Integer, List<Score>> scoresInTables : scoresToTable.entrySet()) {

                    Integer actualTable = scoresInTables.getKey();
                    List<Score> actualScoreList = scoresInTables.getValue();

                    if (actualScoreList.size() >= 5) {
                        if (maxScorePointInRow(actualScoreList) == 5 || maxScorePointInCol(actualScoreList) == 5) {
                            if (listOfWinningTables.size() < 99 && !listOfWinningTables.contains(actualTable)) {
                                listOfWinningTables.add(actualTable);
                            } else if (winningTable == null && listOfWinningTables.size() == 99) {
                                winningTable = findMissingNumberInList(listOfWinningTables);
                            }
                        }
                    }
                    if (winningTable!= null) {
                        Integer finalWinningTable = winningTable;
                        List<Score> scoresInLastTable = actualScoreList.stream().filter(s -> Objects.equals(s.getTable(), finalWinningTable)).collect(Collectors.toList());
                        if (!scoresInLastTable.isEmpty()) {
                            if (maxScorePointInRow(scoresInLastTable) == 5 || maxScorePointInCol(scoresInLastTable) == 5) {
                                return calculateResult(actualScoreList, actualTable, actualNum);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public Score findWinnerTable() {

        List<Score> scoreList = new ArrayList<>();

        Integer winnerRow = null;
        Integer winnerCol = null;

        for (int numberIndex = 0; numberIndex < bingoData.getNumbers().size(); numberIndex++) {

            int actualNum = bingoData.getNumbers().get(numberIndex);
            int numOfBingoTables = bingoData.getListOfMatrixes().size();

            addToScoreList(actualNum ,numOfBingoTables, scoreList);

            if (numberIndex >= 4) {

                Map<Integer, List<Score>> scoresToTable = scoreList.stream().collect(groupingBy(Score::getTable));
                for (Map.Entry<Integer, List<Score>> scoresInTables : scoresToTable.entrySet()) {

                    Integer actualTable = scoresInTables.getKey();
                    List<Score> actualScoreList = scoresInTables.getValue();

                    if (actualScoreList.size() >= 5) {
                        if (maxScorePointInRow(actualScoreList) == 5) {
                            winnerRow = getWinnerRow(actualScoreList);
                            return new Score(actualNum, actualTable, winnerRow, winnerCol);
                        }
                        if (maxScorePointInCol(actualScoreList) == 5) {
                            winnerCol = getWinnerCol(actualScoreList);
                            return new Score(actualNum, actualTable, winnerRow, winnerCol);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Integer findFinalScore() {
        List<Score> scoreList = new ArrayList<>();

        for (int numberIndex = 0; numberIndex < bingoData.getNumbers().size(); numberIndex++) {

            int actualNum = bingoData.getNumbers().get(numberIndex);
            int numOfBingoTables = bingoData.getListOfMatrixes().size();

            addToScoreList(actualNum ,numOfBingoTables, scoreList);

            if (numberIndex >= 4) {

                Map<Integer, List<Score>> scoresToTable = scoreList.stream().collect(groupingBy(Score::getTable));
                for (Map.Entry<Integer, List<Score>> scoresInTables : scoresToTable.entrySet()) {

                    Integer actualTable = scoresInTables.getKey();
                    List<Score> actualScoreList = scoresInTables.getValue();

                    if (actualScoreList.size() >= 5) {
                        if (maxScorePointInRow(actualScoreList) == 5 || maxScorePointInCol(actualScoreList) == 5) {
                            return calculateResult(actualScoreList, actualTable, actualNum);
                        }
                    }
                }
            }
        }
        return null;
    }

    private void addToScoreList(int actualNum, int numOfBingoTables, List<Score> scoreList) {

        for (int tableIndex = 0; tableIndex < numOfBingoTables; tableIndex++) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (actualNum == bingoData.getListOfMatrixes().get(tableIndex)[row][col]) {
                        scoreList.add(new Score(actualNum, tableIndex, row, col));
                    }
                }
            }
        }
    }

    public int findMissingNumberInList(List<Integer> listOfNumbers) {
        int sum = listOfNumbers.stream().mapToInt(Integer::intValue).sum();
        int total = (listOfNumbers.size() + 1) * listOfNumbers.size() / 2;

        return total - sum;
    }
    
    private int calculateResult(List<Score> scoreList, Integer actualTable, Integer actualNum) {

        int sumOfMarkedNums = scoreList.stream().mapToInt(Score::getNumber).sum();

        int sumAll = Arrays.stream(bingoData.getListOfMatrixes().get(actualTable))
                .flatMapToInt(Arrays::stream)
                .sum();

        return (sumAll - sumOfMarkedNums) * actualNum;
    }

    private static Long maxScorePointInRow(List<Score> scoreList) {
        return scoreList.stream()
                .map(Score::getRow)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(null);
    }

    private static Long maxScorePointInCol(List<Score> scoreList) {
        return scoreList.stream()
                .map(Score::getCol)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(null);
    }

    private static Integer getWinnerRow(List<Score> tableWithMin5Scores) {
        return tableWithMin5Scores.stream()
                .map(Score::getRow)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), 5L))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet())
                .iterator()
                .next();
    }

    private static Integer getWinnerCol(List<Score> tableWithMin5Scores) {
        return tableWithMin5Scores.stream()
                .map(Score::getCol)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), 5L))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet()).iterator().next();
    }

    public int calculateResultWithMarkedNums(Score score) {
        int sum = 0;

        if (score.getRow() != null) {
            for (int i = 0; i < 5; i++) {
                sum = sum + bingoData.getListOfMatrixes().get(score.getTable())[score.getRow()][i];
            }
        }

        if (score.getCol() != null) {
            for (int i = 0; i < 5; i++) {
                sum = sum + bingoData.getListOfMatrixes().get(score.getTable())[i][score.getCol()];
            }
        }

        return sum * score.getNumber();
    }

    public int calculateResultWithUnmarkedNums(Score score) {
        int sumOfMarkedNums = 0;

        if (score.getRow() != null) {
            for (int i = 0; i < 5; i++) {
                sumOfMarkedNums = sumOfMarkedNums + bingoData.getListOfMatrixes().get(score.getTable())[score.getRow()][i];
            }
        }

        if (score.getCol() != null) {
            for (int i = 0; i < 5; i++) {
                sumOfMarkedNums = sumOfMarkedNums + bingoData.getListOfMatrixes().get(score.getTable())[i][score.getCol()];
            }
        }

        int sumAll = Arrays.stream(bingoData.getListOfMatrixes().get(score.getTable()))
                .flatMapToInt(Arrays::stream)
                .sum();

        return (sumAll - sumOfMarkedNums) * score.getNumber();
    }

    public BingoData readFileToArrayList(String filePath) {

        List<Integer> numbers = new ArrayList<>();

        List<int[][]> listOfMatrixes = new ArrayList<>();
        int[][] myTable = new int[5][5];
        int countTableRows = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String next, line = reader.readLine();
            for (boolean first = true, last = (line == null); !last; first = false, line = next) {

                last = ((next = reader.readLine()) == null);
                if (first) {
                    String[] stringArrayOfNums = line.split(",");
                    for (String s : stringArrayOfNums) {
                        numbers.add(Integer.parseInt(s));
                    }
                } else if (last) {
                    line = line.replaceFirst("^\\s*", "");
                    String[] stringArrayOfNums = line.split("\\s+");
                    for (int i = 0; i < stringArrayOfNums.length; i++) {
                        myTable[countTableRows][i] = Integer.parseInt(stringArrayOfNums[i]);
                    }
                    listOfMatrixes.add(myTable);

                } else {

                    if (!line.isEmpty()) {
                        line = line.replaceFirst("^\\s*", "");

                        String[] stringArrayOfNums = line.split("\\s+");
                        for (int i = 0; i < stringArrayOfNums.length; i++) {
                            myTable[countTableRows][i] = Integer.parseInt(stringArrayOfNums[i]);
                        }

                        countTableRows++;
                    } else {
                        if (!Arrays.deepEquals(myTable, new int[5][5])) {
                            listOfMatrixes.add(myTable);
                            myTable = new int[5][5];
                        }
                        countTableRows = 0;
                    }
                }
            }
        } catch (RuntimeException | IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return new BingoData(numbers, listOfMatrixes);
    }
}
