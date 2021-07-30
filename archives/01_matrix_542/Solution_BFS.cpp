//
// 542. 01 Matrix
// https://leetcode.com/problems/01-matrix/
// 
// 49 / 49 test cases passed.	Status: Accepted
// Runtime: 64 ms
// Memory Usage: 27.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      30/07/2021, 18:20:40
// LeetCode submit time: 5 hours, 4 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/530713666//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        int m = mat.size();
        int n = mat[0].size();
        const vector<pair<int, int>> neibs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // If a cell has just gotten its distance value, it is considered on the frontlines.
        queue<int> frontlines;
        
        // Replace 1s with -1s. -1 means distance unkown.
        // If a -1 is next to a 0, change it to 1 and add it to fronlines.
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (mat[r][c] == 1) {
                    mat[r][c] = -1;
                    for (auto&& d : neibs) {
                        int r2 = r + d.first;
                        int c2 = c + d.second;
                        if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n && mat[r2][c2] == 0) {
                            mat[r][c] = 1;   
                            frontlines.push((r << 16) + c);
                        }
                    }
                }
            }
        }
        
        // Push forward the frontlines. This is also the process that expands the kown areas.
        while (!frontlines.empty()) {
            int r = frontlines.front() >> 16;
            int c = frontlines.front() & 0xffff;
            frontlines.pop();
            
            for (auto&& d : neibs) {
                int r2 = r + d.first;
                int c2 = c + d.second;
                if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n && mat[r2][c2] == -1) {
                    mat[r2][c2] = mat[r][c] + 1;   
                    frontlines.push((r2 << 16) + c2);
                }
            }
        }
        
        return mat;
    }
};