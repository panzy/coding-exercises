/*
818. Race Car
https://leetcode.com/problems/race-car/
--
Zhiyong
2021-02-21
*/
#include <iostream>
#include <numeric>
#include <array>
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

typedef long long LL;
typedef unsigned long long ULL;

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
private:
    ULL makekey(LL position, LL speed) {
        ULL key = ((ULL)abs(position) << 33) + ((ULL)abs(speed) << 1);
        if (position < 0)
            key += 0x100000000;
        if (speed < 0)
            key++;
        return key;
    }

public:
    // BFS, Time limit exceeded.
    int racecar(int target) {
        // { position, speed, instructions }
        queue<array<LL, 3>> Q;
        set<ULL> seen;

        Q.push({ 0, 1, 0 });
        seen.insert(makekey(0, 1));

        while (Q.front()[0] != target) {
            auto [p, s, i] = Q.front();
            Q.pop();

            // A?
            ULL key = makekey(p + s, s * 2);
            if (seen.find(key) == seen.end()) {
                Q.push({ p + s, s * 2, i + 1 });
                seen.insert(key);
            }

            // R?
            key = makekey(p, s > 0 ? -1 : 1);
            if (seen.find(key) == seen.end()) {
                Q.push({ p, s > 0 ? -1 : 1, i + 1 });
                seen.insert(key);
            }
        }

        return Q.front()[2];
    }
};

int main() {
    int ans;
    _ASSERT(2 == (ans = Solution().racecar(3)));
    _ASSERT(5 == (ans = Solution().racecar(6)));
    _ASSERT(20 == (ans = Solution().racecar(500)));
    _ASSERT(45 == (ans = Solution().racecar(10000)));
    return 0;
}
