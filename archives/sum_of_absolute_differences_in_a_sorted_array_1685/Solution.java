package sum_of_absolute_differences_in_a_sorted_array_1685;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * To do it in O(N), compute incrementally:
 *
 * res[i] = res[i - 1] + i * delta - (n - i) * delta, where
 * delta = nums[i] - nums[i - 1].
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // Compute res[0].
        res[0] = 0;
        for (int i = 1; i < n; ++i) {
            res[0] += nums[i] - nums[0];
        }

        // Compute others
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] + (2 * i - n) * (nums[i] - nums[i - 1]);
        }

        return res;
    }

    @Test
    void testSumOfDifferences() {
        Assertions.assertArrayEquals(new int[]{4, 3, 5}, getSumAbsoluteDifferences(new int[]{2, 3, 5}));
        Assertions.assertArrayEquals(new int[]{24, 15, 13, 15, 21}, getSumAbsoluteDifferences(new int[]{1, 4, 6, 8, 10}));
    }
}
