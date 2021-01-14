package minimum_operations_to_reduce_x_to_zero;

import java.util.HashMap;

/**
 * I knew this won't work. The N is too large for a recursion approach (stack overflow).
 *
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class Solution0 {
    HashMap<String, Integer> memo = new HashMap();
    int bestAnswer = Integer.MAX_VALUE; // best answer so far

    public int minOperations(int[] nums, int x) {
        return minOperations(nums, x, 0, nums.length - 1);
    }

    private int minOperations(int[] nums, int x, int first, int last) {
        if (x == 0)
            return 0;
        if (x < 0)
            return -1;

        String key = String.format("%d-%d-%d", x, first, last);
        if (memo.containsKey(key))
            return memo.get(key);

        if (first + (nums.length - 1 - last) >= bestAnswer)
            return -1;

        int a = first <= last ? minOperations(nums, x - nums[first], first + 1, last) : -1;
        int b = last > first ? minOperations(nums, x - nums[last], first, last - 1) : -1;
        int ans;

        if (a < 0 && b < 0)
            ans = -1;
        else if (a < 0)
            ans = 1 + b;
        else if (b < 0)
            ans = 1 + a;
        else
            ans = 1 + Math.min(a, b);

        memo.put(key, ans);
        bestAnswer = Math.min(bestAnswer, ans);

        return ans;
    }
}
