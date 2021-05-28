//
// 1695. Maximum Erasure Value
// https://leetcode.com/problems/maximum-erasure-value/
// 
// 62 / 62 test cases passed.	Status: Accepted
// Runtime: 148 ms
// Memory Usage: 89.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      28/05/2021, 16:44:16
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/499583695/?from=explore&item_id=3758/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maximumUniqueSubarray(vector<int>& nums) {
        int n = nums.size();  
        int a = 0, b = 1; // defines range [a,b)
        int sum = nums[a];
        int ans = sum;
        bool seen[10001]{};
        seen[nums[a]] = true;
        
        while (b < n) {
            if (seen[nums[b]]) {
                // forward `a` until the previous nums[b] has been removed.
                while (nums[a] != nums[b]) {
                    sum -= nums[a];
                    seen[nums[a]] = false;
                    ++a;
                }
                ++a;
                ++b;
            } else {
                sum += nums[b];
                seen[nums[b]] = true;
                ++b;
                ans = max(ans, sum);
            }
        }
        
        return ans;
    }
};