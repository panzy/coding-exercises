package house_robber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Improved solution #1 by:
 * 1. replacing recursion with iteration.
 * 2. reduce cache size from O(n) to O(1).
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
class Solution2 {
    public int rob(int[] nums) {
        int n = nums.length;

        // Starting from the last of the array,
        // each iteration depends on the results of the two previous iterations.
        // So the iteration starts from i = n - 3.

        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        // For a iteration i,
        // a0, a1, and a2 are equivalent to
        // cache[i], cache[i + 1], and cache[i + 2] in solution #1, respectively.
        int a2 = nums[n - 1];
        int a1 = Math.max(nums[n - 2], a2);
        int a0 = 0;

        for (int i = n - 3; i >= 0; --i) {
            a0 = Math.max(nums[i] + a2, a1);
            a2 = a1;
            a1 = a0;
        }

        return a0;
    }
}

class Solution2Test {
    Solution2 solution = new Solution2();

    @Test
    void empty() {
        int[] nums = new int[0];
        int expected = 0;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void single() {
        int[] nums = new int[]{1};
        int expected = 1;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void example1() {
        int[] nums = new int[]{1, 2, 3, 1};
        int expected = 4;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void example2() {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        int expected = 12;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void example3() {
        int[] nums = new int[]{1, 1};
        int expected = 1;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void example4() {
        int[] nums = new int[]{1, 2};
        int expected = 2;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void largeN() {
        int[] nums = new int[]{226, 174, 214, 16, 218, 48, 153, 131, 128, 17, 157, 142, 88, 43, 37, 157, 43, 221, 191, 68, 206, 23, 225, 82, 54, 118, 111, 46, 80, 49, 245, 63, 25, 194, 72, 80, 143, 55, 209, 18, 55, 122, 65, 66, 177, 101, 63, 201, 172, 130, 103, 225, 142, 46, 86, 185, 62, 138, 212, 192, 125, 77, 223, 188, 99, 228, 90, 25, 193, 211, 84, 239, 119, 234, 85, 83, 123, 120, 131, 203, 219, 10, 82, 35, 120, 180, 249, 106, 37, 169, 225, 54, 103, 55, 166, 124};
        int expected = 7102;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void largeN_allZero() {
        int[] nums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int expected = 0;
        Assertions.assertEquals(expected, solution.rob(nums));
    }
}
