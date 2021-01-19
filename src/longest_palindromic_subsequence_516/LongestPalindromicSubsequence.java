package longest_palindromic_subsequence_516;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class LongestPalindromicSubsequence {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(4, solution.longestPalindromeSubseq("bbbab"));
        Assertions.assertEquals(2, solution.longestPalindromeSubseq("cbbd"));
        Assertions.assertEquals(5, solution.longestPalindromeSubseq("babncana")); // "anana"
    }
}
