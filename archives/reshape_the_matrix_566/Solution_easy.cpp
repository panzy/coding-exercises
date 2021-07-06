//
// 566. Reshape the Matrix
// https://leetcode.com/problems/reshape-the-matrix/
// 
// 57 / 57 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 10.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      05/07/2021, 20:01:25
// LeetCode submit time: 25 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/517970646//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& mat, int r, int c) {
        int m = mat.size();
        int n = mat[0].size();

        if (m * n != r * c)
            return mat;

        vector<vector<int>> res(r, vector<int>(c));
        
        for (int i = 0; i < m * n; ++i) {
            res[i / c][i % c] = mat[i / n][i % n];
        }
        
        return res;
    }
};