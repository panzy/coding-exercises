//
// 91. Decode Ways
// https://leetcode.com/problems/decode-ways/
// 
// 269 / 269 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 6.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/07/2021, 20:21:43
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/520517730//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numDecodings(string s) {
        int n = s.length();
        
        // dp[i] = numDecodings(s[0:i])
        vector<int> dp(n, 0);
        
        // special case for i = 0
        if (s[0] == '0') {
            dp[0] = 0;
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
            } else {
                multi1 = 1;
                if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '6')) {
                    multi2 = 1;
                }
            }
            dp[i] = dp[i - 1] * multi1 + (i < 2 ? 1 : dp[i - 2]) * multi2;
        }

        return dp[n - 1];
    }
};