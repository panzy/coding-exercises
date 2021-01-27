package minimum_number_of_removals_to_make_mountain_array_1671;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-27.
 */
public class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 1; i + 1 < n; ++i) {
            int costLeft = (i + 1) - lengthOfLIS(nums, 0, i + 1, false);
            if (costLeft == i) // you can't remove the whole left part!
                continue;
            int costRight = (n - i) - lengthOfLIS(nums, i, n, true);
            if (costRight == n - i - 1) // you can't remove the whole right part!
                continue;
            ans = Math.min(ans, costLeft + costRight);
        }

        return ans;
    }

    int lengthOfLIS(int[] nums, int from, int to, boolean descent) {
        int n = to - from;

        // [i] = how many numbers before nums[i] are less than nums[i]?
        int[] cnts = new int[n];
        Arrays.fill(cnts, 0);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if ((descent && nums[from + j] > nums[from + i]) ||
                        (!descent && nums[from + j] < nums[from + i])) {
                    cnts[i] = Math.max(cnts[i], 1 + cnts[j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, cnts[i]);
        }

        return ans + 1;
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
