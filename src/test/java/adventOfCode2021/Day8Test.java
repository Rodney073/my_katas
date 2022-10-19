package adventOfCode2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day8Test {

    @Test
    public void day8_part1() {
        Day8 day8 = new Day8();
        Assertions.assertEquals(245, day8.countUniqueDigits());
    }

    @Test
    public void day8_part2() {
        Day8 day8 = new Day8();
        Assertions.assertEquals(983026, day8.sumAllOutputDigits());
    }

    @Test
    public void day8_part2b() {
        Day8 day8 = new Day8();
        Assertions.assertEquals(983026, day8.sumAllOutputDigits2());
    }

}