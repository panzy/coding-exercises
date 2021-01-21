package number_of_longest_increasing_subsequence_673;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        // lens[i] = the length of the LISs ending at (including) number [i].
        int[] lens = new int[n];

        // ways[i] = the number of the LISs ending at (including) number [i].
        int[] ways = new int[n];

        // Init to single-element LISs.
        Arrays.fill(lens, 1);
        Arrays.fill(ways, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    lens[i] = Math.max(lens[i], 1 + lens[j]);
                }
            }

            // ways[i] = sum(ways[j])
            if (lens[i] > 1) {
                ways[i] = 0;
                for (int j = 0; j < i; ++j) {
                    if (nums[j] < nums[i] && lens[j] + 1 == lens[i]) {
                        ways[i] += ways[j];
                    }
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; ++i) {
            maxLen = Math.max(maxLen, lens[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (maxLen == lens[i])
                ans += ways[i];
        }

        return ans;
    }

    @Test
    void example1() {
        Assertions.assertEquals(2, findNumberOfLIS(new int[]{1, 3, 5, 4, 7}));
    }

    @Test
    void example2() {
        Assertions.assertEquals(5, findNumberOfLIS(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    void example3() {
        // 4,8,9
        // 4,8,9
        // 3,8,9
        Assertions.assertEquals(3, findNumberOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
    }

    @Test
    void example4() {
        // 5,9,10
        // 5,8,10
        // 5,8,9
        // 4,8,10
        // 4,8,9
        // 3,8,10
        // 3,8,9
        Assertions.assertEquals(7, findNumberOfLIS(new int[]{5, 9, 4, 3, 8, 10, 9}));
    }
}
