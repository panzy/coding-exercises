/*
152. Maximum Product Subarray
https://leetcode.com/problems/maximum-product-subarray/
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
    int maxProduct(const vector<int>& nums) {
        const int n = nums.size();
        int ans;
        int prefix;
		int minPositivePrefix;
		int maxNegativePrefix;
        bool reset = true; // indicates the prefix vars are not initialized

        for (int i = 0; i < n; ++i) {
			ans = max(ans, nums[i]);

            if (nums[i] == 0) {
                reset = true;
                continue;
            }

            if (reset) {
				prefix = nums[i];
				minPositivePrefix = nums[i] > 0 ? nums[i] : numeric_limits<int>::max();
				maxNegativePrefix = nums[i] < 0 ? nums[i] : numeric_limits<int>::min();
                reset = false;
                continue;
            }

            prefix *= nums[i];
            ans = max(ans, prefix);
            if (prefix > 0) {
                ans = max(ans, prefix / minPositivePrefix);
                if (minPositivePrefix > prefix)
                    minPositivePrefix = prefix;
            } 
            else if (prefix < 0) {
                ans = max(ans, prefix / maxNegativePrefix);
                if (maxNegativePrefix < prefix)
                    maxNegativePrefix = prefix;
            }
        }

        return ans;
    }
};

int main() {
    {
        auto res = Solution().maxProduct({ 0, 2, 3 });
        _ASSERT(6 == res);
    }
    {
        auto res = Solution().maxProduct({ 0, 2 });
        _ASSERT(2 == res);
    }
    {
        auto res = Solution().maxProduct({ -2, 0, -1 });
        _ASSERT(0 == res);
    }
    {
        auto res = Solution().maxProduct({ -2, 0, 1 });
        _ASSERT(1 == res);
    }
    {
        auto res = Solution().maxProduct({ -2, 2, -3, -5 });
        _ASSERT(30 == res);
    }
    {
        auto res = Solution().maxProduct({ -2, 2, 3, 5 });
        _ASSERT(30 == res);
    }
    {
        auto res = Solution().maxProduct({ 2, 3, -2, 4 });
        _ASSERT(6 == res);
    }
    {
        auto res = Solution().maxProduct({ 2, 3, -2, 4, 5 });
        _ASSERT(20 == res);
    }
    return 0;
}
