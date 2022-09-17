//
// 1411. Number of Ways to Paint N × 3 Grid
// https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
// 
// 50 / 50 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 5.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/09/2022, 16:28:14
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/802324434//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numOfWays(int n) {
        const int M = 1e9 + 7;
        
        // If we define f(i, m) = the number of ways paint ix3 grid with the last row using
        // m kinds of colors, then we have:
        // a. m = 2 or 3, and
        // b. numOfWays(n) = f(n, 2) + f(n, 3)
        
        // Whatever i is, let f2 = f(i-1, 2), f3 = f(i-1, 3)
        long long f2 = 6, f3 = 6;
        
        for (int i = 2; i <= n; ++i) {
            long long x = (f2 * 3 + f3 * 2) % M;
            long long y = (f2 * 2 + f3 * 2) % M;
            f2 = x;
            f3 = y;
        }
        
        return (f2 + f3) % M;
    }
};