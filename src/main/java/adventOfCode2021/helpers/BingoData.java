package adventOfCode2021.helpers;

import java.util.List;

public class BingoData {

    private final List<Integer> numbers;
    private final List<int[][]> listOfMatrixes;

    public BingoData(List<Integer> numbers, List<int[][]> listOfMatrixes) {
        this.numbers = numbers;
        this.listOfMatrixes = listOfMatrixes;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<int[][]> getListOfMatrixes() {
        return listOfMatrixes;
    }
}
