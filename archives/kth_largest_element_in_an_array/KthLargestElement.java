package kth_largest_element_in_an_array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class KthLargestElement {
    Solution solution = new Solution();

    @Test
    void exmple0() {
        Assertions.assertEquals(1, solution.findKthLargest(new int[]{1}, 1));
    }

    @Test
    void example1() {
        Assertions.assertEquals(5, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test
    void example2() {
        Assertions.assertEquals(4, solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    void min() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Assertions.assertEquals(0, solution.findKthLargest(nums, nums.length));
    }

    @Test
    void max() {
        Assertions.assertEquals(9, solution.findKthLargest(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 1));
    }
}
