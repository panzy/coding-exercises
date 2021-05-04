/* 665. Non-decreasing Array
 * https://leetcode.com/problems/non-decreasing-array/
 * --
 * Zhiyong 2021-05-04
 */
class Solution {
public:
    bool checkPossibility(vector<int>& nums) {
        bool modified = false;
        for (size_t i = 0, n = nums.size(); i + 1 < n; ++i) {
            if (nums[i] > nums[i + 1]) {
                if (modified)
                    return false;
                // modifying strategy:
                // modify [i] to the least possible value,
                // if that's not possible, modify [i+1] to the least possible value.
                if (i == 0) {
                    nums[i] = -1e5;
                } else if (nums[i - 1] <= nums[i + 1]) {
                    nums[i] = nums[i - 1];
                } else {
                    nums[i + 1] = nums[i];
                }
                modified = true;
            }
        }
        
        return true;
    }
};
