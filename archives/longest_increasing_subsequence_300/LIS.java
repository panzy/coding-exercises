package longest_increasing_subsequence_300;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class LIS {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(4, solution.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        Assertions.assertEquals(4, solution.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        Assertions.assertEquals(1, solution.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
        Assertions.assertEquals(3, solution.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
    }
}
