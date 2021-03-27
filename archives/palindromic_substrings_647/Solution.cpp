/* 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 * --
 * Zhiyong
 * 2021-03-27
 */
class Solution {
public:
    int countSubstrings(string s) {
        int n = s.length();
        int cnt = 0;

        for (int i = 0; i < n; ++i) {
            // use [i] as the center
            int r = 1; // radius
            for (; i - r >= 0 && i + r < n && s[i - r] == s[i + r]; ++r);
            cnt += r;
            
            // use [i-1] and [i] as the center
            if (i > 0 && s[i - 1] == s[i]) {
                r = 1;
                for (; i - 1 - r >= 0 && i + r < n && s[i - 1 - r] == s[i + r]; ++r);
                cnt += r;
            }
        }
        
        return cnt;
    }
};
