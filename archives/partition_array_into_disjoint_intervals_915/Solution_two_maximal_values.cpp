//
// 915. Partition Array into Disjoint Intervals
// https://leetcode.com/problems/partition-array-into-disjoint-intervals/
// 
// 56 / 56 test cases passed.	Status: Accepted
// Runtime: 32 ms
// Memory Usage: 20.4 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      22/07/2021, 19:33:54
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/526786034/?from=explore&item_id=3823/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int partitionDisjoint(vector<int>& nums) {
        int n = nums.size();
        int maxLeft = nums[0]; // max value of the established left part
        int maxSeen = nums[0]; // max value of the potential left part
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < maxLeft) {
                ans = i + 1;
                maxLeft = maxSeen;
            } else if (nums[i] > maxSeen) {
                maxSeen = nums[i];
            }
        }
        return ans;
    }
};