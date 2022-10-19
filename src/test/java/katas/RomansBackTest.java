package katas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomansBackTest {

    RomansBack romansBack = new RomansBack();

    @Test
    public void testRomanNumTransformer() {
        Assertions.assertEquals("MMXIX", romansBack.transform(2019));

        Assertions.assertEquals("XXXIX", romansBack.transform(39));
        Assertions.assertEquals("CCXLVI", romansBack.transform(246));
        Assertions.assertEquals("DCCLXXXIX", romansBack.transform(789));
        Assertions.assertEquals("MMCDXXI", romansBack.transform(2421));

        Assertions.assertEquals("CLX", romansBack.transform(160));
        Assertions.assertEquals("CCVII", romansBack.transform(207));
        Assertions.assertEquals("MIX", romansBack.transform(1009));
        Assertions.assertEquals("MLXVI", romansBack.transform(1066));

        Assertions.assertEquals("MDCCLXXVI", romansBack.transform(1776));
        Assertions.assertEquals("MCMXVIII", romansBack.transform(1918));
        Assertions.assertEquals("MCMLIV", romansBack.transform(1954));
        Assertions.assertEquals("MMXIV", romansBack.transform(2014));
        Assertions.assertEquals("MMMIX", romansBack.transform(3009));
        Assertions.assertEquals("MMMIV", romansBack.transform(3004));

        Assertions.assertEquals("I", romansBack.transform(1));
        Assertions.assertEquals("II", romansBack.transform(2));
        Assertions.assertEquals("III", romansBack.transform(3));
        Assertions.assertEquals("IV", romansBack.transform(4));

        Assertions.assertEquals("V", romansBack.transform(5));
        Assertions.assertEquals("IX", romansBack.transform(9));
        Assertions.assertEquals("XII", romansBack.transform(12));
        Assertions.assertEquals("XVI", romansBack.transform(16));
        Assertions.assertEquals("XXIX", romansBack.transform(29));
        Assertions.assertEquals("XLIV", romansBack.transform(44));
        Assertions.assertEquals("XLV", romansBack.transform(45));

        Assertions.assertEquals("LXVIII", romansBack.transform(68));
        Assertions.assertEquals("LXXXIII", romansBack.transform(83));
        Assertions.assertEquals("XCVII", romansBack.transform(97));
        Assertions.assertEquals("XCIX", romansBack.transform(99));
        Assertions.assertEquals("D", romansBack.transform(500));
        Assertions.assertEquals("DI", romansBack.transform(501));
        Assertions.assertEquals("DCXLIX", romansBack.transform(649));

        Assertions.assertEquals("DCCXCVIII", romansBack.transform(798));
        Assertions.assertEquals("DCCCXCI", romansBack.transform(891));
        Assertions.assertEquals("M", romansBack.transform(1000));
        Assertions.assertEquals("MIV", romansBack.transform(1004));
        Assertions.assertEquals("MVI", romansBack.transform(1006));
        Assertions.assertEquals("MXXIII", romansBack.transform(1023));
        Assertions.assertEquals("MMXIV", romansBack.transform(2014));
        Assertions.assertEquals("MMMCMXCIX", romansBack.transform(3999));

    }
}