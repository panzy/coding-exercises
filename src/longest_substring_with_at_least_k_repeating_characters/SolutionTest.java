package longest_substring_with_at_least_k_repeating_characters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SolutionTest {
    Solution3 solution = new Solution3();

    @Test
    void longestSubstring_single_K1() {
        String s = "a";
        int k = 1;
        int expected = 1;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_single_K2() {
        String s = "a";
        int k = 2;
        int expected = 0;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_aaa_1() {
        String s = "aaa";
        int k = 1;
        int expected = 3;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_aaa_3() {
        String s = "aaa";
        int k = 3;
        int expected = 3;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_tail() {
        String s = "aaabbcccddddc";
        int k = 4;
        int expected = 8;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_middle() {
        String s = "aaabbcdcdcdcdeee";
        int k = 4;
        int expected = 8;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_case1() {
        String s = "aaabb";
        int k = 3;
        int expected = 3;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_case2() {
        String s = "ababbc";
        int k = 2;
        int expected = 5;
        Assertions.assertEquals(expected, solution.longestSubstring(s, k));
    }

    @Test
    void longestSubstring_largeN_none() {
        // generate a long string in the form of "accc...bddd..ceee...dfff..."
        int N = 10000;
        int k = 1000;
        char[] chars = new char[N];
        char startChar = 'a';
        for (int i = 0; i < N; i += k) {
            chars[i] = startChar;
            Arrays.fill(chars, i + 1, i + k, (char) (startChar + 2));
        }

        String s = new String(chars);

        long startTime = System.nanoTime();
        Assertions.assertEquals(0, solution.longestSubstring(s, k));
        System.out.printf("Elapsed time while processing N=%d: %d ms%n", N,
                (System.nanoTime() - startTime) / 1000000);
        startTime = System.nanoTime();

        Assertions.assertEquals(k - 1, solution.longestSubstring(s, k - 1));
        System.out.printf("Elapsed time while processing N=%d: %d ms%n", N,
                (System.nanoTime() - startTime) / 1000000);
    }
}
