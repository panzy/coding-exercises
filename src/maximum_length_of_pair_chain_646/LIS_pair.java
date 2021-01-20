package maximum_length_of_pair_chain_646;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class LIS_pair {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(2, solution.findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        Assertions.assertEquals(2, solution.findLongestChain(new int[][]{{3, 4}, {2, 3}, {1, 2}}));
        Assertions.assertEquals(2, solution.findLongestChain(new int[][]{{1, 2}, {2, 3}, {2, 4}, {3, 4}}));
        Assertions.assertEquals(2, solution.findLongestChain(new int[][]{{1, 2}, {2, 3}, {2, 4}, {3, 5}}));
        Assertions.assertEquals(4, solution.findLongestChain(new int[][]{{0, 1}, {2, 3}, {0, 1}, {6, 7}, {4, 5}, {6, 7}}));
        Assertions.assertEquals(1, solution.findLongestChain(new int[][]{{7, 8}, {7, 8}, {7, 8}, {7, 8}, {7, 8}, {7, 8}, {7, 8}}));
        Assertions.assertEquals(5, solution.findLongestChain(new int[][]{{8, 9}, {20, 21}, {8, 9}, {6, 7}, {16, 17}, {18, 19}}));
    }
}
