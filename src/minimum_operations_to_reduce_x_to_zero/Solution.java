package minimum_operations_to_reduce_x_to_zero;

/**
 * Learned a thing from problem 927. Three Equal Parts, which I did two days ago.
 *
 * Assume [0, i) and [j, n) are the two ranges holding minimum operations, what
 * properties do i and j have?
 *
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; ++i)
            sum += nums[i];

        if (sum < x)
            return -1;
        else if (sum == x)
            return n;

        // Search all range [i, j) that has a desired sum.
        int desiredSum = sum - x;
        int rangeSum = nums[0];
        int i = 0, j = 1;
        int ans = n;

        while (true) {
            if (rangeSum < desiredSum) {
                if (j == n)
                    break;
                rangeSum += nums[j++];
            } else if (rangeSum > desiredSum) {
                rangeSum -= nums[i++];
                // It's ok if i reaches j, in that case the range collapses and
                // will try to expand in next iteration.
            } else if (rangeSum == desiredSum) {
                ans = Math.min(ans, n - (j - i));
                rangeSum -= nums[i++];
            }
        }

        return ans < n ? ans : -1;
    }
}
