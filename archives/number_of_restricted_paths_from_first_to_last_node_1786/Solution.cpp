/*
1786. Number of Restricted Paths From First to Last Node
https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
--
Zhiyong
2021-03-06
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
#include <tuple>
#include <algorithm>
#include <functional>
#include <regex>
#include <mutex>
#include <barrier>
#include <cassert>
using namespace std;

class Solution {
public:
    int countRestrictedPaths(int n, vector<vector<int>>& edges) {
        const int M = 1e9 + 7;

        // node index -> [{node index, weight}]
        vector<vector<pair<int, int>>> neibs(n + 1, vector<pair<int, int>>{});
        neibs.reserve(n + 1);
        for (auto&& e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            neibs[u].push_back({ v, w });
            neibs[v].push_back({ u, w });
        }

        // D[i] = distance from the i-th node to the n-th node.
        vector<int> D;
        D.resize(n + 1);
        fill(D.begin(), D.end(), -1);

        // cnts[i] = the number of restricted paths from node i to node n.  
        // cnts[1] is the answer.
        vector<int64_t> cnts; // count
        cnts.resize(n + 1);
        fill(cnts.begin(), cnts.end(), 0);
        cnts[n] = 1;

        // priority queue for Dijstra algo
        auto cmp = [&D](pair<int, int> i, pair<int, int> j) { return i.second > j.second; };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(cmp)> Q(cmp);

        Q.push({ n, 0 });
        int closedNodes = 0;

        while (closedNodes < n) {
            auto i = Q.top();
            Q.pop();

            if (D[i.first] == -1) {
                D[i.first] = i.second;
                ++closedNodes;

                // Accumulate neibs' answers to me.
                for (auto [j, w] : neibs[i.first]) {
                    if (D[j] != -1) {
                        if (D[j] < D[i.first]) {
                            cnts[i.first] = (cnts[i.first] + cnts[j]) % M;
                        }
                    }
                }
            }
            else {
                assert(D[i.first] <= i.second);
                continue;
            }

            for (auto [j, w] : neibs[i.first]) {
                if (D[j] == -1) { // not closed
                    Q.push({ j, i.second + w });
                }
            }
        }

        return (int)(cnts[1] % M);
    }
};

int main() {
    int n, ans;
    vector<vector<int>> edges;

    n = 10;
    edges = { {9, 10, 8}, {9, 6, 5}, {1, 5, 9}, {6, 8, 10}, {1, 8, 1}, {8, 10, 7}, {10, 7, 9}, {5, 7, 3}, {4, 2, 9}, {2, 3, 9}, {3, 10, 4}, {1, 4, 7}, {7, 6, 1}, {3, 9, 8}, {9, 1, 6}, {4, 7, 10}, {9, 4, 9} };
    _ASSERT(1 == (ans = Solution().countRestrictedPaths(n, edges)));

    n = 1;
    edges = {};
    _ASSERT(1 == (ans = Solution().countRestrictedPaths(n, edges)));

    n = 2;
    edges = { {1, 2, 1} };
    _ASSERT(1 == (ans = Solution().countRestrictedPaths(n, edges)));

    n = 5;
    edges = {{1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}};
    _ASSERT(3 == (ans = Solution().countRestrictedPaths(n, edges)));
    
    n = 7;
    edges = {{1, 3, 1}, {4, 1, 2}, {7, 3, 4}, {2, 5, 3}, {5, 6, 1}, {6, 7, 2}, {7, 5, 3}, {2, 6, 4}};
    _ASSERT(1 == (ans = Solution().countRestrictedPaths(n, edges)));
}
