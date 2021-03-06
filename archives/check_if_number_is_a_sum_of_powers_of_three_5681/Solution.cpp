/*
5681. Check if Number is a Sum of Powers of Three
https://leetcode.com/problems/missing-number/
--
Zhiyong
2021-03-06
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
using namespace std;


class Solution {
    unordered_map<int64_t, bool> memo;
private:
    // bits = used exponents.
    bool checkPowersOfThree(int n, int bits) {
        int64_t key = ((int64_t)n << 32) + bits;
        if (memo.count(key))
            return memo[key];

        bool found = false;
        for (int i = 0; i < 16; ++i) {
            if (((1 << i) & bits) == 0) {
                int p = pow(3, i);
                if (p == n || (p < n && checkPowersOfThree(n - p, bits | (1 << i)))) {
                    found = true;
                    break;
                }
            }
        }

        memo[key] = found;
        return found;
    }
public:
    // A dumb solution: recursion + memo
    bool checkPowersOfThree(int n) {
        return checkPowersOfThree(n, 0);
    }

    // A better solution
    bool checkPowersOfThree2(int n) {
        for(; n; n /= 3) if(n % 3 == 2) return false;
        return true;
    }
};

int main() {
    int ans;
    _ASSERT(Solution().checkPowersOfThree(12));
    _ASSERT(Solution().checkPowersOfThree(91));
    _ASSERT(!Solution().checkPowersOfThree(21));
    _ASSERT(Solution().checkPowersOfThree(27));
    _ASSERT(!Solution().checkPowersOfThree(1e7));
    _ASSERT(Solution().checkPowersOfThree(pow(3, 14) + pow(3, 0) + pow(3,6)));
    _ASSERT(Solution().checkPowersOfThree(pow(3, 10) + pow(3, 0) + pow(3,6) + pow(3, 9)));
    _ASSERT(!Solution().checkPowersOfThree(pow(3, 10) + pow(3, 0) + pow(3,6) + pow(3, 10)));
}
