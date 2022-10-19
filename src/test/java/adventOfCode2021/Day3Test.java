package adventOfCode2021;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day3Test {

    @Test
    public void getPowerConsumption_test() throws IOException {
        Day3 day3 = new Day3();
        assertEquals(4118544, day3.getPowerConsumption());
    }

    @Test
    public void findLifeSupportRating_test() throws IOException {
        Day3 day3 = new Day3();
        assertEquals(3832770, day3.findLifeSupportRating());
    }
}