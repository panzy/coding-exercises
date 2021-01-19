package longest_palindromic_substring_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class LongestPalindromicSubstring {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals("a", solution.longestPalindrome("a"));
        Assertions.assertEquals("a", solution.longestPalindrome("ab"));
        Assertions.assertEquals("bb", solution.longestPalindrome("abb"));
        Assertions.assertEquals("bb", solution.longestPalindrome("cbbd"));
//        Assertions.assertEquals("bab", solution.longestPalindrome("babad"));
        Assertions.assertEquals("babab", solution.longestPalindrome("babab"));
        Assertions.assertEquals("badab", solution.longestPalindrome("babadab"));
        Assertions.assertEquals("aaaaa", solution.longestPalindrome("aaaaa"));
        Assertions.assertEquals("aaaaaa", solution.longestPalindrome("aaaaaa"));
        Assertions.assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", solution.longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        Assertions.assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", solution.longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }
}
