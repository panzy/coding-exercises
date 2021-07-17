//
// 1. Two Sum
// https://leetcode.com/problems/two-sum/
// 
// 54 / 54 test cases passed.	Status: Accepted
// Runtime: 12 ms
// Memory Usage: 10.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/07/2021, 13:47:55
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/524013148//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int n = nums.size();
        vector<pair<int, int>> nums2;

        nums2.reserve(n);
        for (int i = 0; i < n; ++i)
            nums2.push_back({nums[i], i});
        
        sort(begin(nums2), end(nums2));
        
        int i = 0, j = n - 1;
        while (i < j) {
            if (nums2[i].first + nums2[j].first == target) {
                break;
            } else if (nums2[i].first + nums2[j].first > target) {
                --j;
            } else {
                ++i;
            }
        }
        return {nums2[i].second, nums2[j].second};
    }
};