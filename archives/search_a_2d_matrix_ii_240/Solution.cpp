/*
240. Search a 2D Matrix II
https://leetcode.com/problems/search-a-2d-matrix-ii/
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
    // Search from top-right corner.
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
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

int main() {
    {
        vector<vector<int>> matrix = {
            {1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}
        };
        _ASSERT(!Solution().searchMatrix(matrix, 20));
    }
    {
        vector<vector<int>> matrix = {
            {1,4,7,11,15} ,{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}
        };

        for (auto&& row : matrix) {
            for (int target : row) {
                _ASSERT(Solution().searchMatrix(matrix, target));
            }
        }
    }
    return 0;
}
