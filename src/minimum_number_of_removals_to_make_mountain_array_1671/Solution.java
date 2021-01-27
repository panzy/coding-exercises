package minimum_number_of_removals_to_make_mountain_array_1671;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * A mountain consists of an increasing and a decreasing subsequence.
 *
 * The minimum removals to make an array range into an increasing or a decreasing subsequence
 * can be obtained by subtracting the length of LIS/LDS from the array range length.
 *
 * Building a table of LIS/LDS requires O(N^2) time.
 *
 * Created by Zhiyong Pan on 2021-01-27.
 */
public class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        // LIS[i] = length of longest increasing subsequence in range [0, i].
        int[] LIS = new int[n];
        // LDS[i] = length of longest decreasing subsequence in range [i, n).
        int[] LDS = new int[n];

        LIS[0] = 1;
        for (int i = 1; i < n; ++i) {
            int max = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i] && max < LIS[j] + 1) {
                    max = LIS[j] + 1;
                }
            }
            LIS[i] = max;
        }

        LDS[n - 1] = 1;
        for (int i = n - 2; i >= 0; --i) {
            int max = 1;
            for (int j = i + 1; j < n; ++j) {
                if (nums[j] < nums[i] && max < LDS[j] + 1) {
                    max = LDS[j] + 1;
                }
            }
            LDS[i] = max;
        }

        int ans = n;

        for (int i = 1; i + 1 < n; ++i) {
            // If [i] is the mountain peak, what's the cost?

            int costLeft = (i + 1) - LIS[i];
            if (costLeft == i) // you can't remove the whole left part!
                continue;

            int costRight = (n - i) - LDS[i];
            if (costRight == n - i - 1) // you can't remove the whole right part!
                continue;

            ans = Math.min(ans, costLeft + costRight);
        }

        return ans;
    }

    @Test
    void testRemoval() {
        Assertions.assertEquals(0, minimumMountainRemovals(new int[]{1, 3, 1}));
        Assertions.assertEquals(3, minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
        Assertions.assertEquals(4, minimumMountainRemovals(new int[]{4, 3, 2, 1, 1, 2, 3, 1}));
        Assertions.assertEquals(1, minimumMountainRemovals(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
        Assertions.assertEquals(6, minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));
    }

    @Test
    void testRemovalLargeN() throws IOException, ParseException {
        Assertions.assertEquals(351, minimumMountainRemovals(IntArrays.loadFromJsonFile(
                "./src/minimum_number_of_removals_to_make_mountain_array_1671/test-case-66.json"
        )));
    }
}
