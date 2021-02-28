/*
1775. Equal Sum Arrays With Minimum Number of Operations
https://leetcode.com/contest/weekly-contest-230/problems/equal-sum-arrays-with-minimum-number-of-operations/
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
    int helper(const vector<int>& nums1, const vector<int>& nums2, int delta) {
        priority_queue<int, vector<int>, greater<int>> heap1;
        priority_queue<int> heap2;

        for (int i : nums1) {
            heap1.push(i);
        }

        for (int i : nums2) {
            heap2.push(i);
        }

        int ans = 0;
        while (delta > 0) {
            int d1 = 0, d2 = 0;
            if (heap1.size()) {
                d1 = 6 - heap1.top();
            }
            if (heap2.size()) {
                d2 = heap2.top() - 1;
            }

            if (d1 == 0 && d2 == 0)
                return -1;

            if (d1 >= d2) {
                delta -= d1;
                heap1.pop();
            }
            else if (d2 > 0) {
                delta -= d2;
                heap2.pop();
            }

            ++ans;
        }

        return ans;
    }
public:
    int minOperations(const vector<int>& nums1, const vector<int>& nums2) {
        int sum1 = reduce(nums1.begin(), nums1.end());
        int sum2 = reduce(nums2.begin(), nums2.end());

        if (sum1 == sum2)
            return 0;

        if (sum1 < sum2) {
            return helper(nums1, nums2, sum2 - sum1);
        }
        else {
            return helper(nums2, nums1, sum1 - sum2);
        }
    }
};


int main() {
    _ASSERT(3 == Solution().minOperations({ 1,2,3,4,5,6 }, { 1,1,2,2,2,2 }));
    _ASSERT(-1 == Solution().minOperations({ 1,1,1,1,1,1,1 }, { 6 }));
    _ASSERT(3 == Solution().minOperations({ 6, 6 }, { 1 }));
    _ASSERT(0 == Solution().minOperations({ 1 }, { 1 }));
    _ASSERT(1 == Solution().minOperations({ 1 }, { 6 }));
    return 0;
}
