/*
--
Zhiyong
2021-02
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

int main() {
    return 0;
}
