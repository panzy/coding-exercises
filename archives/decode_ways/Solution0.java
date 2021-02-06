package decode_ways;

import java.util.Stack;

/**
 * Backtracking approach.
 *
 * It's correct but slow.
 *
 * Zhiyong Pan, 2020-12-26.
 */
public class Solution0 {
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
