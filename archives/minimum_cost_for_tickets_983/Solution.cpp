/*
983. Minimum Cost For Tickets
https://leetcode.com/problems/minimum-cost-for-tickets/submissions/
--
Zhiyong
2021-03-12
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

// recursion + memo
class Solution {
    // duration days for each kind to ticket.
    const int passes[3]{ 1, 7, 30 };

    int memo[365 + 1];

    int helper(const vector<int>& days, const vector<int>& costs, int start) {
        if (start >= days.size())
            return 0;

        if (memo[start] != -1)
            return memo[start];

        int ans = -1;
        for (int i = 0; i < 3; ++i) {
            int start2 = start + 1;
            while (start2 < days.size() && days[start2] - days[start] < passes[i])
                ++start2;
            int ans2 = costs[i] + helper(days, costs, start2);
            if (ans == -1 || ans > ans2)
                ans = ans2;
        }

        memo[start] = ans;
        return ans;
    }

public:
    int mincostTickets(const vector<int>& days, const vector<int>& costs) {
        fill(memo, memo + sizeof memo / sizeof(int), -1);
        return helper(days, costs, 0);
    }
};

int main() {
    int ans;
    vector<int> coins;

    assert(423 == (ans = Solution().mincostTickets(
        { 1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,24,25,27,28,29,30,31,34,37,38,39,41,43,44,45,47,48,49,54,57,60,62,63,66,69,70,72,74,76,78,80,81,82,83,84,85,88,89,91,93,94,97,99 },
        { 9, 38, 134 })));
    assert(7 == (ans = Solution().mincostTickets({ 1,2,3,4,5,6,7 }, { 2, 7, 15 })));
    assert(2 == (ans = Solution().mincostTickets({ 1 }, { 2, 7, 15 })));
    assert(17 == (ans = Solution().mincostTickets({ 1,2,3,4,5,6,7,8,9,10,30,31 }, { 2, 7, 15 })));
    assert(11 == (ans = Solution().mincostTickets({ 1, 4, 6, 7, 8, 20 }, { 2, 7, 15 })));
}
