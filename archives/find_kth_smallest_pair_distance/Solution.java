package find_kth_smallest_pair_distance;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Time Limit Exceeded.
 *
 * Created by Zhiyong Pan on 2021-01-15.
 */
public class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        TreeSet<Integer> diffs = new TreeSet<>();
        HashMap<Integer, Integer> repeats = new HashMap<>();

        // Collect distances.
        for (int i = 0; i + 1 < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                int d = Math.abs(nums[i] - nums[j]);
                repeats.put(d, repeats.getOrDefault(d, 0) + 1);
                if (repeats.get(d) == 1)
                    diffs.add(d);
            }
        }

        // Poll the TreeSet until the k-th.
        for (int i = 1; i < k; ) {
            int d = diffs.pollFirst();
            i += repeats.get(d);
            if (i > k)
                return d;
        }

        return diffs.pollFirst();
    }
}

