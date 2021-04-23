/**
 * 696. Count Binary Substrings
 * https://leetcode.com/problems/count-binary-substrings/
 * --
 * Zhiyong 2021-04-23
 */

class Solution {
public:
    int countBinarySubstrings(string s) {
        int n = s.length();

        // c0: count of 0's,
        // c1: count of 1's.
        int c0 = 0, c1 = 0;

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (s[i] == '0')
                ++c0;
            else
                ++c1;

            if (i == n || s[i] != s[i + 1]) {
                ans += min(c0, c1);
                if (s[i] == '0')
                    c1 = 0;
                else
                    c0 = 0;
            }
        }
        
        return ans;
    }
};
