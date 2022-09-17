//
// 790. Domino and Tromino Tiling
// https://leetcode.com/problems/domino-and-tromino-tiling/
// 
// 39 / 39 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 5.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/09/2022, 15:48:29
// LeetCode submit time: 17 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/802289478//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numTilings(int n) {
        const int M =  1e9 + 7;
        
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 5;
        }

        // f[i][0] = number of ways to tile 2xi board
        // example: █████
        // f[i][1] = number of ways to tile 2xi board except the bottom-right cell,
        // example: ████▀
        // f[i][2] = number of ways to tile 2xi board except the top-right cell,
        // example: ████▄
        long long f[n + 1][3];
        
        f[2][0] = 2;
        f[2][1] = 1;
        f[2][2] = 1;
        
        f[3][0] = 5;
        f[3][1] = 2;
        f[3][2] = 2;
        
        for (int i = 4; i <= n; ++i) {
            f[i][0] = (f[i - 1][0] + f[i - 1][1] + f[i - 1][2] + f[i - 2][0]) % M;
            f[i][1] = (f[i - 1][2] + f[i - 2][0]) % M;
            f[i][2] = (f[i - 1][1] + f[i - 2][0]) % M;
        }

        return f[n][0];
    }
};