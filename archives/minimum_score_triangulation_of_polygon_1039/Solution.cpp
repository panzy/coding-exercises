/*
1039. Minimum Score Triangulation of Polygon
https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
--
Zhiyong
2021-02-24
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
private:
    int memo[51][51]{};

    // return the minimum score of polygon {i, i + 1, ..., j}, where i <= j.
    int score(const vector<int>& A, int i, int j) {
        if (i == j) return 0;
        if (memo[i][j] != 0) return memo[i][j];

        // For each vertex that can form a triangle with [i] and [j].
        int ans = 0;
        for (int k = i + 1; k <= j - 1; ++k) {
            int s = A[i] * A[j] * A[k] + score(A, i, k) + score(A, k, j);
            if (ans == 0 || ans > s)
                ans = s;
        }
        return memo[i][j] = ans;
    }

public:
    int minScoreTriangulation(const vector<int>& A) {
        return score(A, 0, A.size() - 1);
    }

    int minScoreTriangulation_bruteforce(const vector<int>& A) {
        const int n = A.size();

        if (n < 3) return 0;
        if (n == 3) return A[0] * A[1] * A[2];
        if (n == 4) return min(A[0] * A[1] * A[2] + A[2] * A[3] * A[0], A[1] * A[2] * A[3] + A[3] * A[0] * A[1]);

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            vector<int> rest1;
            rest1.reserve(n - 1);

            for (int j = 0; j < n; ++j) {
                if (j != (i + 1) % n)
                    rest1.push_back(A[j]);
            }

            int score = A[i] * A[(i + 1) % n] * A[(i + 2) % n] + minScoreTriangulation(rest1);
            if (ans == 0 || ans > score)
                ans = score;
        }

        return ans;
    }
};

int main() {
    int ans;
    _ASSERT(6 == (ans = Solution().minScoreTriangulation({ 1,2,3 })));
    _ASSERT(8 == (ans = Solution().minScoreTriangulation({ 2,2,2,1 })));
    _ASSERT(144 == (ans = Solution().minScoreTriangulation({ 3,7,4,5 })));
    _ASSERT(27 == (ans = Solution().minScoreTriangulation({ 3,2,3,5,1 })));
    _ASSERT(12 == (ans = Solution().minScoreTriangulation({ 2,2,2,2,1 })));
    _ASSERT(24 == (ans = Solution().minScoreTriangulation({ 2,2,2,2,2 })));
    _ASSERT(81 == (ans = Solution().minScoreTriangulation({ 3,3,3,3,3 })));
    _ASSERT(108 == (ans = Solution().minScoreTriangulation({ 3,3,3,3,3,3 })));
    _ASSERT(132 == (ans = Solution().minScoreTriangulation({ 4,3,4,3,5 })));
    _ASSERT(13 == (ans = Solution().minScoreTriangulation({ 1,3,1,4,1,5 })));
    _ASSERT(14 == (ans = Solution().minScoreTriangulation({ 1,3,1,4,1,5,1 })));
    _ASSERT(20 == (ans = Solution().minScoreTriangulation({ 1,3,1,4,1,5,1,6 })));
    _ASSERT(21 == (ans = Solution().minScoreTriangulation({ 1,3,1,4,1,5,1,6,1 })));
    _ASSERT(28 == (ans = Solution().minScoreTriangulation({ 1,3,1,4,1,5,1,6,1,7 })));
    _ASSERT(140295 == (ans = Solution().minScoreTriangulation({ 35,73,90,27,71,80,21,33,33,13,48,12,68,70,80,36,66,3,70,58 })));
    return 0;
}
