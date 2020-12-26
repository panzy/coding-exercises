/**
 * Zhiyong Pan, 2020-12-25
 */
package longest_substring_with_at_least_k_repeating_characters;

import java.util.Arrays;

/**
 * Outer loop: for each possible substring length w from N to k.
 * Inner loop: for each substring position from 0 to N - w.
 * Core feature: the sliding window's size is fixed for each iteration.
 * Time complexity in worst case: O(n^2) when k is very small and the result is 0.
 * This solution is somewhat faster than solution #1 (403 ms vs 686 ms).
 *
 * Performance:
 * iterations: 40504500
 * Elapsed time while processing N=10000: 234 ms
 * iterations: 40504501
 * Elapsed time while processing N=10000: 208 ms
 */
public class Solution2 {
    /**
     * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of
     * each character in this substring is greater than or equal to k.
     *
     * @param s 1 <= s.length <= 10^4. s consists of only lowercase English letters.
     * @param k 1 <= k <= 10^5
     * @return
     */
    public int longestSubstring(String s, int k) {
        final int N = s.length();

        if (k > N) return 0;

        // record how many times each char is contained in the current substring
        int[] chars = new int[26];
        int iterations = 0;

        // for each window size, slide it from left to right.
        for (int w = N; w >= k; --w) {

            // reset char counts
            Arrays.fill(chars, 0);

            // init char counts to reflect the first window position.
            for (int i = 0; i < w; ++i) {
                ++chars[s.charAt(i) - 'a'];
            }

            // slide the window
            for (int i = 0; i <= N - w; ++i) {
                if (check(chars, k)) {
                    System.out.printf("iterations: %d%n", iterations);
                    return w;
                }

                // keep sliding the window until it might have arrived at an answer
                while (i + w < N) {
                    ++iterations;
                    char exitChar = s.charAt(i);
                    char enterChar = s.charAt(i + w);
                    --chars[exitChar - 'a'];
                    ++chars[enterChar - 'a'];
                    if (chars[exitChar - 'a'] == 0 && chars[enterChar - 'a'] >= k) break;
                    ++i;
                }
            }
        }

        System.out.printf("iterations: %d%n", iterations);
        return 0;
    }

    static boolean check(int[] chars, int k) {
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] > 0 && chars[i] < k) {
                return false;
            }
        }
        return true;
    }
}
