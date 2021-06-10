//
// 1696. Jump Game VI
// https://leetcode.com/problems/jump-game-vi/
// 
// 66 / 66 test cases passed.	Status: Accepted
// Runtime: 300 ms
// Memory Usage: 78.5 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/06/2021, 23:52:22
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/505730268/?from=explore&item_id=3773/
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
class Solution_heap_and_map {
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

class Solution_deque {
public:
    int maxResult(vector<int>& nums, int k) {
        vector<int>& dp = nums;
        // prev scores
        // item = { position, score }
        // 1. keep track of k items at most,
        // 2. scores before the maximum are dropped as soon as possible,
        // 3. so there is no cost to find the maximum score -- it's always the front
        // 4. so the scores are in descending order
        deque<pair<int, int>> scores;
        int n = nums.size();
        
        scores.push_back({0, dp[0]});

        for (int i = 1; i < n; ++i) {
            dp[i] = scores.front().second + nums[i];
            
            if (!scores.empty() && i - scores.front().first >= k) // out of scope
                scores.pop_front();
            
            // keep descending
            while (!scores.empty() && scores.back().second < dp[i])
                scores.pop_back();
            
            scores.push_back({i, dp[i]});
        }
        
        return dp.back();
    }
};

class Solution_deque_optimized {
public:
    int maxResult(vector<int>& nums, int k) {
        vector<int>& dp = nums;
        // similar to the previous implementation, but hold position only
        // because the score can be retrieve via the position.
        deque<int> scores;
        int n = nums.size();
        
        scores.push_back(0);

        for (int i = 1; i < n; ++i) {
            dp[i] = dp[scores.front()] + nums[i];
            
            if (!scores.empty() && i - scores.front() >= k) // out of scope
                scores.pop_front();
            
            // keep descending
            while (!scores.empty() && dp[scores.back()] < dp[i])
                scores.pop_back();
            
            scores.push_back(i);
        }
        
        return dp.back();
    }
};

using Solution = Solution_deque_optimized;
