//
// 611. Valid Triangle Number
// https://leetcode.com/problems/valid-triangle-number/
// 
// 233 / 233 test cases passed.	Status: Accepted
// Runtime: 120 ms
// Memory Usage: 12.8 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      15/07/2021, 23:29:23
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/523254361/?from=explore&item_id=3815/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        int n = nums.size();
        int badCnt = 0;
        
        sort(begin(nums), end(nums));
        
        // get rid of 0s
        int start = 0;
        while (start < n && nums[start] == 0) ++start;
        
        for (int i = start; i + 2 < n; ++i) {
            int k = i + 2; // the position of the first side length that is too long
            for (int j = i + 1; j + 1 < n; ++j) {
                while (k < n && nums[k] < nums[i] + nums[j]) ++k;
                    badCnt += n - k;
            }
            // Note that for each i, k only increase from i+2 to n one round.
            // So the entire time complexity is O(n*n) + O(n*n) = O(n*n), not O(n*n*n).
            // The first O(n*n) is for i and j, the second is for i and k.
        }
        
        int m = n - start;
        return m * (m - 1) * (m - 2) / 6 - badCnt;
    }
};