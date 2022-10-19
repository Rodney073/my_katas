package adventOfCode2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    public void numOfLargerMeasurements_Test() {
        Day1 day1 = new Day1();
        assertEquals(1316, day1.numOfLargerMeasurements());
    }

    @Test
    public void numOfLargerMeasurements2_Test() {
        Day1 day1 = new Day1();
        assertEquals(1344, day1.numOfLargerMeasurements2());
    }
}