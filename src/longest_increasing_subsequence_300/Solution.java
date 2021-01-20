package longest_increasing_subsequence_300;

import java.util.Arrays;

/**
 * dp[i] = how many numbers before nums[i] are less than nums[i]?
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // [i] = how many numbers before nums[i] are less than nums[i]?
        int[] cnts = new int[n];
        Arrays.fill(cnts, 0);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    cnts[i] = Math.max(cnts[i], 1 + cnts[j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, cnts[i]);
        }

        return ans + 1;
    }
}
