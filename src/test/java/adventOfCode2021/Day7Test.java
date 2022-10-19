package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day7Test {

    @Test
    public void day7_part1() throws IOException {
        Day7 day7 = new Day7();
        Assertions.assertEquals(342730, day7.findCheapestPosition());
    }

    @Test
    public void day7_part2() throws IOException {
        Day7 day7 = new Day7();
        Assertions.assertEquals(92335207, day7.findCheapestPosition_part2());
    }
}