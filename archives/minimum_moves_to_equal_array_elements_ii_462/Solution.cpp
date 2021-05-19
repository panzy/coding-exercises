/**
 * 462. Minimum Moves to Equal Array Elements II
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 * 
 * --
 * Created by Zhiyong 
 * 2021-05-19, 4:18:49 p.m.
 */

class Solution {
    public:
        int minMoves2(vector<int>& nums) {
            auto n = nums.size();

            sort(nums.begin(), nums.end());

            // sum[i + 1] = a0 + a1 + ... + a[i]
            vector<int64_t> sum(n + 1, 0);

            for (auto i = 0; i < n; ++i) {
                sum[i + 1] = sum[i] + nums[i];
            }

            int64_t ans = -1;

            for (auto i = 0; i < n; ++i) {
                // assume we are changing all elements to nums[i], then
                // moves = nums[i+1:n-1] - (n-i-1) * nums[i]
                //        -nums[0:k-1] + i * nums[i]
                auto moves = (sum[n] - sum[i + 1]) - sum[i] + (2 * i - n + 1) * nums[i];
                if (ans == -1 || ans > moves)
                    ans = moves;
            }

            return ans;
        }
};
