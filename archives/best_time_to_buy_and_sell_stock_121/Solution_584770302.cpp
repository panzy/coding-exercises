//
// 121. Best Time to Buy and Sell Stock
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// 
// 211 / 211 test cases passed.	Status: Accepted
// Runtime: 112 ms
// Memory Usage: 93.3 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      09/11/2021, 22:17:59
// LeetCode submit time: 1 hour, 11 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/584770302//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        int minPrice = prices[0];  
        int maxProfit = 0;
        for (int i = 1; i < n; ++i) {
            maxProfit = max(maxProfit, prices[i] - minPrice);
            minPrice = min(minPrice, prices[i]);
        }
        return maxProfit;
    }
};