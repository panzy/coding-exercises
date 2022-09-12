//
// 980. Unique Paths III
// https://leetcode.com/problems/unique-paths-iii/
// 
// 39 / 39 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 7.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      11/09/2022, 23:33:28
// LeetCode submit time: 8 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/797608718//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
private:
    int m, n;
    // which bit of the hash corresponds to the end square?
    int endBitIdx;
    
    // hash: the right-most 20 bits represent the remaining empty sqaures (we have constraint m*n <= 20).
    // spaces: how many empty squares remaining?
    // [u, v]: where is the robot now?
    // return: how many paths from here to the ending square?
    int f(int hash, int spaces, int u, int v) {
        
        int result = 0;
        if (u + 1 < m) {
            int bitIdx = (u + 1) * n + v;
            int mask = 1 << bitIdx;
            if ((hash & mask) != 0) {
                hash &= ~mask;
                result += f(hash, spaces - 1, u + 1, v);
                hash |= 1 << bitIdx;
            } else if (spaces == 0 && bitIdx == endBitIdx) {
                ++result;
            }
        }
        if (u - 1 >= 0) {
            int bitIdx = (u - 1) * n + v;
            int mask = 1 << bitIdx;
            if ((hash & mask) != 0) {
                hash &= ~mask;
                result += f(hash, spaces - 1, u - 1, v);
                hash |= 1 << bitIdx;
            } else if (spaces == 0 && bitIdx == endBitIdx) {
                ++result;
            }
        }
        if (v + 1 < n) {
            int bitIdx = u  * n + v + 1;
            int mask = 1 << bitIdx;
            if ((hash & mask) != 0) {
                hash &= ~mask;
                result += f(hash, spaces - 1, u, v + 1);
                hash |= 1 << bitIdx;
            } else if (spaces == 0 && bitIdx == endBitIdx) {
                ++result;
            }
        }
        if (v - 1 >= 0) {
            int bitIdx = u  * n + v - 1;
            int mask = 1 << bitIdx;
            if ((hash & mask) != 0) {
                hash &= ~mask;
                result += f(hash, spaces - 1, u, v - 1);
                hash |= 1 << bitIdx;
            } else if (spaces == 0 && bitIdx == endBitIdx) {
                ++result;
            }
        }
        
        return result;
    }
public:
    int uniquePathsIII(vector<vector<int>>& grid) {
        m = grid.size();
        n = grid[0].size();
        
        int u, v, spaces = 0, hash = 0;
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    u = i;
                    v = j;
                } else if (grid[i][j] == 0) {
                    ++spaces;
                    hash |= (1 << (i * n + j));
                } else if (grid[i][j] == 2) {
                    endBitIdx = i * n + j;
                }
            }
        }
        
        return f(hash, spaces, u, v);
    }
};