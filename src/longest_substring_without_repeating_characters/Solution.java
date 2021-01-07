package longest_substring_without_repeating_characters;

import java.util.BitSet;

/**
 * Use two pointers to define a sliding window [i, j), and a bit set to mark which characters present in that range.
 *
 * Everytime ++j, ++i zero or any times to keep the characters between [i, j) unique.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        BitSet chars = new BitSet(256); // s consists of English letters, digits, symbols and spaces.
        int n = s.length();
        if (n <= 1)
            return n;

        int i = 0, j = 1;
        chars.set(s.charAt(0));

        int ans = 1;

        while (j < n) {
            char c = s.charAt(j++);
            // TODO this inner loop could be optimized out if we knew where exactly the previous |c| was.
            // See next solution.
            while (chars.get(c)) {
                chars.clear(s.charAt(i++));
            }
            chars.set(c);
            ans = Math.max(ans, j - i);
        }

        return ans;
    }
}
