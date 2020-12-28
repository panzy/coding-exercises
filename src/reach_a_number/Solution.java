package reach_a_number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * It's all about doing the math.
 * --
 * Zhiyong Pan, 2020-12-28
 */
public class Solution {
    public int reachNumber(int target) {
        // Because it's symmetric.
        if (target < 0)
            target = -target;

        // No matter where we're, we can always reach the next number with a combination
        // of a left and a right movements.
        // So it's guaranteed to be able to reach any target using 2 * target movements.
        int worstAnswer = 2 * target;

        // The n-th move.
        //
        // We don't want to start from 1 because that would be too slow for large targets.
        // We want to start from the n that satisfies:
        //      1 + 2 + 3 + ... + n >= target
        // which means
        //      n * (n + 1) / 2 >= target
        // which in turn means
        //      n >= sqrt(2 * target) - 1
        int n = Math.max(1, (int) (Math.sqrt(2 * target) - 1));

        // Our position before the n-th move.
        int p = (n - 1) * n / 2;

        for (; n <= target; ++n) {
            p += n;

            if (n >= worstAnswer) {
                break;
            }

            // done!
            if (p == target) {
                return n;
            }

            // In this case, we can always reverse one of the previous movements which
            // forwards (p-target)/2 steps, then our final movement will precisely hit the target.
            if (p > target && (p - target) % 2 == 0) {
                return n;
            }

            // It's guaranteed to be able to reach a target which is one step away with two more movements.
            // Though a better plan might be reverse one and add one.
            // So we record the answer and go on searching.
            if (p + 1 == target || p - 1 == target) {
                worstAnswer = Math.min(worstAnswer, n + 2);
            }
        }

        return worstAnswer;
    }

    @Test
    void reachNumber_1() {
        Assertions.assertEquals(1, reachNumber(1));
    }

    @Test
    void reachNumber_2() {
        // 0+1  1+2      ->3
        // 0+1  1-2 -1+3 ->2!
        Assertions.assertEquals(3, reachNumber(2));
    }

    @Test
    void reachNumber_3() {
        // 0+1 1+2 ->3!
        Assertions.assertEquals(2, reachNumber(3));
    }

    @Test
    void reachNumber_4() {
        // 0+1 1+2 3+3  ->6
        // 0-1 -1+2 1+3 ->4!
        Assertions.assertEquals(3, reachNumber(4));
    }

    @Test
    void reachNumber_5() {
        // 0+1 1+2 3+3          ->6
        // 0+1 1+2 3+3 6+4 10-5 ->5!
        Assertions.assertEquals(5, reachNumber(5));
    }

    @Test
    void reachNumber_6() {
        // 0+1 1+2 3+3 ->6!
        Assertions.assertEquals(3, reachNumber(6));
    }

    @Test
    void reachNumber_7() {
        // 0+1 1+2 3+3          ->6
        // 0+1 1+2 3+3 6-4 2+5  ->7!
        Assertions.assertEquals(5, reachNumber(7));
    }

    @Test
    void reachNumber_8() {
        // 0+1 1+2  3+3 6+4 ->10
        // 0-1 -1+2 1+3 4+4 ->8!
        Assertions.assertEquals(4, reachNumber(8));
    }

    @Test
    void reachNumber_9() {
        // 0+1 1+2 3+3 6+4           ->10
        // 0+1 1+2 3-3 0+4 4+5       ->9!
        Assertions.assertEquals(5, reachNumber(9));
    }

    @Test
    void reachNumber_10() {
        // 0+1 1+2 3+3 6+4 ->10!
        Assertions.assertEquals(4, reachNumber(10));
    }

    @Test
    void reachNumber_11() {
        // 0+1 1+2  3+3 6+4 10+5     ->15
        // 0+1 1-2 -1+3 2+4  6+5     ->11!
        Assertions.assertEquals(5, reachNumber(11));
    }

    @Test
    void reachNumber_minus1() {
        Assertions.assertEquals(1, reachNumber(-1));
    }

    @Test
    void reachNumber_minus2() {
        Assertions.assertEquals(3, reachNumber(-2));
    }
}
