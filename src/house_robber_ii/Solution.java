package house_robber_ii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Based on solution #2 to the problem House Robber I.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        // for n <= 3, at most one house can be robbed
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        if (n == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));
        return Math.max(rob(nums, true), rob(nums, false));
    }

    public int rob(int[] nums, boolean robTheFirst) {
        int n = nums.length;

        // Definition of S[i]:
        //
        // S[i] is the answer for the range [0,i] of the array. So,
        //
        // S[i] = max(nums[i] + S[i-2], S[i-1])
        //
        // But i = n - 1 is special because it's influenced by robTheFirst param.

        int s0 = robTheFirst ? nums[0] : 0;
        int s1 = robTheFirst ? s0 : nums[1];
        int s2 = 0;

        for (int i = 2; i < n - 1; ++i) {
            s2 = Math.max(nums[i] + s0, s1);
            s0 = s1;
            s1 = s2;
        }

        return Math.max((robTheFirst ? 0 : nums[n - 1]) + s0, s1);
    }
}

class SolutionTest {
    Solution solution = new Solution();

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
        int[] nums = new int[]{2, 3, 2};
        int expected = 3;
        Assertions.assertEquals(expected, solution.rob(nums));
    }

    @Test
    void example2() {
        int[] nums = new int[]{1, 2, 3, 1};
        int expected = 4;
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
    void example5() {
        int[] nums = new int[]{200, 3, 140, 20, 10};
        int expected = 340;
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
