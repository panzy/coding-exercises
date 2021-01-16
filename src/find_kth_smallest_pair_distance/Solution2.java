package find_kth_smallest_pair_distance;

import java.util.Arrays;

/**
 * Rewrite from LeetCode official approach #2.
 *
 * I was amazed by how binary search was applied to this problem.
 *
 * An Excel sheet was created to help myself understand this approach.
 *
 * Created by Zhiyong Pan on 2021-01-15.
 */
public class Solution2 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int min = nums[0];
        int max = nums[nums.length - 1];

        // Let prefix[v] be the number of points in nums less than or equal to v.
        // The maximal v we will need is `max + max_distance = max + (max - min)`.
        int[] prefix = new int[1 + max + (max - min)];
        // `left` is the number that will be assigned to prefix[v].
        for (int i = 0, left = 1; i < n; ++i, ++left) {
            int v = nums[i];
            prefix[v] = left;
            // If prefix[v] should be greater in the end because v has duplicates, it will be overwritten with
            // a increased `left`, so the code above is correct.

            do {
                prefix[v] = left;
            } while (i + 1 < n && ++v < nums[i + 1]);
        }
        Arrays.fill(prefix, max, prefix.length, prefix[max]);

        // Another approach to init the prefix array (copied from LeetCode's official solution page):
//        int[] prefix = new int[WIDTH];
//        int left = 0;
//        for (int i = 0; i < WIDTH; ++i) {
//            while (left < nums.length && nums[left] == i) left++;
//            prefix[i] = left;
//        }

        // Let multiplicity[j] be the number of points i with i < j and nums[i] == nums[j].
        int[] multiplicity = new int[n];
        multiplicity[0] = 0;
        for (int i = 1; i < n; ++i) {
            multiplicity[i] = nums[i] == nums[i - 1] ? multiplicity[i - 1] + 1 : 0;
        }

        // binary search
        int lo = 0; // the minimal distance
        int hi = max - min; // the maximum distance
        while (lo < hi) {
            // We guess `mi` is the answer, i.e.:
            // (1) the count of pairs with a distance less or equal to mi is not less than k; and
            // (2) the count of pairs with a distance less or equal to (mi+1) is greater than k.
            int mi = (hi + lo) / 2;

            int count = 0;
            for (int i = 0; i < n; ++i) {
                count += prefix[nums[i] + mi] - prefix[nums[i]] + multiplicity[i];
            }

            if (count >= k) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }

        return lo;
    }
}
