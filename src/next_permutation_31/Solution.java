package next_permutation_31;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // Search for [i] < [i+1]
        int i = n - 2;
        for (; i >= 0; --i) {
            if (nums[i] < nums[i + 1])
                break;
        }

        if (i == -1) {
            // Not found. Reverse the whole number.
            reverse(nums, 0, n);
        } else {
            // Search for [j] > [i] where j > i from right.
            int j = n - 1;
            for (; j > i; --j) {
                if (nums[j] > nums[i])
                    break;
            }

            // Swap [j] with [i].
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;

            // Range [i+1, n) must be in descent order.
            // Now we reverse it to make the answer correct.
            reverse(nums, i + 1, n);
        }
    }

    private void reverse(int[] nums, int begin, int end) {
        for (int j = begin, k = end - 1; j < k; ++j, --k) {
            int t = nums[j];
            nums[j] = nums[k];
            nums[k] = t;
        }
    }

    @Test
    void example1() {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{1, 3, 2}, nums);
    }

    @Test
    void example2() {
        int[] nums = {3, 2, 1};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void example3() {
        int[] nums = {1, 1, 5};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{1, 5, 1}, nums);
    }

    @Test
    void example4() {
        int[] nums = {1};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void example5() {
        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{2, 1, 3}, nums);
    }

    @Test
    void example6() {
        int[] nums = {2, 3, 1};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{3, 1, 2}, nums);
    }

    @Test
    void example7() {
        int[] nums = {1, 2, 3, 9, 8, 7, 6};
        nextPermutation(nums);
        Assertions.assertArrayEquals(new int[]{1, 2, 6, 3, 7, 8, 9}, nums);
    }
}
