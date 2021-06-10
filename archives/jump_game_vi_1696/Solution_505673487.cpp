//
// 1696. Jump Game VI
// https://leetcode.com/problems/jump-game-vi/
// 
// 65 / 65 test cases passed.	Status: Accepted
// Runtime: 472 ms
// Memory Usage: 119.7 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/06/2021, 20:57:51
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/505673487/?from=explore&item_id=3773/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution_intuition {
public:
    int maxResult(vector<int>& nums, int k) {
        // Sample inputs:
        // k = 5
        // nums = [0, -1, -2, -3, -4, -5 , -10, -10, -10, -10, 0]  (-5, 0)
        // nums = [0, -1, -2, -3, -4, -50, -10, -10, -10, -10, 0]  (-1, -10, 0)
        // nums = [0, -1, -2, -3, -4, -50, -10, -10, -10, -1 , 0]  (-4, -1,  0)

        // define dp[i] = maxResult(nums[0:i+1], k) 
        // then   dp[i] = max(dp[i-k:i]) + nums[i]
        vector<int>& dp = nums;
        
        for (int i = 1; i < nums.size(); ++i) {
            dp[i] = *max_element(nums.begin() + max(0, i - k), nums.begin() + i) + nums[i];
        }
        
        return dp.back();
    }
};

// Combine a hash map and a priority queue to keep track of the maximum element of a sliding window.
class Solution {
public:
    int maxResult(vector<int>& nums, int k) {
        vector<int>& dp = nums;
        unordered_map<int, int> scoreCnts; // scoreCnts[score] = 0 means this score has been deleted
        priority_queue<int> scoreHeap;
        int n = nums.size();
        
        scoreCnts[dp[0]] = 1;
        scoreHeap.push(dp[0]);

        for (int i = 1; i < n; ++i) {
            while (!scoreHeap.empty()) {
                int maxPrevScore = scoreHeap.top();
                if (scoreCnts[maxPrevScore] == 0) { // has been deleted
                    scoreHeap.pop();
                    continue;
                }
                else {
                    dp[i] = maxPrevScore + nums[i];
                    break;
                }
            }
            
            scoreHeap.push(dp[i]);
            ++scoreCnts[dp[i]];
            if (i - k >= 0) // out of scope
                --scoreCnts[dp[i - k]];
        }
        
        return dp.back();
    }
};
