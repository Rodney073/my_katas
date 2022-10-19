package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day4Test {

    @Test
    public void bingo_test(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4test.txt");
        day4test.findWinnerTable();
    }

    @Test
    public void bingo_SumOfMarked(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        System.out.println(day4test.calculateResultWithMarkedNums(day4test.findWinnerTable()));
    }

    @Test
    public void bingo_SumOfUnmarked(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        System.out.println(day4test.calculateResultWithUnmarkedNums(day4test.findWinnerTable()));
    }

    @Test
    public void printMatrixByIndex(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        System.out.println(Arrays.deepToString(day4test.bingoData.getListOfMatrixes().get(60)));
    }

    @Test
    public void findFinalScore(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        Assertions.assertEquals(64084, day4test.findFinalScore());
    }

    @Test
    public void findMissingNumTest(){
        List<Integer> list2 = Arrays.asList(0, 1, 2, 3, 5 );
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        day4test.findMissingNumberInList(list2);
    }
    @Test
    public void findLastWinning(){
        Day4 day4test = new Day4("src/main/resources/AdventOfCode/4.txt");
        Assertions.assertEquals(12833, day4test.findLastWinningTable());
    }

}