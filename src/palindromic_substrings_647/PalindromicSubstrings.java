package palindromic_substrings_647;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class PalindromicSubstrings {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(0, solution.countSubstrings(""));
        Assertions.assertEquals(1, solution.countSubstrings("a"));
        Assertions.assertEquals(2, solution.countSubstrings("ab"));
        Assertions.assertEquals(3, solution.countSubstrings("abc"));
        Assertions.assertEquals(3, solution.countSubstrings("aa"));
        Assertions.assertEquals(6, solution.countSubstrings("aaa"));
        Assertions.assertEquals(10, solution.countSubstrings("aaaa"));
        Assertions.assertEquals(9, solution.countSubstrings("aabaa"));
    }
}
