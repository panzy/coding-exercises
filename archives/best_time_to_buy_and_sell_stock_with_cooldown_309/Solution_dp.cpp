//
// 309. Best Time to Buy and Sell Stock with Cooldown
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
// 
// 210 / 210 test cases passed.	Status: Accepted
// Runtime: 0 ms
// Memory Usage: 11.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      11/11/2021, 20:30:42
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/585789999//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    int maxProfit(const vector<int>& prices) {
        int n = prices.size();

        // Consider input [11,12,15,10,15,0,19]. Linear scanning won't work, so use DP.
        //
        // For a given day, there might be 3 states:
        // - HOLD: buy before or on that day;
        // - SELL: shell on that day;
        // - COOLDOWN: either we have to cooldown because we sold yesterday, or
        // we choose not to buy.

        // profits of each states of the current day
        int hold = -prices[0];
        int sell = 0;
        int cooldown = 0;

        for (int i = 1; i < n; ++i) {
            int hold2 = max(hold, cooldown - prices[i]);
            int sell2 = hold + prices[i];
            int cooldown2 = max(sell, cooldown);
            hold = hold2;
            sell = sell2;
            cooldown = cooldown2;
        }

        return max(sell, cooldown);
    }
};
