//
// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
// 
// 22 / 268 test cases passed.	Status: Time Limit Exceeded
// Runtime: 3980 ms
// Memory Usage: 0 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/07/2021, 20:27:59
// LeetCode submit time: 6 months, 2 weeks ago
// Submission detail page: https://leetcode.com/submissions/detail/434878929//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
import java.util.Stack;

public class Solution {
    public int numDecodings(String s) {
        int ways = 0;
        Stack<Integer> path = new Stack<>();

        final int n = s.length();
        int i = 0;
        int lastCode = 0;

        // push an fake code as a terminator.
        // if this one is popped, then we know we are done.
        path.push(0);

        while (!path.empty()) {
            int a = nextCode(s, i, lastCode == 0 ? 0 : lastCode + 1);

            if (a == 0) {
                // no next code? go back
                lastCode = path.pop();
                i -= lastCode < 10 ? 1 : 2;
            } else {
                i += a < 10 ? 1 : 2;

                if (i == n) {
                    // found a decode way.
                    ++ways;

                    // go back
                    lastCode = a;
                    i -= lastCode < 10 ? 1 : 2;
                } else {
                    // go down the path
                    path.push(a);
                    lastCode = 0;
                }
            }
        }

        return ways;
    }

    static int nextCode(String s, int position, int minValue) {
        int a = s.charAt(position) - '0';
        int b = position + 1 < s.length() ? a * 10 + s.charAt(position + 1) - '0' : 0;

        if (b > 26) b = 0;

        if (a >= minValue)
            return a;
        else if (b >= minValue)
            return b;
        else
            return 0;
    }
}
