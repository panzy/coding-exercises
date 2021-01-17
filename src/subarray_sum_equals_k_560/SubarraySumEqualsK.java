package subarray_sum_equals_k_560;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class SubarraySumEqualsK {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(2, solution.subarraySum(new int[]{1, 1, 1}, 2));
    }

    @Test
    void example2() {
        Assertions.assertEquals(2, solution.subarraySum(new int[]{1, 2, 3}, 3));
    }

    @Test
    void example3() {
        Assertions.assertEquals(4, solution.subarraySum(new int[]{1, 0, 0, 1, 1}, 2));
    }

    @Test
    void example4() {
        Assertions.assertEquals(0, solution.subarraySum(new int[]{1}, 0));
    }

    @Test
    void example5() {
        Assertions.assertEquals(3, solution.subarraySum(new int[]{1, 0, 0, 1, 1}, 0));
    }
}
