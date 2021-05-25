//
// 64. Minimum Path Sum
// https://leetcode.com/problems/minimum-path-sum/
// 
// 61 / 61 test cases passed.	Status: Accepted
// Runtime: 4 ms
// Memory Usage: 9.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      25/05/2021, 13:12:24
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/498153526//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// Optimized DP: reuse the input grid as the dp array.
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        // Define dp[r][c] = path sum from grid[r][c] to grid[m-1][n-1].
        // Define dp[m][c] = dp[r][n] = 0.
        // Then dp[r][c] = grid[r][c] + min(dp[r + 1][c], dp[r][c + 1]).
        // ...then, for performance's sake, we reuse grid as dp.
        vector<vector<int>>& dp = grid;

        for (int r = m - 2; r >= 0; --r) {
            dp[r][n - 1] += dp[r + 1][n - 1];
        }

        for (int c = n - 2; c >= 0; --c) {
            dp[m - 1][c] += dp[m - 1][c + 1];
        }

        for (int r = m - 2; r >= 0; --r) {
            for (int c = n - 2; c >= 0; --c) {
                dp[r][c] += min(dp[r + 1][c], dp[r][c + 1]);
            }
        }

        return dp[0][0];
    }
};

