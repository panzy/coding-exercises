//
// 1. Two Sum
// https://leetcode.com/problems/two-sum/
// 
// 54 / 54 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 10.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/07/2021, 13:36:40
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/524008339//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> seen;
        seen[nums[0]] = 0;
        for (int i = 1, n = nums.size(); i < n; ++i) {
            if (seen.count(target - nums[i]))
                return {seen[target - nums[i]], i};
            seen[nums[i]] = i;
        }
        return {-1, -1};
    }
};