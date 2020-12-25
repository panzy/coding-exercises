/**
 * Zhiyong Pan, 2020-12-25
 */
package longest_substring_with_at_least_k_repeating_characters;

/**
 * Based on the official solution Approach 2: Divide And Conquer.
 */
public class Solution3 {
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
        return  longestSubstring(s, 0, N, k);
    }

    int longestSubstring(String s, int start, int end, int k) {
        final int N = end - start;
        if (k > N) return 0;

        // record how many times each char is contained in the current substring
        int[] chars = new int[26];

        // collect invalid chars
        for (int i = start; i < end; ++i) {
            ++chars[s.charAt(i) - 'a'];
        }

        // Divide and conquer: if there is an invalid char, then split the input string with
        // it and recursively call longestSubstring() on each of it, taking the larger return
        // value as our final result.
        //
        // An invalid character is the one with a frequency of less than k.
        for (int i = start; i < end; ++i) {
            if (chars[s.charAt(i) - 'a'] < k) {
                int ans = 0; // the question answer
                if (i >= k) {
                    ans = Math.max(ans, longestSubstring(s, start, i, k));
                }
                if (end - (i + 1) >= k) {
                    ans = Math.max(ans, longestSubstring(s, i + 1, end, k));
                }
                return ans;
            }
        }

        // if there is not a single invalid char, then the range [start, end) is already a valid subsring.
        return end - start;
    }
}
