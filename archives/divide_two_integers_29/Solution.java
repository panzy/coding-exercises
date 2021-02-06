package divide_two_integers_29;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This solution doesn't use bit shifting, which is essentially a multiplication by 2 or a division by 2.
 *
 * Created by Zhiyong Pan on 2021-02-03.
 */
public class Solution {
    public int divide(int dividend, int divisor) {
        final long MAX = 2147483647; // (int) (Math.pow(2, 31) - 1);
        final long MIN = -MAX - 1;

        // This the only scenario where the output will overflow.
        // We detect it here only for efficiency's consideration.
        // Removing it doesn't affect the whole program's correctness.
        if (dividend == MIN && divisor == -1)
            return (int) MAX;

        long ans = 0;
        boolean neg = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        // a / b
        // Notice that a might overflow, hence the long datatype.
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        // Essentially we are repeatedly doing
        //      a -= mul * b
        // and double mul each time.
        // However, we archive that without multiplication operator.
        while (a >= b) {
            int mul = 1;
            long div = b;
            while (a >= div) {
                a -= div;
                ans += mul;
                mul += mul;
                div += div;
            }
        }

        ans = neg ? -ans : ans;

        // Assume we are dealing with an environment that could only store integers within the 32-bit
        // signed integer range: [−231,  231 − 1].
        // For this problem, assume that your function returns 231 − 1 when the division result overflows.
        if (ans > MAX)
            ans = MAX;

        return (int) ans;
    }

    @Test
    void example1() {
        Assertions.assertEquals(3, divide(10, 3));
    }

    @Test
    void example2() {
        Assertions.assertEquals(-2, divide(7, -3));
    }

    @Test
    void example3() {
        Assertions.assertEquals(0, divide(0, 1));
    }

    @Test
    void example4() {
        Assertions.assertEquals(1, divide(1, 1));
    }

    @Test
    void example5() {
        Assertions.assertEquals(-333, divide(-1000, 3));
        Assertions.assertEquals(333, divide(1000, 3));
        Assertions.assertEquals((int)(Math.pow(2, 31) - 1), divide((int) (Math.pow(2, 31) - 1), 1));
        Assertions.assertEquals((int)(Math.pow(2, 31) - 1), divide((int) -Math.pow(2, 31), -1));
        Assertions.assertEquals(16, divide(32, 2));
        Assertions.assertEquals(6, divide(13, 2));
        Assertions.assertEquals(6, divide(12, 2));
        Assertions.assertEquals(5, divide(10, 2));
    }
}
