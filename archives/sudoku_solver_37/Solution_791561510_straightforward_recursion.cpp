//
// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/
// 
// 6 / 6 test cases passed.	Status: Accepted
// Runtime: 55 ms
// Memory Usage: 6.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      04/09/2022, 17:56:57
// LeetCode submit time: 2 hours, 14 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/791561510//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        solve(board);
    }
private: 
    bool solve(vector<vector<char>>& board) {
        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                if (board[y][x] == '.') {
                    // fill only this cell and then recur
                    for (int d = 1; d <= 9; ++d) {
                        board[y][x] = d + '0';
                        if (isSudokuValidAt(board, x, y)) {
                            bool solved = solve(board);
                            if (solved) {
                                return true;
                            }
                        }
                    }
                    
                    // leave the board untouched and indicate it's unsolvable
                    board[y][x] = '.';
                    return false;
                }
            }
        }
        
        // it's already solved
        return true;
    }
    
    bool isSudokuValidAt(vector<vector<char>>& board, int x, int y) {
        
        // check the current row
        {
            bool flags[10] = { false };
            for (int i = 0; i < 9; ++i) {
                char c = board[y][i];
                if (c != '.') {
                    if (flags[c - '0']) {
                        return false;
                    }
                    else {
                        flags[c - '0'] = true;
                    }
                }
            }
        }
        
        // check the current column
        {
            bool flags[10] = { false };
            for (int i = 0; i < 9; ++i) {
                char c = board[i][x];
                if (c != '.') {
                    if (flags[c - '0']) {
                        return false;
                    }
                    else {
                        flags[c - '0'] = true;
                    }
                }
            }
        }
        
        // check the current sub-box
        {
            bool flags[10] = { false };
            int x0 = 3 * (x / 3), y0 = 3 * (y / 3); // index of the top left cell of the sub-box
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    char c = board[y0 + j][x0 + i];
                    if (c != '.') {
                        if (flags[c - '0']) {
                            return false;
                        }
                        else {
                            flags[c - '0'] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
};