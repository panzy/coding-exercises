package max_number_of_k_sum_pairs_1679;

import java.util.HashMap;

/**
 * Eliminated the loop on the hash map's key set in the previous solution.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution2 {
    public int maxOperations(int[] nums, int k) {
        // key = number
        // value = occurrence times
        HashMap<Integer, Integer> numCnts = new HashMap<>();

        int ans = 0;

        for (int i : nums) {
            // Since all numbers are positive, those >= k will not be paired.
            if (i < k) {
                int j = k - i; // the paired number
                int cj = numCnts.getOrDefault(j, 0);
                if (cj > 0) {
                    // Found a pair, consume both i and j.
                    ++ans;
                    numCnts.put(j, cj - 1);
                } else {
                    // Didn't find a pair, record i in the map.
                    numCnts.put(i, numCnts.getOrDefault(i, 0) + 1);
                }
            }
        }

        return ans;
    }
}
