/*
287. Find the Duplicate Number
https://leetcode.com/problems/find-the-duplicate-number/solution/

Sort + binary search
*/
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        
        int lo = 0, hi = nums.size();
        
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (lo == mi) {
                return nums[mi];
            } else if (nums[lo] + mi - lo > nums[mi]) {
                hi = mi;
            } else {
                lo = mi;
            }
        }
        
        return nums[lo];
    }
};