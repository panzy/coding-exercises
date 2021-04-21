/* 120. Triangle
 * https://leetcode.com/problems/triangle/
 *
 * O(2n) space.
 *
 * --
 * Zhiyong 2021-04-21
 */
class Solution {
    public:
        int minimumTotal(vector<vector<int>>& triangle) {
            // Keep the two most recent rows of sum
            vector<int> sumA(triangle.size(), 0);
            vector<int> sumB(triangle.size(), 0);
            vector<int>* a = &sumA, *b = &sumB;

            for (auto&& row : triangle) {
                int n = row.size();

                if (n == 1) {
                    (*a)[0] = row[0];
                    continue;
                }

                (*b)[0] = (*a)[0] + row[0];
                for (int i = 1; i < n - 1; ++i) {
                    (*b)[i] = min((*a)[i - 1], (*a)[i]) + row[i];
                }
                (*b)[n - 1] = (*a)[n - 2] + row[n - 1];

                // swap a and b
                auto c = a;
                a = b;
                b = c;
            }

            // sums of the last row are in *a.
            int ans = (*a)[0];
            for (int i = 1; i < triangle.size(); ++i) {
                ans = min(ans, (*a)[i]);
            }
            return ans;
        }
};
