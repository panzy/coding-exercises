/* 523. Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 * --
 * Zhiyong 2021-04-09
 */

/* Observation:
 *
 * If [i:j] is the subarray, then
 *     sum([0:j]) - sum([0:i]) == n * k
 * which means
 *     sum([0:j]) % k == sum([0:i]) % k
 */
class Solution {
public:
    bool checkSubarraySum(vector<int>& nums, int k) {
        // Group prefix sums by their mod.
        // key = sum[0:i] % k
        // val = the smallest i
        unordered_map<int, int> sums;
        
        int sum = 0;
        for (int i = 0; i < nums.size(); ++i) {
            sum += nums[i];
            int m = sum % k;
            
            if (i > 0 && m == 0)
                return true;
            
            if (sums.count(m)) {
                if (sums[m] + 1 < i)
                    return true;
            } else {
                sums[m] = i;
            }
        }
        
        return false;
    }
};
