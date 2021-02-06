package decode_ways;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    void numDecodings_example1() {
        String s = "12";
        int expected = 2;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_example2() {
        String s = "226";
        int expected = 3;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_example3() {
        String s = "0";
        int expected = 0;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_1111() {
        String s = "1111";
        int expected = 5;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_11111() {
        String s = "11111";
        int expected = 8;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_3333() {
        String s = "3333";
        int expected = 1;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_26226() {
        String s = "26226";
        int expected = 6;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_27() {
        String s = "27";
        int expected = 1;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }

    @Test
    void numDecodings_largeN() {
        String s = "111111111111111111111111111111111111111111111";
        int expected = 1836311903;
        Assertions.assertEquals(expected, solution.numDecodings(s));
    }
}
