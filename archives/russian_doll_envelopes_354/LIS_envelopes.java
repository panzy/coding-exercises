package russian_doll_envelopes_354;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class LIS_envelopes {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(3, solution.maxEnvelopes(new int[][]{{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}}));
        Assertions.assertEquals(3, solution.maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}}));
    }
}
