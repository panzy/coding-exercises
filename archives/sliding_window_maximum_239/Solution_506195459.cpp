//
// 239. Sliding Window Maximum
// https://leetcode.com/problems/sliding-window-maximum/
// 
// 61 / 61 test cases passed.	Status: Accepted
// Runtime: 340 ms
// Memory Usage: 127.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      10/06/2021, 23:32:19
// LeetCode submit time: 1 minute ago
// Submission detail page: https://leetcode.com/submissions/detail/506195459//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        // keep track of num positions of the current window,
        // but without those items that will never be the max from now on.
        // so the queue will be monotonic.
        deque<int> win;
        vector<int> res;
        int n = nums.size();
        res.reserve(n - k + 1);
        
        for (int i = 0; i < n; ++i) {
            // push the current number to the window, invalidating previous, smaller numbers
            while (!win.empty() && nums[win.back()] <= nums[i]) {
                win.pop_back();
            }
            win.push_back(i);
            
            // pop the front if it has been out of scope
            if (!win.empty() && win.front() + k <= i) {
                win.pop_front();
            }
            
            // the max seats at the front of the window
            if (i + 1 >= k) {
                res.push_back(nums[win.front()]);
            }
        }
        
        return res;
    }
};