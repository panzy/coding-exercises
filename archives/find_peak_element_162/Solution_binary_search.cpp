//
// 162. Find Peak Element
// https://leetcode.com/problems/find-peak-element/
// 
// 63 / 63 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 8.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      13/07/2021, 18:05:26
// LeetCode submit time: 6 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/522117377//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int lo = 0, hi = nums.size() - 1;
        
        while (lo < hi) {
            int m = (lo + hi) / 2, m2 = m + 1;
            if (nums[m] < nums[m + 1]) {
                lo = m + 1; // there must be a peak in [m+1:hi]
            } else { // nums[m] > nums[m+1], constraint: nums[i] != nums[i + 1] for all valid i
                hi = m; // there must be a peak in [lo,m]
            }
        }
        
        return lo;
    }
};