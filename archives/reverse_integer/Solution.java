package reverse_integer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Without resorting to string.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution {
    public int reverse(int x) {
        // maximal positive absolute value
        final long MAX_POSITIVE_ABS = (long) (Math.pow(2, 31) - 1); // 2147483647l
        // maximal negative absolute value
        final long MAX_NEGATIVE_ABS = (long) (Math.pow(2, 31)); // 2147483648l

        int sign = x >= 0 ? 1 : -1;
        long xx = Math.abs((long) x); // convert int to long to avoid overflow during calculation

        long n = xx % 10;
        xx /= 10;

        while (xx > 0) {
            n = n * 10 + xx % 10; // won't overflow because n is a long
            if ((sign == 1 && n > MAX_POSITIVE_ABS) || (sign == -1 && n > MAX_NEGATIVE_ABS)) {
                return 0; // overflow for an int
            }
            xx /= 10;
        }
        return (int) (n * sign);
    }

    @Test
    void example0() {
        Assertions.assertEquals(0, reverse(0));
    }

    @Test
    void example1() {
        Assertions.assertEquals(321, reverse(123));
    }

    @Test
    void example2() {
        Assertions.assertEquals(1, reverse(1));
    }

    @Test
    void example3() {
        Assertions.assertEquals(-1, reverse(-1));
    }

    @Test
    void example4() {
        Assertions.assertEquals(-12, reverse(-21));
    }

    @Test
    void example5() {
        Assertions.assertEquals(-123, reverse(-321));
    }

    @Test
    void example6() {
        Assertions.assertEquals(-6, reverse(-600));
    }

    @Test
    void example7() {
        Assertions.assertEquals(0, reverse(-2147483648));
    }

    @Test
    void test1027_overflow() {
        Assertions.assertEquals(0, reverse(1534236469));
    }
}
