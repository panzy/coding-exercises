/* 329. Longest Increasing Path in a Matrix
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * --
 * Zhiyong 2021-04-10
 */

class Solution {
    // coordinate delta of neighbours
    vector<pair<int, int>> neibs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    // dp[r][c] = the length of the longest path starting from (r,c).
    int dp[200][200];
    
    // the input matrix and its size
    vector<vector<int>>* mtx;
    int m, n;
    
    int depth(int r, int c) {
        if (dp[r][c] != -1) return dp[r][c];
        
        dp[r][c] = 1; // the length before connected any neighbours
        
        for (auto&& d : neibs) {
            int r2 = r + d.first;
            int c2 = c + d.second;
            if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n && (*mtx)[r][c] < (*mtx)[r2][c2])
                dp[r][c] = max(dp[r][c], 1 + depth(r2, c2));
        }
        
        return dp[r][c];
    }

public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        
        mtx = &matrix;
        m = matrix.size();
        n = matrix[0].size();

        for (int i = 0; i < m; ++i)
            fill(dp[i], dp[i] + n, -1);
        
        int ans = 0;
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                dp[r][c] = depth(r, c);
                if (ans < dp[r][c])
                    ans = dp[r][c];
            }
        }
            
        return ans;
    }
};
