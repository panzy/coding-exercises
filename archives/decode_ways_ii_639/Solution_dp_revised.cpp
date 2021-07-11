//
// 639. Decode Ways II
// https://leetcode.com/problems/decode-ways-ii/
// 
// 217 / 217 test cases passed.	Status: Accepted
// Runtime: 40 ms
// Memory Usage: 21.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/07/2021, 20:18:04
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/520516807/?from=explore&item_id=3809/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numDecodings(string s) {
        const int M = 1e9 + 7;
        int n = s.length();
        
        // dp[i] = numDecodings(s[0:i])
        vector<uint64_t> dp(n, 0);
        
        // special case for i = 0
        if (s[0] == '0') {
            dp[0] = 0;
        } else if (s[0] == '*') {
            dp[0] = 9;
        } else {
            dp[0] = 1;
        }
        
        for (int i = 1; i < n; ++i) {
            // dp[i] = dp[i - 1] * multi1 + dp[i - 2] * multi2
            int multi1 = 0, multi2 = 0;
            
            if (s[i] == '0') {
                if (s[i - 1] == '1' || s[i - 1] == '2') {
                    multi2 = 1;
                } else if (s[i - 1] == '*') {
                    multi2 = 2;
                }
            } else if (s[i] == '*') {
                multi1 = 9;
                if (s[i - 1] == '1') {
                    multi2 = 9; // s[i] can be any of 1-9
                } else if (s[i - 1] == '2') {
                    multi2 = 6; // s[i] can be any of 1-6
                } else if (s[i - 1] == '*') {
                    multi2 = 9 + 6; // combination of the above two
                } else {
                }
            } else {
                multi1 = 1;
                if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '6')) {
                    multi2 = 1;
                } else if (s[i - 1] == '*') {
                    if (s[i] <= '6')
                        multi2 = 2;
                    else
                        multi2 = 1;
                }
            }
            dp[i] = (dp[i - 1] * multi1 + (i < 2 ? 1 : dp[i - 2]) * multi2) % M;
        }

        return dp[n - 1];
    }
};