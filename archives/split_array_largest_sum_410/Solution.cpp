/* 410. Split Array Largest Sum
https://leetcode.com/problems/split-array-largest-sum/
--
Zhiyong, 2021-02-16
*/
#include <cmath>
#include <cstdio>
#include <vector>
#include <set>
#include <queue>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <numeric>
#include <functional>
using namespace std;

bool check(const vector<int>& nums, int m, int limit) {
    bool removed[1000] = {};
    int sum = 0;

    for (int i = 0; i < nums.size() && m >= 0 && sum <= limit; ++i) {
		if (sum + nums[i] <= limit) {
			sum += nums[i];
		}
		else {
			sum = nums[i];
			--m;
		}
    }

    if (sum > 0)
        --m;

    return m >= 0 && sum <= limit;
}

int splitArray(vector<int>& nums, int m) {

    int sum = reduce(nums.begin(), nums.end());

    int lo = 0, hi = sum;
    while (lo < hi) {
        int mi = lo + (hi - lo) / 2;
        if (check(nums, m, mi)) {
            hi = mi;
        }
        else {
            lo = mi + 1;
        }
    }
    return hi;
}

int main() {
    {
		vector<int> nums = { 2,16,14,15 };
        int ans = splitArray(nums, 2);
        _ASSERT(29 == ans);
    }
    {
		vector<int> nums = { 1,2,3,4,5 };
        int ans = splitArray(nums, 2);
        _ASSERT(9 == ans);
    }
    {
        vector<int> nums = { 7,2,5,10,8 };
        int ans = splitArray(nums, 2);
        _ASSERT(18 == ans);
    }
    return 0;
}
