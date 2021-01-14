package minimum_operations_to_reduce_x_to_zero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
