package minimum_operations_to_reduce_x_to_zero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class MinimumOperationsReduce {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] nums = {1, 1, 4, 2, 3};
        int x = 5;
        int expected = 2;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example2() {
        int[] nums = {5, 6, 7, 8, 9};
        int x = 4;
        int expected = -1;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example3() {
        int[] nums = {3, 2, 20, 1, 1, 3};
        int x = 10;
        int expected = 5;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example4() {
        int[] nums = {10, 1, 2, 3, 4};
        int x = 10;
        int expected = 1;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example5() {
        int[] nums = {1, 2, 3, 4, 10};
        int x = 10;
        int expected = 1;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example6() {
        int[] nums = {11, 1, 2, 3, 4};
        int x = 10;
        int expected = 4;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example7() {
        int[] nums = {1, 2, 3, 4, 11};
        int x = 10;
        int expected = 4;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example8() {
        int[] nums = {1, 2, 3, 4, 11, 7, 3};
        int x = 10;
        int expected = 2;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void example9() {
        int[] nums = {4, 11, 0, 0, 0};
        int x = 10;
        int expected = -1;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }

    @Test
    void worst() {
        int[] nums = new int[1000];
        Arrays.fill(nums, 1);
        int x = nums.length - 1;
        int expected = nums.length - 1;
        Assertions.assertEquals(expected, solution.minOperations(nums, x));
    }
}
