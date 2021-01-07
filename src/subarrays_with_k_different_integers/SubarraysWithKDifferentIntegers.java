package subarrays_with_k_different_integers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class SubarraysWithKDifferentIntegers {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        Assertions.assertEquals(7, solution.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    @Test
    void example2() {
        Assertions.assertEquals(3, solution.subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }

    @Test
    void example3() {
        Assertions.assertEquals(1, solution.subarraysWithKDistinct(new int[]{1}, 1));
    }

    @Test
    void example4() {
        Assertions.assertEquals(3, solution.subarraysWithKDistinct(new int[]{1, 1}, 1));
    }

    @Test
    void test28() {
        Assertions.assertEquals(8, solution.subarraysWithKDistinct(new int[]{2, 1, 1, 1, 2}, 1));
    }

    // large-scale worst case
    @Test
    void largeN() {
        int[] A = new int[20000];
        Arrays.fill(A, 1);
        Assertions.assertEquals(200010000, solution.subarraysWithKDistinct(A, 1));
    }
}
