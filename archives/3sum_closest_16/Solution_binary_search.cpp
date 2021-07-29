//
// 16. 3Sum Closest
// https://leetcode.com/problems/3sum-closest/
// 
// 131 / 131 test cases passed.	Status: Accepted
// Runtime: 72 ms
// Memory Usage: 9.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      29/07/2021, 10:44:50
// LeetCode submit time: 32 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/530155897/?from=explore&item_id=3828/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/*
Approach: for each pair of nums, binary search for the best 3rd num.
*/
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int n = nums.size();
        int ans = 1e9;
        sort(begin(nums), end(nums));
        for (int i = 0; i + 2 < n; ++i) {
            for (int j = i + 1; j + 1 < n; ++j) {
                int best = target - nums[i] - nums[j];
                // find p so that *(p-1) <= best < *p
                auto p = upper_bound(begin(nums) + j + 1, end(nums), best);
                // try *p
                if (p != end(nums) && (ans == 1e9 || abs(ans - target) > abs(*p - best)))
                    ans = nums[i] + nums[j] + *p;
                // try *(p-1), notice that *(p-1) might be [j], in which case it's invalid
                if (ans == 1e9 || (p - 1 > begin(nums) + j && abs(ans - target) > abs(*(p - 1) - best)))
                    ans = nums[i] + nums[j] + *(p - 1);
            }
        }
        return ans;
    }
};