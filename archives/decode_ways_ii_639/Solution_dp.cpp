//
// 639. Decode Ways II
// https://leetcode.com/problems/decode-ways-ii/
// 
// 217 / 217 test cases passed.	Status: Accepted
// Runtime: 64 ms
// Memory Usage: 31.4 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/07/2021, 20:10:03
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/520514338/?from=explore&item_id=3809/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numDecodings(string s) {
        const int M = 1e9 + 7;
        int n = s.length();
        
        // dp[i].first = numDecodings(s[0:i]) if s[i] alone must represent a letter;
        // dp[i].second = numDecodings(s[0:i]) if s[i-1] and s[i] together represent a letter.
        vector<pair<uint64_t, uint64_t>> dp(n + 1, {0, 0});
        
        // special case for i = 0
        if (s[0] == '0') {
            dp[0].first = 0;
        } else if (s[0] == '*') {
            dp[0].first = 9;
        } else {
            dp[0].first = 1;
        }
        dp[0].second = 0; // because it's impossible; s[i-1] doesn't exist
        
        for (int i = 1; i < n; ++i) {
            // dp[i].second = dp[i].both * multi
            int multi = 0;
            
            if (s[i] == '0') {
                dp[i].first = 0;
                if (s[i - 1] == '1' || s[i - 1] == '2') {
                    multi = 1;
                } else if (s[i - 1] == '*') {
                    multi = 2;
                }
            } else if (s[i] == '*') {
                dp[i].first = (dp[i - 1].first + dp[i - 1].second) * 9 % M;
                if (s[i - 1] == '1') {
                    multi = 9; // s[i] can be any of 1-9
                } else if (s[i - 1] == '2') {
                    multi = 6; // s[i] can be any of 1-6
                } else if (s[i - 1] == '*') {
                    multi = 9 + 6; // combination of the above two
                } else {
                    dp[i].second = 0;
                }
            } else {
                dp[i].first = (dp[i - 1].first + dp[i - 1].second) % M;
                if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] <= '6')) {
                    multi = 1;
                } else if (s[i - 1] == '*') {
                    if (s[i] <= '6')
                        multi = 2;
                    else
                        multi = 1;
                }
            }
            dp[i].second = (i < 2 ? 1 : dp[i - 2].first + dp[i - 2].second) * multi % M;
        }

        return (dp[n - 1].first + dp[n - 1].second) % M;
    }
};