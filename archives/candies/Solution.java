package candies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Candies
 * https://www.hackerrank.com/challenges/candies/problem
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    static long candies(int n, int[] arr) {
        // Ignoring each student's right side, how many candy should he have?
        int[] candy1 = new int[n];
        // Ignoring each student's left side, how many candy should he have?
        int[] candy2 = new int[n];
        // The final number of candy for a student is max(candy1, candy2).

        candy1[0] = 1;
        for (int i = 1; i < n; ++i) {
            if (arr[i] > arr[i - 1]) {
                candy1[i] = candy1[i - 1] + 1;
            } else {
                candy1[i] = 1;
            }
        }

        candy2[n - 1] = 1;
        for (int i = n - 2; i >= 0; --i) {
            if (arr[i] > arr[i + 1]) {
                candy2[i] = candy2[i + 1] + 1;
            } else {
                candy2[i] = 1;
            }
        }

        long sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += Math.max(candy1[i], candy2[i]);
        }

        return sum;
    }

    @Test
    void test() {
        Assertions.assertEquals(19, candies(10, new int[]{2, 4, 2, 6, 1, 7, 8, 9, 2, 1}));
        Assertions.assertEquals(46, candies(10, new int[]{1, 9, 8, 7, 6, 5, 4, 3, 2, 1}));
        Assertions.assertEquals(4, candies(3, new int[]{1, 2, 2}));
        Assertions.assertEquals(10, candies(6, new int[]{4, 6, 4, 5, 6, 2}));
    }
}
