/*
413. Arithmetic Slices
https://leetcode.com/problems/arithmetic-slices/
--
Zhiyong, 2021-02-18
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
using namespace std;

class Solution {
public:
    int numberOfArithmeticSlices(const vector<int>& A) {
        int n = A.size();
        int ans = 0;
        for (int p = 0, q = 2; q < n; ++q) {
            if (p + 1 < q && A[p] - A[p + 1] == A[q - 1] - A[q]) {
                ans += q - p - 1;
            }
            else {
                p = q - 1;
            }
        }
        return ans;
    }
};

int main() {
    {
		vector<int> nums = { 1,2,3,4 };
        int res = Solution().numberOfArithmeticSlices(nums);
        _ASSERT(3 == res);
    }
    {
		vector<int> nums = { 1,2,3,4,5 };
        int res = Solution().numberOfArithmeticSlices(nums);
        _ASSERT(6 == res);
    }
    {
		vector<int> nums = { 1,2,3,4,6,8 };
        int res = Solution().numberOfArithmeticSlices(nums);
        _ASSERT(4 == res);
    }
    return 0;
}
