package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day5Test {

    @Test
    public void day5_part1() {
        Day5 day5 = new Day5("src/main/resources/AdventOfCode/5.txt");
        Assertions.assertEquals(5632, day5.countOverlapsWithoutDiagonals());
    }

    @Test
    public void day5_part2() {
        Day5 day5 = new Day5("src/main/resources/AdventOfCode/5.txt");
        Assertions.assertEquals(22213, day5.countOverlapsWithDiagonals());
    }
}