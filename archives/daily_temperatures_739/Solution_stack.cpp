//
// 739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/
// 
// 47 / 47 test cases passed.	Status: Accepted
// Runtime: 268 ms
// Memory Usage: 85 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      13/11/2021, 22:39:52
// LeetCode submit time: 5 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/586825144//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& T) {
        int n = T.size();
        // Imagine the temerature values are pillars in a row, and
        // one is standing at index i and looking towards index n.
        // Which pillars are visible?
        // The nearest's index is at the top of the stack.
        stack<int> visibles;
        vector<int> ans(n);
        for (int i = n - 1; i >= 0; --i) {
            while (visibles.size() && T[visibles.top()] <= T[i]) {
                visibles.pop();
            }
            ans[i] = visibles.size() ? visibles.top() - i : 0;
            visibles.push(i);
        }
        return ans;
    }
};