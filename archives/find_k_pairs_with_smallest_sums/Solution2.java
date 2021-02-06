package find_k_pairs_with_smallest_sums;

import java.util.*;

/**
 * The pairs form a graph.
 *
 * We search from the node of pair (0,0), it's the 1st smallest pair.
 *
 * Everytime we get a m-th smallest pair (i,j), the (m+1)-th is among (i+1,j) and (i,j+1).
 *
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class Solution2 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0)
            return ans;

        // Element = pair indices = (index of nums1, index of nums2).
        PriorityQueue<int[]> heap = new PriorityQueue<>((p1, p2) ->
                nums1[p1[0]] + nums2[p1[1]] - nums1[p2[0]] - nums2[p2[1]]);

        // Record visited pair indices.
        // Key = (i << 16) + j, where i is the index of nums1 and j is the index of nums2.
        HashSet<Integer> visited = new HashSet<>();

        // Since both array are sorted, sum of pair (0,0) is the global smallest.
        heap.add(new int[]{0, 0});
        // No need to add (0,0) to visited set because the pari indices are always increasing.

        // Poll the next smallest pair until we've got k of them or the pairs have been exhausted.
        while (k > 0 && !heap.isEmpty()) {
            int[] indices = heap.poll();
            int i = indices[0], j = indices[1];

            // (i,j) is the smallest pair.
            ans.add(Arrays.asList(nums1[i], nums2[j]));

            // Next candidates are (i+1,j) and (i,j+1).
            if (i + 1 < nums1.length && !visited.contains(((i + 1) << 16) + j)) {
                heap.add(new int[]{i + 1, j});
                visited.add(((i + 1) << 16) + j);
            }
            if (j + 1 < nums2.length && !visited.contains((i << 16) + j + 1)) {
                heap.add(new int[]{i, j + 1});
                visited.add((i << 16) + j + 1);
            }
            --k;
        }

        return ans;
    }
}
