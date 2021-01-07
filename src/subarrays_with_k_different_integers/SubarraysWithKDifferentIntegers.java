package subarrays_with_k_different_integers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class SubarraysWithKDifferentIntegers {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(7, solution.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }
}
