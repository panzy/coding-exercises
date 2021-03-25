/* 417. Pacific Atlantic Water Flow
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * --
 * Zhiyong
 * 2021-03-25
 */

/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

const vector<pair<int, int>> neibs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

class Solution {
    void bfs(vector<vector<int>>& matrix, queue<pair<int, int>>& Q, bitset<151 * 151>& seen) {
        
        int R = matrix.size();
        int C = matrix[0].size();
        
        while (Q.size()) {
            auto [r0, c0] = Q.front();
            Q.pop();
            
            for (auto& neib : neibs) {
                int r = r0 + neib.first;
                int c = c0 + neib.second;
                
                if (r >= 0 && r < R && c >= 0 && c < C && matrix[r][c] >= matrix[r0][c0] && !seen[r * C + c]) {
                    seen.set(r * C + c);
                    Q.push({r, c});
                }
            }
        }
    }
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& matrix) {
        
        int R = matrix.size();
        if (R == 0) return {};
        int C = matrix[0].size();
        
        // search from pacific

        queue<pair<int, int>> Q;
        bitset<151 * 151> seen;
        
        for (int r0 = 0; r0 < R; ++r0) {
            Q.push({r0, 0});
            seen.set(r0 * C);
        }
        for (int c0 = 0; c0 < C; ++c0) {
            Q.push({0, c0});
            seen.set(c0);
        }
        
        bfs(matrix, Q, seen);

        // search from atlantic
        
        queue<pair<int, int>> Q2;
        bitset<151 * 151> seen2;

        for (int r0 = 0; r0 < R; ++r0) {
            Q2.push({r0, C - 1});
            seen2.set(r0 * C + C - 1);
        }
        for (int c0 = 0; c0 < C; ++c0) {
            Q2.push({R - 1, c0});
            seen2.set((R - 1) * C + c0);
        }
        
        bfs(matrix, Q2, seen2);
        
        // collect answers

        vector<vector<int>> res;
        
        for (int r0 = 0; r0 < R; ++r0) {
            for (int c0 = 0; c0 < C; ++c0) {
                if (seen[r0 * C + c0] && seen2[r0 * C + c0]) {
                    res.push_back({r0, c0});
                }
            }
        }
        
        return res;
    }
};
