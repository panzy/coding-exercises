/**
 * Zhiyong Pan, 2020-12-25
 */
package longest_substring_with_at_least_k_repeating_characters;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Based on the official solution Approach 3: Sliding Window.
 *
 * Outer loop: for each possible distinct count of chars.
 * Inner loop: for each window position from 0 to N - 1.
 * Core feature:
 * (1) the sliding window is maintained such that it contains precisely a particular amount of distinct
 * chars; it's easy to keep track of how many chars are of a frequency of at least k; so it's effortless
 * to check while sliding the window.
 * (2) the outer loop contains 26 iterations at most. Hence, the time complexity is approximately
 * O(26 * N) = O(N).
 * Time complexity in worst case: O(n^2) when k is very small and the result is 0.
 * This solution is somewhat faster than solution #1 (403 ms vs 686 ms).
 *
 * Performance (it's amazing compared to solution #2):
 * iterations: 29001
 * Elapsed time while processing N=10000: 3 ms
 * iterations: 29001
 * Elapsed time while processing N=10000: 2 ms
 */
public class Solution4 {
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

        int ans = 0;
        int iterations = 0;

        for (int targetUnique= countDistinctChars(s); targetUnique > 0; --targetUnique) {

            int windowStart = 0, windowEnd = 0;
            int unique = 0;
            int countAtLestK = 0;

            // reset char counts
            Arrays.fill(chars, 0);

            while (windowEnd < N) {
                ++iterations;
                if (unique <= targetUnique) {
                    // increase window end
                    int i = s.charAt(windowEnd) - 'a';
                    if (++chars[i] == 1) ++unique;
                    if (chars[i] == k) ++countAtLestK;
                    ++windowEnd;
                } else {
                    // increase window start
                    int i = s.charAt(windowStart) - 'a';
                    if (chars[i] == k) --countAtLestK;
                    if (--chars[i] == 0)  --unique;
                    ++windowStart;
                }

                if (unique == targetUnique && unique == countAtLestK) {
                    if (windowEnd - windowStart > ans) {
                        ans = windowEnd - windowStart;
                    }
                }
            }
        }

        System.out.printf("iterations: %d%n", iterations);
        return ans;
    }

    static int countDistinctChars(String s) {
        BitSet set = new BitSet();
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (!set.get(s.charAt(i) - 'a')) {
                ++n;
                set.set(s.charAt(i) - 'a');
            }
        }
        return n;
    }
}
