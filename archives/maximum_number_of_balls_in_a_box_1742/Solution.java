package maximum_number_of_balls_in_a_box_1742;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-30.
 */
public class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] cnts = new int[50];
        for (int ball = lowLimit; ball <= highLimit; ++ball) {
            int box = 0;
            int ba = ball;
            while (ba > 0) {
                box += ba % 10;
                ba /= 10;
            }
            ++cnts[box];
        }

        int ans = cnts[1];
        for (int c : cnts) {
            ans = Math.max(ans, c);
        }

        return ans;
    }

    @Test
    void example() {
        Assertions.assertEquals(1,  countBalls(1, 1));
        Assertions.assertEquals(2,  countBalls(1, 10));
        Assertions.assertEquals(2,  countBalls(5, 15));
        Assertions.assertEquals(2,  countBalls(19, 28));
        countBalls(1, 100_000);
    }
}
