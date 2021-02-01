package k_similar_strings_854;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Brute force with memo. Barely accepted.
 *
 * Created by Zhiyong Pan on 2021-02-01.
 */
public class Solution {

    HashMap<String, Integer> memo = new HashMap<>();

    public int kSimilarity(String A, String B) {
        memo.clear();
        return help(A.toCharArray(), 0, B.toCharArray(), 0, 0);
    }

    private int help(char[] a, int offsetA, char[] b, int offsetB, int steps) {
        if (offsetA == a.length)
            return steps;

        assert offsetB < b.length;

        String key = new String(a, offsetA, a.length - offsetA);
        if (memo.containsKey(key))
            return steps + memo.get(key);

        if (a[offsetA] == b[offsetB]) {
            int ans = help(a, offsetA + 1, b, offsetB + 1, steps);
            memo.put(key, ans - steps);
            return ans;
        } else {
            int ans = Integer.MAX_VALUE;

            // TODO Our search space is huge. How can we swap wisely?

            for (int j = offsetA + 1; j < a.length; ++j) {
                if (a[j] == b[offsetB]) {
                    // swap a[offsetA] and a[j]
                    ans = Math.min(ans, help(
                            swap(a, offsetA, j), 0,
                            b, offsetB + 1,
                            steps + 1));
                }
            }

            // We don't have to swap in b, as the result will be the same.

            memo.put(key, ans - steps);
            return ans;
        }
    }

    private char[] swap(char[] a, int i, int j) {
        char[] b = new char[a.length - i - 1];
        for (int k = 0; k < b.length; ++k) {
            b[k] = a[i + 1 + k];
        }
        b[j - i - 1] = a[i];
        return b;
    }

    @Test
    void testSwap() {
        Assertions.assertArrayEquals("acd".toCharArray(), swap("abcd".toCharArray(), 0, 1));
        Assertions.assertArrayEquals("bad".toCharArray(), swap("abcd".toCharArray(), 0, 2));
        Assertions.assertArrayEquals("bca".toCharArray(), swap("abcd".toCharArray(), 0, 3));
        Assertions.assertArrayEquals("bd".toCharArray(), swap("abcd".toCharArray(), 1, 2));
        Assertions.assertArrayEquals("cb".toCharArray(), swap("abcd".toCharArray(), 1, 3));
        Assertions.assertArrayEquals("c".toCharArray(), swap("abcd".toCharArray(), 2, 3));
    }

    @Test
    void testK_example1() {
        Assertions.assertEquals(1, kSimilarity("ab", "ba"));
    }

    @Test
    void testK_example2() {
        Assertions.assertEquals(2, kSimilarity("abc", "bca"));
    }

    @Test
    void testK_example3() {
        Assertions.assertEquals(2, kSimilarity("abac", "baca"));
    }

    @Test
    void testK_example4() {
        Assertions.assertEquals(2, kSimilarity("aabc", "abca"));
    }

    @Test
    void testK_example5() {
        Assertions.assertEquals(0, kSimilarity("abcdefabcdef", "abcdefabcdef"));

        // Swapped the first 'a' with the last 'f'.
        Assertions.assertEquals(1, kSimilarity("abcdefabcdef", "fbcdefabcdea"));
    }

    @Test
    void testK_case10() {
        Assertions.assertEquals(8, kSimilarity("abcdeabcdeabcdeabcde", "aaaabbbbccccddddeeee"));
    }
}
