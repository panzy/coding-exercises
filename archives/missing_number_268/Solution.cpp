/*
268. Missing Number
https://leetcode.com/problems/missing-number/

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
--
Zhiyong
2021-03-03
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
public:
    int missingNumber(vector<int>& nums) {
        int n = nums.size();
        
        // for each i, swap to let nums[i] == i.
        // it's not possible if nums[i] == n, in that case, just move on with ++i.
        for (int i = 0; i < n; ++i) {
            if (nums[i] == i) continue;            
            
            while (nums[i] != i && nums[i] < n) {
                swap(nums[i], nums[nums[i]]);
            }
        }
        
        // find the mismatch
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i) return i;
        }
        
        // since numbers in [0, n) are all present
        return n;
    }
};
