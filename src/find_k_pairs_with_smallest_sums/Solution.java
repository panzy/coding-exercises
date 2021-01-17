package find_k_pairs_with_smallest_sums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * k x k.
 *
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>((p1, p2) ->
                p1.get(0) + p1.get(1) - p2.get(0) - p2.get(1));

        for (int i = 0, n = Math.min(nums1.length, k); i < n; ++i) {
            for (int j = 0, m = Math.min(nums2.length, k); j < m; ++j) {
                heap.add(Arrays.asList(nums1[i], nums2[j]));
            }
        }

        for (int i = 0; i < k && !heap.isEmpty(); ++i) {
            ans.add(heap.poll());
        }

        return ans;
    }
}
