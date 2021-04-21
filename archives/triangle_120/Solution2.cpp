/* 120. Triangle
 * https://leetcode.com/problems/triangle/
 *
 * O(n) space.
 *
 * --
 * Zhiyong 2021-04-21
 */
class Solution {
    public:
        int minimumTotal(vector<vector<int>>& triangle) {
            int n = triangle.size();

            // The minimum path sums of the current row.
            // Compute the sums from the last row to the first.
            // sums[0] will be the answer.
            vector<int> sums = triangle[n - 1];

            for (int r = n - 2; r >= 0; --r) {
                for (int c = 0; c <= r; ++c) {
                    sums[c] = triangle[r][c] + min(sums[c], sums[c + 1]);
                }
            }

            return sums[0];
        }
};
