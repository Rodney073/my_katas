package adventOfCode2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    @Test
    public void calculateFinalPosition_Test() {
        Day2 day2 = new Day2();
        assertEquals(1692075, day2.calculateFinalPosition());
    }

    @Test
    public void calculateFinalPosition2_Test() {
        Day2 day2 = new Day2();
        assertEquals(1749524700, day2.calculateFinalPosition2());
    }
}