//
// 611. Valid Triangle Number
// https://leetcode.com/problems/valid-triangle-number/
// 
// 233 / 233 test cases passed.	Status: Accepted
// Runtime: 508 ms
// Memory Usage: 12.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      15/07/2021, 23:28:29
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/523242217/?from=explore&item_id=3815/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        int n = nums.size();
        int badCnt = 0;
        
        sort(begin(nums), end(nums));
        
        // get rid of 0s
        auto p = upper_bound(begin(nums), end(nums), 0);
        int start = p - begin(nums);
        
        for (int i = start; i + 2 < n; ++i) {
            for (int j = i + 1; j + 1 < n; ++j) {
                p = lower_bound(begin(nums) + j + 1, end(nums), nums[i] + nums[j]);
                badCnt += end(nums) - p;
            }
        }
        
        int m = n - start;
        return m * (m - 1) * (m - 2) / 6 - badCnt;
    }
};