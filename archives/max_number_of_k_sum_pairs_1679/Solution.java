package max_number_of_k_sum_pairs_1679;

import java.util.HashMap;

/**
 * Use hash map to find out the presence of the paired number for each number.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution {
    public int maxOperations(int[] nums, int k) {
        // key = number
        // value = occurrence times
        HashMap<Integer, Integer> numCnts = new HashMap<>();

        for (int i : nums) {
            // Since all numbers are positive, those >= k will not be paired.
            if (i < k)
                numCnts.put(i, numCnts.getOrDefault(i, 0) + 1);
        }

        int ans = 0;
        for (int i : numCnts.keySet()) {
            int ci = numCnts.getOrDefault(i, 0);
            if (ci == 0)
                continue;

            int j = k - i; // the paired number
            if (i == j) { // pair with itself
                if (ci >= 2) {
                    int pairs = ci / 2;
                    ans += pairs;
                    numCnts.put(i, ci % 2);
                }
            } else {
                int cj = numCnts.getOrDefault(j, 0);
                if (cj > 0) {
                    int pairs = Math.min(ci, cj);
                    ans += pairs;
                    numCnts.put(i, ci - pairs);
                    numCnts.put(j, cj - pairs);
                }
            }
        }

        return ans;
    }
}
