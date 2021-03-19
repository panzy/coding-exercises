/* 1573. Number of Ways to Split a String
 * https://leetcode.com/problems/number-of-ways-to-split-a-string/
 *
 * --
 * Zhiyong
 * 2021-03-19
 */

/*
Given a binary string s (a string consisting only of '0's and '1's), we can
split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).

Return the number of ways s can be split such that the number of characters '1'
is the same in s1, s2, and s3.

Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

Input: s = "10101"
Output: 4
Explanation: There are four ways to split s in 3 parts where each part contain
the same number of letters '1'.
"1|010|1"
"1|01|01"
"10|10|1"
"10|1|01"
Example 2:

Input: s = "1001"
Output: 0
Example 3:

Input: s = "0000"
Output: 3
Explanation: There are three ways to split s in 3 parts.
"0|0|00"
"0|00|0"
"00|0|0"
Example 4:

Input: s = "100100010100110"
Output: 12
 

Constraints:

3 <= s.length <= 10^5
s[i] is '0' or '1'.
*/

class Solution {
    const int MOD = (int)1e9 + 7;
public:
    int numWays(string s) {
        int n = s.length();
        int cnt = 0;
        
        for (int i = 0; i < n; ++i) {
            if (s[i] == '1') {
                ++cnt;
            }
        }
        
        // edge case
        if (cnt == 0) {
            return (unsigned long long)(n - 1) * (n - 2) / 2 % MOD;
        }
        
        // short cut
        if (cnt % 3 != 0) {
            return 0;
        }
        
        // s1 = [0, i) ~ [0, i2)
        // s2 = ...
        // s3 = [j2, n) ~ [j, n)

        // find the bound of i and j

        int third = cnt / 3;
        int i = 0, i2;
        int cnt1 = s[i] - '0';
        while (cnt1 < third) {
            if (s[++i] == '1')
                ++cnt1;
        }
        
        i2 = i;
        while (s[i2 + 1] == '0') ++i2;
        
        int j = n - 1, j2;
        int cnt3 = s[j] - '0';
        while (cnt3 < third) {
            if (s[--j] == '1')
                ++cnt3;
        }
        
        j2 = j;
        while (s[j2 - 1] == '0') --j2;
        
        //cout << "i=" << i << " j=" << j << endl;
        
        return (unsigned long long)(i2 - i + 1) * (j - j2 + 1) % MOD;
    }
};
