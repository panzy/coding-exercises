/* 554. Brick Wall
 * https://leetcode.com/problems/brick-wall/
 * --
 * Zhiyong 2021-04-22
 */

class Solution {
public:
    int leastBricks(vector<vector<int>>& wall) {
        // gaps[horizontal position] = number of brick edges here.
        // The two edges of the whole wall are not included.
        unordered_map<int, int> gaps;
        
        for (auto&& row : wall) {
            int x = 0;
            for (int i = 0; i < row.size() - 1; ++i) {
                x += row[i];
                gaps[x]++;
            }
        }
        
        int maxGap = 0;
        // Note that gaps might be empty.
        for (auto&& p : gaps) {
            maxGap = max(maxGap, p.second);
        }
        return wall.size() - maxGap;
    }
};
