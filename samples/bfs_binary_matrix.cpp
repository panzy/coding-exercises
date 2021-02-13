#include <cmath>
#include <cstdio>
#include <vector>
#include <set>
#include <queue>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
#include <functional>
using namespace std;

/* BFS on a binary matrix. */
class Solution {
public:
    /* 1091. Shortest Path in Binary Matrix
    https://leetcode.com/problems/shortest-path-in-binary-matrix/
    */
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        int visited[100][100] = {}; // value = path length, including the start cell.
        queue<tuple<int, int>> neibs;
        int ans = 0;

        if (grid[0][0] == 0) {
            neibs.push(make_tuple(0, 0));
            visited[0][0] = 1;
        }

        while (visited[n - 1][n - 1] == 0 && !neibs.empty()) {
            auto [r, c] = neibs.front();
            neibs.pop();

            for (int dr = -1; dr <= 1; ++dr) {
                for (int dc = -1; dc <= 1; ++dc) {
                    if ((dr != 0 || dc != 0) &&
                        0 <= r + dr && r + dr < n && 0 <= c + dc && c + dc < n &&
                        visited[r + dr][c + dc] == 0 && grid[r + dr][c + dc] == 0) {
                        visited[r + dr][c + dc] = visited[r][c] + 1;
                        neibs.push(make_tuple(r + dr, c + dc));
                    }
                }

            }
        }

        return visited[n - 1][n - 1] == 0 ? -1 : visited[n - 1][n - 1];
    }
};

int main() {
    {
        vector<vector<int>> grid{ {0, 1}, { 1, 0 } };
        cout << Solution{}.shortestPathBinaryMatrix(grid) << endl;
    }
    {
        vector<vector<int>> grid{ {0, 0, 0}, { 1, 1, 0 }, { 1, 1, 0 } };
		cout << Solution{}.shortestPathBinaryMatrix(grid) << endl;
    }
    {
        vector<vector<int>> grid{ {1, 0, 0}, { 1, 1, 0 }, { 1, 1, 0 } };
		cout << Solution{}.shortestPathBinaryMatrix(grid) << endl;
    }
}
