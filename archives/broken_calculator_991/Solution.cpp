/*
991. Broken Calculator
https://leetcode.com/problems/broken-calculator/
--
Zhiyong
2021-02-21
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
    // Greedy from Y.
    int brokenCalc(int X, int Y) {
        int ans = 0;
        while (X != Y) {
            if (X >= Y) {
                ans += X - Y;
                Y = X;
            }
            else if (Y % 2 == 0) {
                ans++;
                Y /= 2;
            }
            else {
                ans += 2;
                Y = (Y + 1) / 2;
            }
        }
        return ans;
    }

    // BFS from X.
    // Each number X is connected to 2X and X-1.
    // [Time Limit Exceeded]
    int brokenCalc_bfs(int X, int Y) {
        // Q[i] = { X, cost }.
        queue<pair<int, int>> Q;
        set<int> seen;

        Q.push({ X, 0 });
        seen.insert(X);

        while (Q.front().first != Y) {
            auto [X, cost] = Q.front();
            Q.pop();

            if (X < Y && seen.find(X * 2) == seen.end()) {
                Q.push({ X * 2, cost + 1 });
                seen.insert(X * 2);
            }
            if (X > 1 && seen.find(X - 1) == seen.end()) {
                // Things become wild when X is relatively big. Too many useless X-1 were pushed to the queue.
                Q.push({ X - 1, cost + 1 });
                seen.insert(X - 1);
            }
        }

        return Q.front().second;
    }
};

int main() {
    int ans;
    _ASSERT(24 == (ans = Solution().brokenCalc(1, 1e5)));
    _ASSERT(12 == (ans = Solution().brokenCalc(7, 129)));
    _ASSERT(8 == (ans = Solution().brokenCalc(7, 128)));
    _ASSERT(2 == (ans = Solution().brokenCalc(2, 3)));
    _ASSERT(2 == (ans = Solution().brokenCalc(5, 8)));
    _ASSERT(1023 == (ans = Solution().brokenCalc(1024, 1)));
    _ASSERT(3 == (ans = Solution().brokenCalc(3, 10)));
    return 0;
}
