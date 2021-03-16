// 714. Best Time to Buy and Sell Stock with Transaction Fee
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
// --
// Zhiyong
// 2021-03-16 15:38:22

// Optimized DP.
class Solution3 {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();

        // dp[i].first = maximum profit at [i] if we don't hold the stock at the moment.
        // dp[i].second = ...if we hold.
        // ... actually, we only need to access dp[i - 1].
        // a = dp[i - 1].first
        // b = dp[i - 1].second
        int a = 0, b = -prices[0];

        for (int i = 1; i < n; ++i) {
            int a2 = max(a, b + prices[i] - fee);
            int b2 = max(b, a - prices[i]);
            a = a2;
            b = b2;
        }

        return max(a, b);
    }
};

// DP. Accepted.
class Solution2 {
public:
    int maxProfit(vector<int>& prices, int fee) {
        int n = prices.size();
        
        // dp[i].first = maximum profit at [i] if we don't hold the stock at the moment.
        // dp[i].second = ...if we hold.
        vector<pair<int, int>> dp;
        dp.resize(n, {0, 0});        
       
        dp[0].second = -prices[0];
        for (int i = 1; i < n; ++i) {            
            dp[i].first = max(dp[i - 1].first, dp[i - 1].second + prices[i] - fee);
            dp[i].second = max(dp[i - 1].second, dp[i - 1].first - prices[i]);
            //cout << "#" << i << " " << dp[i].first << " " << dp[i].second << endl;
        }
        
        return max(dp[n - 1].first, dp[n - 1].second);
    }
};

// Recursion + memo. Accepted.
class Solution1 {
    vector<pair<int, int>> memo;
    int calltimes;
    
    // offset - what day is it?
    // bought - have we bought the stock or not?
    // return - how much profit can we make from now till the end (ignoring the profit we have already made)? 
    int maxProfit(const vector<int>& prices, int fee, int offset, bool bought) {
        if (offset == prices.size())
            return 0;
        
        if (bought && memo[offset].first != -1) {
            //cout << "hit memo " << offset << ' ' << bought << endl;
            return memo[offset].first;
        } else if (!bought && memo[offset].second != -1) {
            //cout << "hit memo " << offset << ' ' << bought << endl;
            return memo[offset].second;
        } else {
            //cout << "missed memo " << offset << ' ' << bought << endl;
        }
        
        int ans = 0;
        if (bought > 0) {
            // how about selling it now?
            if (prices[offset] > bought + fee)
                ans = max(ans, prices[offset] - fee + maxProfit(prices, fee, offset + 1, false));
        } else {            
            // how about buy it?
            if (offset + 1 < prices.size() && prices[offset] < prices[offset + 1])
                ans = max(ans,  - prices[offset] + maxProfit(prices, fee, offset + 1, true));
        }
        
        // how about do nothing at the moment?
        ans = max(ans, maxProfit(prices, fee, offset + 1, bought));
        
         if (bought) {
            memo[offset].first = ans;
        } else {
            memo[offset].second = ans;
        }
        ++calltimes;
        return ans;
    }
public:
    int maxProfit(vector<int>& prices, int fee) {
        memo.clear();
        memo.resize(prices.size(), {-1, -1});
        calltimes = 0;
        int ans = maxProfit(prices, fee, 0, 0);
        //cout << "call times " << calltimes << endl;
        return ans;
    }
};
