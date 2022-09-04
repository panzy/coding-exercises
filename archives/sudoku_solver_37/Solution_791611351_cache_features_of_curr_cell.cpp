//
// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/
// 
// 6 / 6 test cases passed.	Status: Accepted
// Runtime: 25 ms
// Memory Usage: 6.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      04/09/2022, 17:59:21
// LeetCode submit time: 52 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/791611351//
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
                    // collect flags of the current row, column and sub-box
                    bool rowFlags[10] = { false };
                    bool colFlags[10] = { false };
                    bool boxFlags[10] = { false };
                    
                    for (int i = 0; i < 9; ++i) {
                        // the current row
                        {
                            char c = board[y][i];
                            if (c != '.') {
                                rowFlags[c - '0'] = true;
                            }
                        }

                        // the current column
                        {
                            char c = board[i][x];
                            if (c != '.') {
                                colFlags[c - '0'] = true;
                            }
                        }
                    }
                    
                    // index of the top left cell of the sub-box
                    int x0 = 3 * (x / 3), y0 = 3 * (y / 3);
                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            char c = board[y0 + j][x0 + i];
                            if (c != '.') {
                                boxFlags[c - '0'] = true;
                            }
                        }
                    }

                    
                    // fill only this cell and then recur
                    for (int d = 1; d <= 9; ++d) {
                        if (!rowFlags[d] && !colFlags[d] && !boxFlags[d]) {
                            board[y][x] = d + '0';
                            if (solve(board)) {
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
        
        return true;
    }
};