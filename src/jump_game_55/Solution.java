package jump_game_55;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-03.
 */
public class Solution {
    public boolean canJump(int[] nums) {
        // Search backward for zeros.
        // Notices that we don't care about whether [n-1] is zero.
        // Each zero is the right edge of a black hole.
        // For each black hole, search its right edge.
        // The last index is reachable only if the first index is not inside a black hole.
        for (int i = nums.length - 2; i >= 0; --i) {
            if (nums[i] == 0) {
                // Find the left edge of the "black hole".
                int j = i;
                while (j > 0 && j - 1 + nums[j - 1] <= i)
                    --j;
                if (j == 0 && nums[0] <= i) {
                    // oh no, we can't jump over the black hole from [0].
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    void example0() {
        Assertions.assertTrue(canJump(new int[]{0}));
    }

    @Test
    void example1() {
        Assertions.assertTrue(canJump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test
    void example2() {
        Assertions.assertFalse(canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    void example3() {
        Assertions.assertFalse(canJump(new int[]{2, 3, 1, 1, 4, 3, 2, 1, 0, 4}));
    }

    @Test
    void example4() {
        Assertions.assertTrue(canJump(new int[]{2, 4, 2, 9, 4, 3, 2, 1, 0, 4}));
    }

    @Test
    void example5() {
        Assertions.assertTrue(canJump(new int[]{2, 0, 0}));
        Assertions.assertFalse(canJump(new int[]{2, 0, 0, 1}));
        Assertions.assertTrue(canJump(new int[]{3, 0, 0, 1}));
        Assertions.assertTrue(canJump(new int[]{1, 1, 1, 0}));
    }
}
