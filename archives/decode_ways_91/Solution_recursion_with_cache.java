//
// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
// 
// 268 / 268 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 36.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/07/2021, 20:26:11
// LeetCode submit time: 6 months, 2 weeks ago
// Submission detail page: https://leetcode.com/submissions/detail/434887541//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
import java.util.Arrays;

public class Solution {
    public int numDecodings(String s) {
        int[] cache = new int[s.length()];
        Arrays.fill(cache, -1);
        return numDecodings(s, 0, cache);
    }

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
