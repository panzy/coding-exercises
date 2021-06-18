//
// 795. Number of Subarrays with Bounded Maximum
// https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
// 
// 43 / 43 test cases passed.	Status: Accepted
// Runtime: 20 ms
// Memory Usage: 32.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      17/06/2021, 20:20:50
// LeetCode submit time: 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/509470029/?from=explore&item_id=3782/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int numSubarrayBoundedMax(const vector<int>& nums, int left, int right) {
        int n = nums.size();
        int ans = 0;
        
        // Find all elements greater than right.
        // Use them as delimiters; valid subarrays are between them.
        // nums[n] is also considered a delimiter.
        for (int i = 0, beginPos = 0; i <= n; ++i) {
            if (i == n || nums[i] > right) {
                ans += go(nums, beginPos, i, left, right);
                beginPos = i + 1;
            }
        }  
        
        return ans;
    }
    
private:
    // Given the length of an array, return the number of non-empty subarrays.
    int numAnySubarray(int n) {
        // A subarray can be defined as a pair of indices a and b where a <= b.
        // For 0 <= a < n and 0 <= b < n, the total number of pairs is n * n.
        // Among them, there are n pairs where a==b,
        // and half the rest is invalid, i.e. a > b.
        return n * n - (n * n - n) / 2;
    }
    
    // Return number of valid subarrays of array nums[beginPos:endPos], not including nums[endPos].
    // Assume min(nums[beginPos:endPos]) <= right
    int go(const vector<int>& nums, int beginPos, int endPos, int left, int right) {
        // Find all elements between left and right.
        // Use these elements as delimiters, any subarrays not containing any of these delimiters are invalid;
        // all the rest are valid.
        
        int numInvalid = 0;
        int invalidSpanBegin = beginPos;
        for (int i = beginPos; i <= endPos; ++i) {
            if (i == endPos || left <= nums[i] && nums[i] <= right) { // got a delimiter
                numInvalid += numAnySubarray(i - invalidSpanBegin);
                invalidSpanBegin = i + 1;
            }
        }
        return numAnySubarray(endPos - beginPos) - numInvalid;
    }
};