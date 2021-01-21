package valid_pefect_square_367;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1)
            return true;

        int lo = 1, hi = 1;

        // Init hi to be half length of num in terms of bit.
        // If we had init hi to num/2 or something, we would end up with incorrect answers due to overflow.
        // Besides, it would be quicker if we guess a better hi.
        int n = num;
        while (n > 0) {
            n >>= 2;
            hi <<= 1;
        }

        while (lo <= hi) {
            int mi = (lo + hi) / 2;
            int sq = mi * mi;
            if (sq == num) {
                return true;
            } else if (sq < 0) { // overflow
                hi = mi - 1;
            } else if (sq < num) {
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }

        return false;
    }

    @Test
    void test() {
        Assertions.assertTrue(isPerfectSquare(2147395600));
        Assertions.assertTrue(isPerfectSquare(808201));
        Assertions.assertTrue(isPerfectSquare(1));
        Assertions.assertFalse(isPerfectSquare(2));
        Assertions.assertFalse(isPerfectSquare(3));
        Assertions.assertTrue(isPerfectSquare(4));
        Assertions.assertFalse(isPerfectSquare(5));
        Assertions.assertFalse(isPerfectSquare(6));
        Assertions.assertFalse(isPerfectSquare(7));
        Assertions.assertFalse(isPerfectSquare(8));
        Assertions.assertTrue(isPerfectSquare(9));
        Assertions.assertFalse(isPerfectSquare(10));
        Assertions.assertFalse(isPerfectSquare(11));
        Assertions.assertFalse(isPerfectSquare(12));
        Assertions.assertFalse(isPerfectSquare(13));
        Assertions.assertFalse(isPerfectSquare(14));
        Assertions.assertFalse(isPerfectSquare(15));
        Assertions.assertTrue(isPerfectSquare(16));
        Assertions.assertFalse(isPerfectSquare(17));
        Assertions.assertFalse(isPerfectSquare(31));
    }
}
