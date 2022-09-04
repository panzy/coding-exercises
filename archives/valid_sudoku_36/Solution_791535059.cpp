//
// 36. Valid Sudoku
// https://leetcode.com/problems/valid-sudoku/
// 
// 507 / 507 test cases passed.	Status: Accepted
// Runtime: 20 ms
// Memory Usage: 17.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      04/09/2022, 15:03:35
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/791535059//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        // boxFlags[boxIdx][digit] == true if the digit exists in the sub-box
        bool boxFlags[9][10] = { false };

        for (int y = 0; y < 9; ++y) {
            bool rowFlags[10] = { false };
            bool colFlags[10] = { false };

            for (int x = 0; x < 9; ++x) {
                // check rows and sub-boxes
                char c = board[y][x];
                if (c != '.') {
                    if (rowFlags[c - '0']) {
                        return false;
                    }
                    else {
                        rowFlags[c - '0'] = true;
                    }

                    int boxIdx = 3 * (y / 3) + (x / 3);
                    if (boxFlags[boxIdx][c - '0']) {
                        return false;
                    }
                    else {
                        boxFlags[boxIdx][c - '0'] = true;
                    }
                }
                
                // interpret x as row idx and y as column idx, and check columns
                c = board[x][y];
                if (c != '.') {
                    if (colFlags[c - '0']) {
                        return false;
                    }
                    else {
                        colFlags[c - '0'] = true;
                    }
                }
            }
        }

        return true;
    }
};