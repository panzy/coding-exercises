package regular_expression_matching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-08.
 */
public class RegularExpressionMatching {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertFalse(solution.isMatch("aa", "a"));
    }

    @Test
    void example2() {
        Assertions.assertTrue(solution.isMatch("aa", "a*"));
    }

    @Test
    void example3() {
        Assertions.assertTrue(solution.isMatch("aab", "c*a*b"));
    }

    @Test
    void example4() {
        Assertions.assertTrue(solution.isMatch("abc!", "a.c."));
    }

    @Test
    void example5() {
        Assertions.assertTrue(solution.isMatch("aaaaab", "a*ab"));
        Assertions.assertTrue(solution.isMatch("aaaaa", "a*a"));
        Assertions.assertTrue(solution.isMatch("aaabc!", "a*abc!"));
        Assertions.assertTrue(solution.isMatch("aaabc!", "a*aabc!"));
    }

    @Test
    void example6() {
        Assertions.assertTrue(solution.isMatch("aaabc!", "c*a*abc!"));
    }

    @Test
    void example7() {
        Assertions.assertTrue(solution.isMatch("aaabc!", "a*.*.!"));
    }

    @Test
    void example8() {
        Assertions.assertTrue(solution.isMatch("", ""));
    }

    @Test
    void example9() {
        Assertions.assertTrue(solution.isMatch("", "a*"));
    }

    @Test
    void example10() {
        Assertions.assertTrue(solution.isMatch("", "a*b*"));
    }

    @Test
    void example11() {
        Assertions.assertTrue(solution.isMatch("abc", "a*b*.*.*.*.*"));
    }

    @Test
    void example12() {
        Assertions.assertTrue(solution.isMatch("abc!@#", "a*b*.*..."));
        Assertions.assertTrue(solution.isMatch("abc!@#", "a*b*.*.*..."));
    }

    @Test
    void preprocessPattern_example1() {
        Assertions.assertEquals("a*b", Solution.Pattern.preprocessPattern("a*ab"));
    }

    @Test
    void preprocessPattern_example2() {
        Assertions.assertEquals("a*.*.*b", Solution.Pattern.preprocessPattern("a*a.*.*b"));
    }
}
