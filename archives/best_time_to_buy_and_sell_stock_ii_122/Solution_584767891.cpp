//
// 122. Best Time to Buy and Sell Stock II
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// 
// 200 / 200 test cases passed.	Status: Accepted
// Runtime: 4 ms
// Memory Usage: 13 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/11/2021, 22:17:36
// LeetCode submit time: 1 hour, 17 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584767891//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int profit = -prices[0]; // buy on the 0-th day
        
        for (int i = 1; i < n; ++i) {
            if (prices[i] < prices[i - 1]) {
                // it's falling, go back to yesterday and sell it
                profit += prices[i - 1];
                profit -= prices[i]; // buy again, in case it rises
            }
        }
        
        // always sell on the last day
        profit += prices[n - 1];
        
        return profit;
    }
};