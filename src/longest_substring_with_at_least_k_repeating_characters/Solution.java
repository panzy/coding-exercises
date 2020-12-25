/**
 * Zhiyong Pan, 2020-12-25
 */
package longest_substring_with_at_least_k_repeating_characters;

import java.util.Arrays;

/**
 * Brute force with local optimizations -- the inner loop may end earlier in some cases.
 */
public class Solution {
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

        int ans = 0; // the answer

        // record how many times each char is contained in the current substring
        int[] chars = new int[26];

        for (int i = 0; i <= N - k; ++i) {
            // reset charCounts
            Arrays.fill(chars, 0);

            // check all substrings which start from |i|.
            for (int j = i; j < N; ++j) {
                ++chars[s.charAt(j) - 'a'];

                // if range [i, j] is going to be acceptable,
                // its length should be at least k * numberOfDistinctChars.
                int minExtraLen = countLacks(chars, k);

                if (minExtraLen == 0) {
                    // range [i, j] is a valid substring, though it might not be the longest one.
                    if (ans < j - i + 1)
                        ans = j - i + 1;
                } else if (minExtraLen > N - i) {
                    // it's not possible for any substrings starting from |i| to be acceptable.
                    break;
                }
            }
        }

        return ans;
    }

    /**
     * For chars that have appeared at least once, how many extra occurrences in total
     * are needed for the substring to be acceptable?
     * @param chars each element is the current frequency of a particular letter. [0] is for 'a'.
     * @param k the required minimum frequency.
     * @return the sum.
     */
    static int countLacks(int[] chars, int k) {
        int n = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] > 0) {
                n += Math.max(0, k - chars[i]);
            }
        }
        return n;
    }
}
