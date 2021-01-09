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
    void example5_A() {
        Assertions.assertTrue(solution.isMatch("aaaaab", "a*ab"));
    }
    @Test
    void example5_B() {
        Assertions.assertTrue(solution.isMatch("aaaaab", "a*aab"));
    }
    @Test
    void example5_C() {
        Assertions.assertTrue(solution.isMatch("aaaaab", "a*aaaaab"));
    }
    @Test
    void example5_D() {
        Assertions.assertFalse(solution.isMatch("aaaaab", "a*aaaaaab"));
    }
    @Test
    void example5_E() {
        Assertions.assertTrue(solution.isMatch("aaabc!", "a*aabc!"));
    }
    @Test
    void example5_F() {
        Assertions.assertTrue(solution.isMatch("aaaaa", "a*a"));
    }
    @Test
    void example5_G() {
        Assertions.assertTrue(solution.isMatch("aaabc!", "a*abc!"));
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
        Assertions.assertTrue(solution.isMatch("abcxyz", "a*b*.*..."));
    }

    @Test
    void example13() {
        Assertions.assertTrue(solution.isMatch("abcxyz", "a*b*.*.*..."));
    }

    @Test
    void example14() {
        Assertions.assertTrue(solution.isMatch("abcxyz", "a*b*.*.*..z"));
    }

    @Test
    void example15() {
        Assertions.assertTrue(solution.isMatch("ab!@#", "a*b*.*.*..#"));
    }

    @Test
    void example16() {
        Assertions.assertTrue(solution.isMatch("ab!@#y#", "a*b*.*.*..#"));
    }

    @Test
    void example17() {
        Assertions.assertTrue(solution.isMatch("ab", ".*"));
    }

    @Test
    void example18() {
        Assertions.assertFalse(solution.isMatch("aaa", "aaaa"));
    }

    @Test
    void example19() {
        Assertions.assertTrue(solution.isMatch("a", "ab*"));
    }

    @Test
    void example20() {
        Assertions.assertFalse(solution.isMatch("a", ".*..a*"));
    }

    @Test
    void example21() {
        Assertions.assertTrue(solution.isMatch("bbbba", ".*a*a"));
    }

    @Test
    void example22() {
        Assertions.assertTrue(solution.isMatch("ab", ".*..c*"));
    }

    @Test
    void example23() {
        Assertions.assertTrue(solution.isMatch("aaa", "ab*a*c*a"));
    }

    @Test
    void example24() {
        Assertions.assertTrue(solution.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
    }
}
