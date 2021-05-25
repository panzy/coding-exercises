//
// 64. Minimum Path Sum
// https://leetcode.com/problems/minimum-path-sum/
// 
// 61 / 61 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 10.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      25/05/2021, 13:11:53
// LeetCode submit time: 12 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/498145675//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// Recursive DP.
class Solution {
    int m{ 0 }, n{ 0 };
    vector<vector<int>> dp;

    int dfs(const vector<vector<int>>& grid, int currRow, int currCol) {
        int& ans = dp[currRow][currCol];
        if (ans != -1)
            return ans;

        int rest = -1;
        if (currRow + 1 < m) {
            int t = dfs(grid, currRow + 1, currCol);
            if (rest == -1 || rest > t)
                rest = t;
        }
        if (currCol + 1 < n) {
            int t = dfs(grid, currRow, currCol + 1);
            if (rest == -1 || rest > t)
                rest = t;
        }

        ans = grid[currRow][currCol] + (rest == -1 ? 0 : rest);
        return ans;
    }
public:
    int minPathSum(const vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        dp.resize(m, vector<int>(n, -1));
        return dfs(grid, 0, 0);
    }
};

