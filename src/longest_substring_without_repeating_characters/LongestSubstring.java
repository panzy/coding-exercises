package longest_substring_without_repeating_characters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class LongestSubstring {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void example2() {
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void example3() {
        Assertions.assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void example4() {
        Assertions.assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void example5() {
        Assertions.assertEquals(1, solution.lengthOfLongestSubstring("a"));
    }

    @Test
    void example6() {
        Assertions.assertEquals(2, solution.lengthOfLongestSubstring("ab"));
    }

    @Test
    void example7() {
        Assertions.assertEquals(6, solution.lengthOfLongestSubstring("ab ~@6b"));
    }
}
