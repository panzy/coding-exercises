/*
719. Find K-th Smallest Pair Distance
https://leetcode.com/problems/find-k-th-smallest-pair-distance/
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

// Are there not less than k pairs that have distance <= limit?
bool check(const vector<int>& nums, int k, int limit) {
    int cnt = 0;
    for (int i = 0; i < nums.size() && cnt < k; ++i) {
        // Find the end of range [i,j] where [j]-[i] <= limit.
        auto pos = upper_bound(nums.begin() + i + 1, nums.end(), limit + nums[i]);
		cnt += pos - (nums.begin() + i + 1);
    }
    return cnt >= k;
}

int smallestDistancePair(vector<int>& nums, int k) {

    sort(nums.begin(), nums.end());

    int lo = 0, hi = nums[nums.size() - 1] - nums[0];
    while (lo < hi) {
        int mi = lo + (hi - lo) / 2;
        if (check(nums, k, mi)) {
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
		vector<int> nums = { 1, 3 };
        int ans = smallestDistancePair(nums, 1);
        _ASSERT(2 == ans);
    }
    {
		vector<int> nums = { 62, 100, 4 };
        int ans = smallestDistancePair(nums, 2);
        _ASSERT(58 == ans);
    }
    {
		vector<int> nums = { 9, 10, 7, 10, 6, 1, 5, 4, 9, 8 };
        int ans = smallestDistancePair(nums, 18);
        _ASSERT(2 == ans);
    }
    {
		vector<int> nums = { 1, 1, 1 };
        int ans = smallestDistancePair(nums, 1);
        _ASSERT(0 == ans);
        ans = smallestDistancePair(nums, 2);
        _ASSERT(0 == ans);
    }
    {
		vector<int> nums = { 1, 3, 5 };
        int ans = smallestDistancePair(nums, 1);
        _ASSERT(2 == ans);
        ans = smallestDistancePair(nums, 2);
        _ASSERT(2 == ans);
    }
    {
		vector<int> nums = { 1, 3, 1 };
        int ans = smallestDistancePair(nums, 1);
        _ASSERT(0 == ans);
    }
    return 0;
}
