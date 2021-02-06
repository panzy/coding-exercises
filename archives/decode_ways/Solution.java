package decode_ways;

import java.util.Arrays;

/**
 * Divide & conquer with cache. Very fast.
 *
 * Caching is the key to avoid O(2^N).
 *
 * Zhiyong Pan, 2020-12-26.
 */
public class Solution {
    public int numDecodings(String s) {
        int[] cache = new int[s.length()];
        Arrays.fill(cache, -1);
        return numDecodings(s, 0, cache);
    }

    /**
     * Calculate the number of decoding ways of s.substring(start).
     * @param s
     * @param start
     * @param cache cache[i] == numDecodings(s, i, _). -1 means none.
     * @return
     */
    public int numDecodings(String s, int start, int[] cache) {
        if (start >= s.length())
            return 1;

        if (cache[start] != -1)
            return cache[start];

        int a = s.charAt(start) - '0';

        if (a == 0)
            return 0;

        int b = a < 3 && start + 1 < s.length() ? a * 10 + s.charAt(start + 1) - '0' : 0;
        if (b > 26)
            b = 0;

        int ans = b == 0 ?
                numDecodings(s, start + 1, cache) :
                numDecodings(s, start + 1, cache) + numDecodings(s, start + 2, cache);
        cache[start] = ans;
        return ans;
    }
}
