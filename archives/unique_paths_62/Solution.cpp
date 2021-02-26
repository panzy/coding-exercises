/*
62. Unique Paths
https://leetcode.com/problems/unique-paths/
--
Zhiyong
2021-02-26
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
using namespace std;


class Solution {
public:
    int uniquePaths(int m, int n) {
        int dp[100][100]{};

        for (int r = 0; r < m; ++r) {
            dp[r][n - 1] = 1;
        }

        for (int c = 0; c < n; ++c) {
            dp[m - 1][c] = 1;
        }

        for (int r = m - 2; r >= 0; --r) {
            for (int c = n - 2; c >= 0; --c) {
                dp[r][c] = dp[r + 1][c] + dp[r][c + 1];
            }
        }

        return dp[0][0];
    }
};
