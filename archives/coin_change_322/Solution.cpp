/*
322. Coin Change
https://leetcode.com/problems/coin-change/

Implemented two algorithms, one is backtracking, the other is recursive.

Both of them are essentially DFS. The backtracking approach is an iterative DFS. It runs a little faster then the recursive one.

TODO: DP and recursion + memo.

--
Zhiyong
2021-03-11
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <tuple>
#include <algorithm>
#include <functional>
#include <regex>
#include <mutex>
#include <barrier>
#include <cassert>
#include <bitset>
using namespace std;

// Backtracking approach.
// This is very fast. Beats 98.81% of cpp submissions.
class Solution1 {
public:
    int coinChange(vector<int>& coins, int amount) {
        if (amount == 0) return 0;

        sort(coins.begin(), coins.end(), greater<int>{});

        int n = coins.size();        
        
        // m[i] = number of coins[i].
        // 1 <= coins.length <= 12 
        // For an answer, we have:
        //      amount == sum(coins[i] * m[i]) where i = 0 to n - 1, and
        //      ans = sum(m[i]).
        int m[12]{};

        // next coin index
        int i = 0;

        // how many coins have been selected.
        // selections = sum(m[i])
        int selections = 0;

        int ans = -1;

        while (true) {
            // see a better answer?
            if (amount == 0 && (ans == -1 || ans > selections)) {
                ans = selections;
            }

            // find the next greatest coin that fits
            while (i < n && coins[i] > amount)
                ++i;
            
            if (i < n) {
                // select as many of this denomination as possible
                int d = amount / coins[i];
                m[i] = d;
                selections += d;
                amount %= coins[i];
                ++i;

                if (ans == -1 || ans > selections) {
                    // move forward with next coin
                    continue;
                }
            }

            // Since we're not moving forward with next coin, we'll backtrack. That is:
            // reduce the count of the last coin by one and set the offset to its next coin.

            if (i == 0)
                break; // there's nothing to backtrack, we're done.

            // step 1: drop coins[i]
            --i; // revert the ++i operation after selecting coins[i] earlier.
            amount += coins[i] * m[i];
            selections -= m[i];
            m[i] = 0;

            // step 2: reduce number of coins[j] by 1, where j < i.
            --i;
            // skip empty coins
            while (i >= 0 && m[i] == 0)
                --i;
            if (i < 0)
                break; // done
            // drop one coin of this denomination
            amount += coins[i];
            --m[i];
            --selections;
            ++i; // will start from the next coin
        }
        
        return ans;
    }
};

// Recursive approach.
// This is fast. Beats 97.23% of cpp submissions.
class Solution2 {
    // the best answer so far.
    int ans = -1;
public:
    void coinChange_recursive_helper(vector<int>& coins, int begin, int amount, int count) {
        if (amount == 0) {
            if (ans == -1 || ans > count)
                ans = count;
            return;
        }
        if (begin >= coins.size())
            return;

        // The possible count of coins[begin] is in {0, 1, ..., amount / coins[begin]}.
        // Both traversal direction are correct, but traversing from max to zero is more effective.
        int64_t remain = amount % coins[begin];
        for (int i = amount / coins[begin];
            i >= 0 && remain >= 0 &&
            // it's important to use the global answer to prune the search space,
            // and update the global answer frequently enough.
            // Note that this optimization is correct only when coins are in descending order.
            (ans == -1 || count + i <= ans);
            --i)
        {
            coinChange_recursive_helper(coins, begin + 1, remain, count + i);
            remain += coins[begin];
        }
    }

    // Recursive
    int coinChange(vector<int>& coins, int amount) {
        sort(coins.begin(), coins.end(), greater<int>{});
        ans = -1;
        coinChange_recursive_helper(coins, 0, amount, 0);
        return ans;
    }
};

int main() {
    int ans;
    vector<int> coins;

    {
        coins = { 1, 5 };
        assert(3 == (ans = Solution().coinChange(coins, 11)));
    }

    {
        coins = { 2147483647 };
        assert(-1 == (ans = Solution().coinChange(coins, 2)));
    }
    {
        // greedy doesn't work
        coins = { 21, 20, 1 };
        assert(5 == (ans = Solution().coinChange(coins, 100)));
    }

    {
        coins = { 176, 6, 366, 357, 484, 226, 1, 104, 160, 331 };
        assert(13 == (ans = Solution().coinChange(coins, 5557)));
    }

    {
        // backtracking TLE
        coins = { 346,29,395,188,155,109 };
        assert(26 == (ans = Solution().coinChange(coins, 9401)));
    }

    {
        // greedy doesn't work
        coins = { 470,35,120,81,121 };
        assert(30 == (ans = Solution().coinChange(coins, 9825)));
    }

    {
        coins = { 1 };
        assert(0 == (ans = Solution().coinChange(coins, 0)));
    }

    {
        // greedy doesn't work
        coins = { 186,419,83,408 };
        assert(20 == (ans = Solution().coinChange(coins, 6249)));
    }
}
