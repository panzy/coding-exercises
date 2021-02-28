/*
1774. Closest Dessert Cost
https://leetcode.com/contest/weekly-contest-230/problems/closest-dessert-cost/
--
Zhiyong
2021-02-27
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
#include <algorithm>
#include <functional>
using namespace std;

class Solution {
    int ans = 0;
    int target;

    int better(int ans, int b, int target) {
        if (ans == 0 || abs(ans - target) > abs(b - target) || (b < ans && b == 2 * target - ans)) {
            return b;
        }
        return ans;
    }

    void pickToppings(const vector<int>& toppingCosts, int i, int total) {
        if (i == toppingCosts.size()) {
            if (ans == 0)
                ans = total;
            else
                ans = better(ans, total, target);
            return;
        }
        pickToppings(toppingCosts, i + 1, total);
        pickToppings(toppingCosts, i + 1, total + toppingCosts[i]);
        pickToppings(toppingCosts, i + 1, total + toppingCosts[i] * 2);
    }
public:
    // Bruteforce
    //
    // It's possible because both m and n <= 10.
    //
    // The remaining question is, how do you enumerate the 3^m combination?
    //
    // My choice during the contest is recursion.
    //
    // Then I realized it's just increasing a number from 0 to 3^m-1, and if you view the number in 3-radix,
    // it's easy to see for a combination |p|, the count of the last kind of topping is p%3,
    // the last 2nd is (p/3)%3, and so on.
    int closestCost(const vector<int>& baseCosts, const vector<int>& toppingCosts, int target) {
        this->target = target;
        ans = 0;

        for (auto&& b : baseCosts) {
            
            ans = better(ans, b, target);
            
            if (b >= target) continue;

            pickToppings(toppingCosts, 0, b);
        }
        return ans;
    }
};

int main() {
    _ASSERT(17 == Solution().closestCost({ 2,3 }, { 4,5,100 }, 18));
    _ASSERT(8 == Solution().closestCost({ 3,10 }, { 2,5 }, 9));
    _ASSERT(10 == Solution().closestCost({ 10 }, { 1 }, 1));
    return 0;
}
