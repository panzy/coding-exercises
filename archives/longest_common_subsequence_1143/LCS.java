package longest_common_subsequence_1143;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class LCS {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(3, solution.longestCommonSubsequence("abcde", "ace"));
        Assertions.assertEquals(3, solution.longestCommonSubsequence("abc", "abc"));
        Assertions.assertEquals(0, solution.longestCommonSubsequence("abc", "def"));
    }
}
