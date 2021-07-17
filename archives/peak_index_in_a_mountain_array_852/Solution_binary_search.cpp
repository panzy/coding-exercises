//
// 852. Peak Index in a Mountain Array
// https://leetcode.com/problems/peak-index-in-a-mountain-array/
// 
// 34 / 34 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 11.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/07/2021, 13:59:50
// LeetCode submit time: 1 day, 2 hours ago
// Submission detail page: https://leetcode.com/submissions/detail/523510781//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int peakIndexInMountainArray(vector<int>& arr) {
        int lo = 0, hi = arr.size() - 1;
        while (lo < hi) {
            int m = (lo + hi) / 2;
            if (arr[m] < arr[m + 1]) {
                lo = m + 1;
            } else { // since there is guaranteed to be a mountain, arr[m] must be > arr[m+1]
                hi = m;
            }
        }
        return lo;
    }
};