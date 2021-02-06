/**
 * Zhiyong Pan, 2020-12-25
 */
package longest_substring_with_at_least_k_repeating_characters;

import java.util.Arrays;

/**
 * Minor adjust to solution #2, replacing check() function with expression
 * unique == countAtLeastK, inspired by solution #4.
 *
 * But it's slower than the original solution #2.
 */
public class Solution2_B {
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

        // for each window size, slide it from left to right.
        for (int w = N; w >= k; --w) {

            // reset char counts
            Arrays.fill(chars, 0);

            int unique = 0;
            int countAtLeastK = 0;

            // init char counts to reflect the first window position.
            for (int i = 0; i < w; ++i) {
                int c = s.charAt(i) - 'a';
                if (chars[c] == 0) ++unique;
                ++chars[c];
                if (chars[c] == k) ++countAtLeastK;
            }

            // slide the window
            for (int i = 0; i <= N - w; ++i) {
                if (unique == countAtLeastK) return w;

                // keep sliding the window until it might have arrived at an answer
                while (i + w < N) {
                    int exitChar = s.charAt(i) - 'a';
                    int enterChar = s.charAt(i + w) - 'a';

                    if (chars[exitChar] == k) --countAtLeastK;
                    --chars[exitChar];
                    if (chars[exitChar] == 0) --unique;

                    if (chars[enterChar] == 0) ++unique;
                    ++chars[enterChar];
                    if (chars[enterChar] == k) ++countAtLeastK;

                    if (chars[exitChar] == 0 && chars[enterChar] == k) break;
                    ++i;
                }
            }
        }

        return 0;
    }
}