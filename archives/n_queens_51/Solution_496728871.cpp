//
// 51. N-Queens
// https://leetcode.com/problems/n-queens/
// Submission detail page: https://leetcode.com/submissions/detail/496728871//
// 
// --
// Created by Zhiyong Pan
// 22/05/2021, 13:11:04
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    // Convert queen positions to board map.
    vector<string> convert(const vector<int>& queens) {
        int n = queens.size();
        vector<string> res; 
        for (int pos : queens) {
            string row(n, '.');
            row[pos] = 'Q';
            res.push_back(row);
        }
        return res;
    }
    
    // Place the rest queens, with each full placement being appended to `solutions`.
    //
    // prev = previously placed queens.
    // prev[i] = column position of the queen on the i-th row (0-based).
    void placeQueens(int n, const vector<int>& prev, vector<vector<string>>& solutions) {
        if (prev.size() == n) {
            // got a solution
            solutions.push_back(convert(prev));
        } else {
            // for each possible position of the next queeen, then go recursive
            for (int i = 0; i < n; ++i) {
                bool safe = true;
                
                for (int k = 0, m = prev.size(); k < m; ++k) {
                    int j = prev[k];
                    if (i == j || abs(i - j) == prev.size() - k) {
                        safe = false;
                        break;
                    }
                }
                
                if (safe) {
                    auto newState = prev;
                    newState.push_back(i);
                    placeQueens(n, newState, solutions);
                }
            }
        }
    }
    
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> solutions;
        placeQueens(n, {}, solutions);
        return solutions;   
    }
};