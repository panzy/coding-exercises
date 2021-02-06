package number_of_subsequences_that_satisfy_the_given_sum_condition_1498;

import java.util.Arrays;

/**
 * Optimized based on the previous solution.
 *
 * Barely accepted.
 *
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class Solution3 {
    final static int mod = (int) (1e9 + 7);

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;

        Arrays.sort(nums);

        // Number of valid subsequences.
        int ans = 0;

        for (int i = 0, j = n - 1; i < n; ++i) {

            if ((nums[i] << 1) > target)
                break;

            // Find the largest range [i,j] that i+j<=target.
            while (nums[i] + nums[j] > target)
                --j;

            // nums[i] must be present, all the rest are optional.
            ans = (ans + bigPow(2, j - i)) % mod;
        }

        return ans;
    }

    // https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/discuss/1005857/C%2B%2B-short-solution
    static int bigPow(int v, int p) {
        if(p == 0) return 1;
        if(p == 1) return v;
        int h = bigPow(v, p>>1), f = (int)(((long)h * h) % mod);
        return ((p&1) == 1) ? (f * v) % mod : f;
    }
}
