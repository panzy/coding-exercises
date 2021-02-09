package max_array_sum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Max Array Sum
 * https://www.hackerrank.com/challenges/max-array-sum/problem
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    static int maxSubsetSum(int[] arr) {
        int n = arr.length;
        int[][] sums = new int[n + 1][];
        sums[0] = new int[]{0, 0};

        for (int i = 0; i < n; ++i) {
            int[] options = {0, 0};
            options[0] = Math.max(sums[i][0], sums[i][1]);
            options[1] = sums[i][0] + arr[i];
            sums[i + 1] = options;
        }

        return Math.max(sums[n][0], sums[n][1]);
    }

    @Test
    void examples() {
        Assertions.assertEquals(8, maxSubsetSum(new int[]{-2, 1, 3, -4, 5}));
        Assertions.assertEquals(0, maxSubsetSum(new int[]{-2, -3, -1}));
        Assertions.assertEquals(13, maxSubsetSum(new int[]{3, 7, 4, 6, 5}));
        Assertions.assertEquals(11, maxSubsetSum(new int[]{2, 1, 5, 8, 4}));
        Assertions.assertEquals(15, maxSubsetSum(new int[]{3, 5, -7, 8, 10}));
    }
}
