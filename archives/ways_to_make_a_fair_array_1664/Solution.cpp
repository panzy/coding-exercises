/*
1664. Ways to Make a Fair Array
https://leetcode.com/problems/ways-to-make-a-fair-array/

You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. Notice that the index of the elements may change after the removal.

For example, if nums = [6,1,7,4,1]:

Choosing to remove index 1 results in nums = [6,7,4,1].
Choosing to remove index 2 results in nums = [6,1,4,1].
Choosing to remove index 4 results in nums = [6,1,7,4].
An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.

Return the number of indices that you could choose such that after the removal, nums is fair.



Example 1:

Input: nums = [2,1,6,4]
Output: 1
Explanation:
Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
There is 1 index that you can remove to make nums fair.
Example 2:

Input: nums = [1,1,1]
Output: 3
Explanation: You can remove any index and the remaining array is fair.
Example 3:

Input: nums = [1,2,3]
Output: 0
Explanation: You cannot make a fair array after removing any index.


Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104

--
Zhiyong
2021-03-19
*/
class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        int n = nums.size();
        // sum[i] = { sum of even-indexed values BEFORE [i], ... odd-indexed ... }
        vector<pair<int, int>> sum(1 + n, {0, 0});
        int ans = 0;
        
        // example: [2, 1, 6, 4]
        for (int i = 0; i < n; ++i) {
            if (i % 2 == 0) {
                // sum[1].first = 0 + 2 = 2
                // sum[1].second = 0
                sum[i + 1].first = sum[i].first + nums[i];
                sum[i + 1].second = sum[i].second;
            } else {
                // sum[2].first = 2
                // sum[2].second = 0 + 1 = 1
                sum[i + 1].first = sum[i].first;
                sum[i + 1].second = sum[i].second + nums[i];
            }
            
            //cout << "sum " << sum[i + 1].first << "," << sum[i + 1].second << endl;
        }
        
        for (int i = 0; i < n; ++i) {
            // what if remove i
            // sum = {{0, 0}, {2, 0}, {2, 1}, {8, 1}, {8, 5}}
            // when i == 1:
            // sumEven = 2 + 5 - 1 = 6
            // sumOdd = 0 + 8 - 2 = 6
            int sumEven = sum[i].first + sum[n].second - sum[i + 1].second;
            int sumOdd = sum[i].second + sum[n].first - sum[i + 1].first;
            //cout << "remove " << i << " sumEven=" << sumEven << ", sumOdd=" << sumOdd << endl;
            if (sumEven == sumOdd) {
                ++ans;
            }
        }
        
        return ans;
    }
};
