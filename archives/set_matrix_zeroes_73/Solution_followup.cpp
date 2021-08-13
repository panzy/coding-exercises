//
// 73. Set Matrix Zeroes
// https://leetcode.com/problems/set-matrix-zeroes/
// 
// 164 / 164 test cases passed.	Status: Accepted
// Runtime: 37 ms
// Memory Usage: 13.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      13/08/2021, 18:43:57
// LeetCode submit time: 3 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/538108708/?from=explore&item_id=3888/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        int rows = matrix.size(), cols = matrix[0].size();
        
        // the coordinates of any zero cell,
        // the cell will be viewed as the origin of a cartesian coordinate system.
        int cx = -1, cy = -1;
        
        // find the first zero cell and mark the others by projecting them to row cy and col cx.
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < cols; ++x) {
                if (matrix[y][x] == 0) {
                    if (cx == -1) {
                        cx = x;
                        cy = y;
                    } else {
                        matrix[y][cx] = matrix[cy][x] = 0;
                    }
                }
            }
        }
        
        if (cx != -1) {
            // using row cy and col cx as references, erase rows and cols containing zero(s).
            // but don't touch the reference row and col!
            for (int x = 0; x < cols; ++x) {
                if (x != cx && matrix[cy][x] == 0) {
                    // erase the col
                    for (int y = 0; y < rows; ++y) {
                        matrix[y][x] = 0;
                    }
                }
            }
            for (int y = 0; y < rows; ++y) {
                if (y != cy && matrix[y][cx] == 0) {
                    // erase the row
                    for (int x = 0; x < cols; ++x) {
                        matrix[y][x] = 0;
                    }
                }
            }
            
            // now clear the reference row and col.
            for (int x = 0; x < cols; ++x) {
                matrix[cy][x] = 0;
            }
            for (int y = 0; y < rows; ++y) {
                matrix[y][cx] = 0;
            }
        }
    }
};