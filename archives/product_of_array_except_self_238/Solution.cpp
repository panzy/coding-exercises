/*
238. Product of Array Except Self
https://leetcode.com/problems/product-of-array-except-self/
--
Zhiyong, 2021-02-19
*/
#include <iostream>
#include <string>
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
#include <iterator>
using namespace std;

typedef long long LL;
typedef unsigned long long ULL;

class Solution {
public:
    vector<int> productExceptSelf(const vector<int>& nums) {
        const int n = nums.size();
        vector<int> res;
        res.resize(n);
        fill(res.begin(), res.end(), 1);

        // multiply numbers to the left
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        // multiply numbers to the right
        int prod = 1;
        for (int i = n - 2; i >= 0; --i) {
            prod *= nums[i + 1];
            res[i] *= prod;
        }

        return res;
    }
};

int main() {
    {
        auto res = Solution().productExceptSelf({ 1, 2, 3, 4 });
        _ASSERT(24 == res[0]);
        _ASSERT(12 == res[1]);
        _ASSERT(8 == res[2]);
        _ASSERT(6 == res[3]);
    }
    return 0;
}
