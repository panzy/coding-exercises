package longest_substring_without_repeating_characters;

import java.util.HashMap;

/**
 * Replaced the set in the previous solution with a map, holding character indices.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();
        int n = s.length();
        if (n <= 1)
            return n;

        int i = 0, j = 1;
        chars.put(s.charAt(0), 0);

        int ans = 1;

        while (j < n) {
            char c = s.charAt(j);
            if (chars.containsKey(c)) {
                i = Math.max(i, chars.get(c) + 1); // it is wrong to write "i = chars.get(c) + 1"
            }
            chars.put(c, j);
            ++j;
            ans = Math.max(ans, j - i);
        }

        return ans;
    }
}
