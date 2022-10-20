package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    @Test
    public void day10_part1() {
        Day10 day10 = new Day10();
        Assertions.assertEquals(311895, day10.calculateSyntaxErrorScore());
    }

    @Test
    public void day10_part2() {
        Day10 day10 = new Day10();
        Assertions.assertEquals(2904180541L, day10.calculateMiddleScore());
    }
}