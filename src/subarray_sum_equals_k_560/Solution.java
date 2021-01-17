package subarray_sum_equals_k_560;

import java.util.HashMap;

/**
 * Pre sum.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;

        // key = a pre sum
        // value = the count of indices having that pre sum.
        HashMap<Integer, Integer> sumIndexCnt = new HashMap<>();

        // A pre sum includes the elements at the indices.
        // So, index -1 has a pre sum of 0.
        sumIndexCnt.put(0, 1);

        for (int i = 0, sum = 0; i < nums.length; ++i) {
            sum += nums[i];
            // How many indices before i have the desired pre sum?
            // Each of them defines a valid sub array with i.
            ans += sumIndexCnt.getOrDefault(sum - k, 0);
            sumIndexCnt.put(sum, sumIndexCnt.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}
