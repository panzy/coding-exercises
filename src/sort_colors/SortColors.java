package sort_colors;

import _lib.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class SortColors {
    SolutionFollowup2 solution = new SolutionFollowup2();

    @Test
    void example1() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        Assertions.assertTrue(IntArrays.isSorted(nums, 0, nums.length));
    }

    @Test
    void example2() {
        int[] nums = {2, 0, 1};
        solution.sortColors(nums);
        Assertions.assertTrue(IntArrays.isSorted(nums, 0, nums.length));
    }

    @Test
    void example3() {
        int[] nums = {0};
        solution.sortColors(nums);
        Assertions.assertTrue(IntArrays.isSorted(nums, 0, nums.length));
    }

    @Test
    void example4() {
        int[] nums = {1};
        solution.sortColors(nums);
        Assertions.assertTrue(IntArrays.isSorted(nums, 0, nums.length));
    }
}
