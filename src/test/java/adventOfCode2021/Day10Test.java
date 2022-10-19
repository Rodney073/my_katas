package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    @Test
    public void day10_part1() {
        Day10 day10 = new Day10();
        //System.out.println(day10.calculateSyntaxErrorScore());
        Assertions.assertEquals(311895, day10.calculateSyntaxErrorScore());
    }
}