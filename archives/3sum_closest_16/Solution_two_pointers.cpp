//
// 16. 3Sum Closest
// https://leetcode.com/problems/3sum-closest/
// 
// 131 / 131 test cases passed.	Status: Accepted
// Runtime: 20 ms
// Memory Usage: 9.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      29/07/2021, 10:50:28
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/530170253/?from=explore&item_id=3828/
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
            for (int j = i + 1, k = n; j + 1 < n; ++j) {
                int best = target - nums[i] - nums[j];
                
                // no need to binary search, we know k must go left unless it's already less than j+1.
                k = max(k, j + 1);
                while (k - 1 > j && nums[k - 1] > best) --k;
                
                // try [k]
                if (k < n && (ans == 1e9 || abs(ans - target) > abs(nums[k] - best)))
                    ans = nums[i] + nums[j] + nums[k];
                // try [k-1], notice that k-1 might equal j, in which case it's invalid
                if (ans == 1e9 || (k - 1 > j && abs(ans - target) > abs(nums[k - 1] - best)))
                    ans = nums[i] + nums[j] + nums[k - 1];
            }
        }
        return ans;
    }
};