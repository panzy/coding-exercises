package number_of_subsequences_that_satisfy_the_given_sum_condition_1498;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class Solution {
    static int mod = (int) (1e9 + 7);

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> prefix = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);

            if (!prefix.containsKey(nums[i])) {
                int p = 0;
                for (int j = 0; j < n; ++j) {
                    if (nums[i] > nums[j]) {
                        ++p;
                    }
                }
                prefix.put(nums[i], p);
            }
        }

        // Number of invalid subsequences.
        long invalidCnt = 0;

        // Record whether a pair of minimum and maximum (no matter where they are)
        // have been counted.
        HashSet<Integer> usedPairs = new HashSet<>();

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (nums[i] + nums[j] > target) {
                    // Got a pair of minimum and maximum.

                    int a = Math.min(nums[i], nums[j]);
                    int b = Math.max(nums[i], nums[j]);

                    // Avoid repeated pairs.
                    int key = (a << 16) + b;
                    if (usedPairs.contains(key))
                        continue;
                    usedPairs.add(key);

                    if (i != j) {
                        if (a == b) {
                            invalidCnt = (long) ((invalidCnt + Math.pow(2, freq.get(a)) - 1) % mod);
                        } else {
                            // How many elements are greater than a and less than b?
                            int optionalElementCount = prefix.get(b) - prefix.getOrDefault(a, 0) - freq.get(a);

                            invalidCnt = (long) ((invalidCnt +
                                    (Math.pow(2, freq.get(a)) - 1) * // the minimum appears at least once
                                            (Math.pow(2, freq.get(b)) - 1) * // the maximum appears at least once
                                            Math.pow(2, optionalElementCount) // other numbers are free to appear or not
                            ) % mod);
                        }
                    } else {
                        // At least one element of this value appears.
                        invalidCnt = (long) ((invalidCnt + Math.pow(2, freq.get(a)) - 1) % mod);
                    }
                }
            }
        }

        return (int) ((Math.pow(2, n) - 1 - invalidCnt) % mod);
    }

    @Test
    void test() {
        Assertions.assertEquals(56, numSubseq(new int[]{7, 10, 7, 3, 7, 5, 4}, 12));
        Assertions.assertEquals(2, numSubseq(new int[]{5, 1}, 8));
        Assertions.assertEquals(0, numSubseq(new int[]{5, 5}, 8));
        Assertions.assertEquals(0, numSubseq(new int[]{5, 5, 5}, 8));
        Assertions.assertEquals(6, numSubseq(new int[]{3, 3, 6, 8}, 10));
        Assertions.assertEquals(4, numSubseq(new int[]{3, 5, 6, 7}, 9));
        Assertions.assertEquals(61, numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12));
        Assertions.assertEquals(127, numSubseq(new int[]{5, 2, 4, 1, 7, 6, 8}, 16));
        Assertions.assertEquals(963594139, numSubseq(new int[]{
                6, 10, 12, 3, 29, 21, 12, 25, 17, 19, 16, 1, 2, 24, 9, 17, 25, 22, 12, 22, 26, 24, 24, 11, 3,
                7, 24, 5, 15, 30, 23, 5, 20, 10, 19, 20, 9, 27, 11, 4, 23, 4, 4, 12, 22, 27, 16, 11, 26, 10, 23,
                26, 16, 21, 24, 21, 17, 13, 21, 9, 16, 17, 27},
                26));
    }
}
