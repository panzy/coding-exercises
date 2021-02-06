package find_the_most_competitive_subsequence_1673;

/**
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class Solution2 {
    public int[] mostCompetitive(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[k];
        int progress = 0;
        int available = n;
        for (int i = 0; i < n; ++i, --available) {
            while (progress > 0 && available > (k - progress) && ans[progress - 1] > nums[i]) {
                --progress;
            }
            if (progress < k) {
                ans[progress] = nums[i];
                ++progress;
            }
        }

        return ans;
    }
}
