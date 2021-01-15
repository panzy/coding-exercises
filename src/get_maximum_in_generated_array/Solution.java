package get_maximum_in_generated_array;

/**
 * Created by Zhiyong Pan on 2021-01-15.
 */
public class Solution {
    public int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        int ans = 1;

        for (int i = 1; ; ++i) {
            if (2 * i <= n) {
                nums[2 * i] = nums[i];
                // It's less than nums[2i-1] for sure.
            } else {
                break;
            }
            if (2 * i + 1 <= n) {
                nums[2 * i + 1] = nums[i] + nums[i + 1];
                // It may or may not be greater than nums[2j+1].
                ans = Math.max(ans, nums[2 * i + 1]);
            } else {
                break;
            }
        }

        return ans;
    }
}
