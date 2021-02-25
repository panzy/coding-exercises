/*
581. Shortest Unsorted Continuous Subarray
https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
--
Zhiyong
2021-02-25
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
public:
    int findUnsortedSubarray(vector<int>& nums) {
        int n = nums.size();
        int begin = -1, end = -1; // the subarray

        // find the first disordered pair
        for (int i = 0; i + 1 < n; ++i) {
            if (nums[i] > nums[i + 1]) {
                begin = i;
                break;
            }
        }

        if (begin != -1) {
            // find the last disordered pair
            for (int i = n - 2; i >= end - 1; --i) {
                if (nums[i] > nums[i + 1]) {
                    end = i + 2;
                    break;
                }
            }

            // extend the subarray to cover the min/max values.
            auto [a, b] = minmax_element(nums.begin() + begin, nums.begin() + end);
            begin = upper_bound(nums.begin(), nums.begin() + begin, *a) - nums.begin();
            end = lower_bound(nums.begin() + end, nums.end(), *b) - nums.begin();
        }

        return end - begin;
    }
};

int main() {
    {
        vector<int> nums = { 1,1,3,1,3,3 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 1,3,2,3,3 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 3,5,2,6,9,1,7,8 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(8 == ans);
    }
    {
        vector<int> nums = { 3,5,2,6,9,1,7,8,10 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(8 == ans);
    }
    {
        vector<int> nums = { 3,2,5 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 2,5,3 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 2,3,5,4 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 3,2,4,5 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    {
        vector<int> nums = { 5,4,3,2 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(4 == ans);
    }
    {
        vector<int> nums = { 1,2,3,4,5,6,7 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(0 == ans);
    }
    {
        vector<int> nums = { 5,8,9,1,6,13,18 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(5 == ans);
    }
    {
        vector<int> nums = { 2,6,4,8,10,9,15 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(5 == ans);
    }
    {
        vector<int> nums = { 1,2,3,4 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(0 == ans);
    }
    {
        vector<int> nums = { 1 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(0 == ans);
    }
    {
        vector<int> nums = { 1,2 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(0 == ans);
    }
    {
        vector<int> nums = { 2,1 };
        int ans = Solution().findUnsortedSubarray(nums);
        _ASSERT(2 == ans);
    }
    return 0;
}
