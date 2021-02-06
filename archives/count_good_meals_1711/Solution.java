package count_good_meals_1711;

import java.util.Arrays;

/**
 * Brute force. Time Limit Exceeded.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution {
    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;
        long ans = 0;
        Arrays.sort(deliciousness);
        for (int i = 0; i + 1 < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isPowerOfTwo(deliciousness[i] + deliciousness[j])) {
                    ++ans;
//                    System.out.printf("%d %d%n", Math.min(deliciousness[i], deliciousness[j]), Math.max(deliciousness[i], deliciousness[j]));
                }
            }
        }

        return (int) (ans % (1e9 + 7));
    }

    static boolean isPowerOfTwo(int d) {
        return d > 0 && (d & (d - 1)) == 0;
    }
}
