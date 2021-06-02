//
// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/
// 
// 728 / 728 test cases passed.	Status: Accepted
// Runtime: 24 ms
// Memory Usage: 26.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      01/06/2021, 20:38:01
// LeetCode submit time: 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/501611465/?from=explore&item_id=3764/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        const vector<pair<int, int>> neibs{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = grid.size();
        int n = grid[0].size();
        int ans = 0;

        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == 1) {
                    // flood the area
                    int area = 1;
                    queue<pair<int, int>> Q;
                    grid[r][c] = 2; // cell value 2 means visited
                    Q.push({r, c});
                    
                    while (!Q.empty()) {
                        auto [r, c] = Q.front();
                        Q.pop();
                        
                        for (auto&& [dx, dy] : neibs) {
                            int r2 = r + dy;
                            int c2 = c + dx;
                            if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n
                                && grid[r2][c2] == 1) {
                                grid[r2][c2] = 2; 
                                ++area;
                                Q.push({r2, c2});
                            }
                        }
                    }
                    
                    ans = max(ans, area);
                }
            }
        }
        
        return ans;
    }
};