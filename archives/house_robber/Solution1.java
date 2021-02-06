package house_robber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Added a cache to solution #0, then it becomes a dynamic planning algorithm, which is super fast.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
class Solution1 {
    int[] cache;

    public int rob(int[] nums) {
        cache = new int[nums.length];
        Arrays.fill(cache, -1);

        return rob(nums, 0);
    }

    int rob(int[] nums, int begin) {
        if (begin >= nums.length)
            return 0;

        if (cache[begin] != -1)
            return cache[begin];

        if (begin + 1 == nums.length)
            return cache[begin] = nums[begin];

        if (begin + 2 == nums.length)
            return cache[begin] = Math.max(nums[begin], nums[begin + 1]);

        // two options: either rob [begin] or rob [begin + 1].
        return cache[begin] = Math.max(
                nums[begin] + (begin + 2 < nums.length ? rob(nums, begin + 2) : 0),
                nums[begin + 1] + (begin + 3 < nums.length ? rob(nums, begin + 3) : 0)
        );
    }
}

class SolutionTest {
    Solution1 solution = new Solution1();

    @Test
    void empty() {
        int[] nums = new int[0];
        int expected = 0;
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
