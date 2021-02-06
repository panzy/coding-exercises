package sum_of_square_numbers_633;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-25.
 */
public class Solution {
    public boolean judgeSquareSum(int c) {
        double sqrtC = Math.sqrt(c);

        if ((int) sqrtC == c)
            return true;

        for (int a = 0; a <= sqrtC; ++a) {
            double b = Math.sqrt(c - a * a);
            if ((int) b == b)
                return true;
        }
        return false;
    }

    @Test
    void testJudgeSquareSum() {
        Assertions.assertTrue(judgeSquareSum(0));
        Assertions.assertTrue(judgeSquareSum(1));
        Assertions.assertTrue(judgeSquareSum(2));
        Assertions.assertFalse(judgeSquareSum(3));
        Assertions.assertTrue(judgeSquareSum(4));
        Assertions.assertTrue(judgeSquareSum(5));
        Assertions.assertFalse(judgeSquareSum(2147483643));
    }
}
