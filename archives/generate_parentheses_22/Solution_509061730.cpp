//
// 22. Generate Parentheses
// https://leetcode.com/problems/generate-parentheses/
// 
// 8 / 8 test cases passed.	Status: Accepted
// Runtime: 4 ms
// Memory Usage: 7.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/06/2021, 00:03:38
// LeetCode submit time: 7 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/509061730//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        if (n == 0) return {};
        if (n == 1) return {"()"};
        if (n == 2) return {"(())", "()()"};
        
        // dp[i] = generateParenthesis(i)
        vector<vector<string>> dp(n + 1, vector<string>{});
        
        dp[1] = {"()"};
        dp[2] = {"(())", "()()"};
        
        for (int i = 3; i <= n; ++i) {
            // case 1, build expressions with exactly one top-level pair of parentheses
            for (auto&& ele : dp[i - 1]) {
                dp[i].push_back("(" + ele + ")");
            }
            
            // case 2, concanate two shorter expressions
            set<string> distincts;
            for (int a = i - 1; a > 0; --a) {
                for (auto&& eleA : dp[a]) {
                    for (auto&& eleB : dp[i - a]) {
                        distincts.insert(eleA + eleB);
                    }
                }
            }
            dp[i].insert(dp[i].end(), begin(distincts), end(distincts));
        }
        
        
        return dp[n];
    }
};