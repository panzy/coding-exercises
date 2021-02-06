package check_if_two_string_arrays_are_equivalent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class CheckStringArraysEquivalent {
    Solution solution = new Solution();

    @Test
    void example1() {
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};
        Assertions.assertTrue(solution.arrayStringsAreEqual(word1, word2));
    }

    @Test
    void example2() {
        String[] word1 = {"a", "cb"};
        String[] word2 = {"a", "bc"};
        Assertions.assertFalse(solution.arrayStringsAreEqual(word1, word2));
    }

    @Test
    void example3() {
        String[] word1 = {};
        String[] word2 = {"a", "bc"};
        Assertions.assertFalse(solution.arrayStringsAreEqual(word1, word2));
    }

    @Test
    void example4() {
        String[] word1 = {"a", "bc"};
        String[] word2 = {};
        Assertions.assertFalse(solution.arrayStringsAreEqual(word1, word2));
    }

    @Test
    void example5() {
        String[] word1 = {};
        String[] word2 = {};
        Assertions.assertTrue(solution.arrayStringsAreEqual(word1, word2));
    }
}
