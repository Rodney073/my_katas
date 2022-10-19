package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day9Test {
    @Test
    public void day9_part1() throws IOException {
        Day9 day9 = new Day9();
        Assertions.assertEquals(489, day9.findLowestPoint());
    }

    @Test
    public void day9_part2() throws IOException {
        Day9 day9 = new Day9();
        Assertions.assertEquals(1056330, day9.findLargestBasin());
    }
}