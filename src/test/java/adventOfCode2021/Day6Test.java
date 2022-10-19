package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class Day6Test {

    @Test
    public void day6_part1() throws IOException {
        Day6 day6 = new Day6();
        Assertions.assertEquals(380758, day6.countNumberOfFishIn80());
    }

    //TODO: Solve it
    @Test
    public void day6_part2() throws IOException {
        Day6 day6 = new Day6();
        //Assertions.assertEquals(380758, day6.countNumberOfFishIn80());
        //System.out.println(day6.countNumberOfFishIn256());
    }
}