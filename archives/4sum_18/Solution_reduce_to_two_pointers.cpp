//
// 18. 4Sum
// https://leetcode.com/problems/4sum/
// 
// 283 / 283 test cases passed.	Status: Accepted
// Runtime: 16 ms
// Memory Usage: 12.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/07/2021, 14:36:14
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/524034327//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        int n = nums.size();
        vector<vector<int>> res;
        
        sort(begin(nums), end(nums));
        
        for (int u = 0; u + 3 < n; ++u) {
            // skip duplicates of u
            if (u > 0 && nums[u - 1] == nums[u]) continue;
            
            // at this point the problem has been reduced to a three sums one, see also
            // https://leetcode.com/problems/3sum/
            for (int i = u + 1; i + 2 < n; ++i) {
                // skip duplicates of i
                if (i > u + 1 && nums[i - 1] == nums[i]) continue;

                int twoSumTarget = target - nums[u] - nums[i];
                
                for (int j = i + 1, k = n - 1; j < k;) {
                    if (nums[j] + nums[k] == twoSumTarget) {
                        res.push_back({nums[u], nums[i], nums[j], nums[k]});
                        // skip duplicates of j
                        do {
                            ++j;
                        } while (j < n && nums[j - 1] == nums[j]);
                        // skip duplicates of k
                        do {
                            --k;
                        } while (k > j && nums[k] == nums[k + 1]);
                    } else if (nums[j] + nums[k] > twoSumTarget) {
                        if (2 * nums[j] > twoSumTarget)
                            break;
                        --k;
                    } else {
                        if (2 * nums[k] < twoSumTarget)
                            break;
                        ++j;
                    }
                }
            }
        }
        
        return res;
    }
};