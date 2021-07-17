//
// 15. 3Sum
// https://leetcode.com/problems/3sum/
// 
// 318 / 318 test cases passed.	Status: Accepted
// Runtime: 76 ms
// Memory Usage: 20 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/07/2021, 14:27:30
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/524029862//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> res;
        
        sort(begin(nums), end(nums));
        
        for (int i = 0; i + 2 < n; ++i) {
            // skip duplicates of i
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            
            int twoSumTarget = 0 - nums[i];
            for (int j = i + 1, k = n - 1; j < k;) {
                if (nums[j] + nums[k] == twoSumTarget) {
                    res.push_back({nums[i], nums[j], nums[k]});
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
        
        return res;
    }
};