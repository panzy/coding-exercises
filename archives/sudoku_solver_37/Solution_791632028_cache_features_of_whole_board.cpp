//
// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/
// 
// 6 / 6 test cases passed.	Status: Accepted
// Runtime: 27 ms
// Memory Usage: 6.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      04/09/2022, 18:01:52
// LeetCode submit time: 15 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/791632028//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
private:
    // features of the board -- which digits are in each row, column and sub-box?
    bool rowFlags[9][9] = {0}; // [row_idx][digit]
    bool colFlags[9][9] = {0}; // [col_idx][digit]
    bool boxFlags[3][3][9] = {0}; // [row_idx][col_idx][digit]
    
public:
    void solveSudoku(vector<vector<char>>& board) {
        
        // extract board features
        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                if (board[y][x] != '.') {
                    int d = board[y][x] - '1';
                    rowFlags[y][d] = true;
                    colFlags[x][d] = true;
                    boxFlags[y/3][x/3][d] = true;
                }
            }
        }
        
        solve(board);
    }

private:
    bool solve(vector<vector<char>>& board) {
        for (int x = 0; x < 9; ++x) {
            for (int y = 0; y < 9; ++y) {
                if (board[y][x] == '.') {
                    
                    // fill only this cell and then recur.
                    // the board features are used to valid the new state quickly.
                    for (int d = 0; d < 9; ++d) {
                        if (0 == (rowFlags[y][d]) &&
                            0 == (colFlags[x][d]) &&
                            0 == (boxFlags[y/3][x/3][d])) {
                            board[y][x] = d + '1';
                            rowFlags[y][d] = true;
                            colFlags[x][d] = true;
                            boxFlags[y/3][x/3][d] = true;
                            if (solve(board)) {
                                return true;
                            } else {
                                // leave the board untouched
                                board[y][x] = '.';
                                rowFlags[y][d] = false;
                                colFlags[x][d] = false;
                                boxFlags[y/3][x/3][d] = false;
                            }
                        }
                    }
                    
                    return false;
                }
            }
        }
        
        return true;
    }
};