/*
74. Search a 2D Matrix
https://leetcode.com/problems/search-a-2d-matrix/
--
Zhiyong
2021-02-23
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

#define FOR(i, n) for (int i = 0; i < n; ++i)
#define FOR_RANGE(i, begin, end) for (int i = begin; i < end; ++i)

// 4 neighbour cells in a grid.
const int NEIBS4[4][2] = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };

template <class T>
pair<int, int> grid_size(const vector<vector<T>>& grid) {
    return { grid.size(), grid[0].size() };
}

template <class T>
T min_element_2d(const vector<vector<T>>& grid) {
    T ans = numeric_limits<T>::max();
    for (auto&& row : grid)
        ans = min(ans, *min_element(row.begin(), row.end()));
    return ans;
}

class Solution {
public:
    // A fast approach.
    // Runtime: 0 ms, faster than 100.00% of C++ online submissions for Search a 2D Matrix.
    // Memory Usage: 9.5 MB, less than 85.24% of C++ online submissions for Search a 2D Matrix.
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        auto [m, n] = grid_size(matrix);
        int r = 0;
        for (; r < m && matrix[r][n - 1] < target; ++r);
        return r < m && binary_search(matrix[r].begin(), matrix[r].end(), target);
    }

    // Another approach, a bit slower: Search from top-right corner.
    bool searchMatrix2(vector<vector<int>>& matrix, int target) {
        auto [m, n] = grid_size(matrix);

        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] == target)
                return true;
            else if (matrix[r][c] < target) {
                ++r;
            }
            else {
                --c;
            }
        }

        return false;
    }
};

