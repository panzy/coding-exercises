package minimum_operations_to_reduce_x_to_zero;

/**
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class Solution {
    public int minOperations(int[] nums, int x) {
        return minOperations(nums, x, 0, nums.length - 1);
    }

    private int minOperations(int[] nums, int x, int first, int last) {
        if (x == 0)
            return 0;
        if (x < 0)
            return -1;

        int a = first < nums.length ? minOperations(nums, x - nums[first], first + 1, last) : -1;
        int b = last >= 0 ? minOperations(nums, x - nums[last], first, last - 1) : -1;

        if (a < 0 && b < 0)
            return -1;
        else if (a < 0)
            return 1 + b;
        else if (b < 0)
            return 1 + a;
        else
            return 1 + Math.min(a, b);
    }
}
