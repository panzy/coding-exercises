/* 1814. Count Nice Pairs in an Array
 * https://leetcode.com/problems/count-nice-pairs-in-an-array/
 *
 * Observation:
 *
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * =>
 * nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
 * => care only a number's difference with its reverse.
 *
 * --
 * Zhiyong 2021-04-04
 */

using ULL = unsigned long long;
const ULL MOD = 1e9 + 7;

class Solution {
    int rev(int n) {
        ULL r = 0;
        while (n > 0) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        return r;
    }
public:
    int countNicePairs(vector<int>& nums) {
        unordered_map<int, ULL> diffs;
        
        for (int i : nums) {
            ++diffs[rev(i) - i];
        }
        
        ULL ans = 0;
        for (auto&& p : diffs) {
            if (p.second > 1) {
                ans += p.second * (p.second - 1) / 2;
            }
        }
        
        return ans % MOD;
    }
};
