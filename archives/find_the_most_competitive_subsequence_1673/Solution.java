package find_the_most_competitive_subsequence_1673;

/**
 * Brute force. Time limit exceeded.
 *
 * The inefficient part was for each of k elements, I had to call indexOfMinimum(from, to).
 *
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class Solution {
    int[] nums;

    public int[] mostCompetitive(int[] nums, int k) {

        this.nums = nums;
        int n = nums.length;

        int[] ans = new int[k];
        int ansIdx = 0;

        // Pick each element of the most competitive subsequence.
        while (ansIdx < k) {
            ans[ansIdx] = indexOfMinimum(
                    ansIdx > 0 ? ans[ansIdx - 1] + 1 : 0, n - (k - (ansIdx + 1)));
            ++ansIdx;
        }

        // map indices in ans to numbers
        for (int i = 0; i < ans.length; ++i)
            ans[i] = nums[ans[i]];
        return ans;
    }

    private int minIndices(int i, int j) {
        return nums[i] > nums[j] ? j : i;
    }

    private int indexOfMinimum(int from, int to) {
        int ans = from;

        for (int i = from + 1; i < to; ++i) {
            ans = minIndices(ans, i);
        }

        return ans;
    }
}
