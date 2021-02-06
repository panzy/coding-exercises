package kth_smallest_number_in_multiplication_table_668;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Binary search. Learned from the LeetCode official solution.
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/solution/
 *
 * Created by Zhiyong Pan on 2021-01-29.
 */
public class Solution {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (enough(m, n, k, mi)) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }

        return hi;
    }

    boolean enough(int m, int n, int k, int x) {
        int cnt = 0;
        for (int i = 1; i <= m; ++i) {
            cnt += Math.min(n, x / i);
        }
        return cnt >= k;
    }

    @Test
    void testMulTable1() {
        // 3x3
        Assertions.assertEquals(1, findKthNumber(3, 3, 1));
        Assertions.assertEquals(2, findKthNumber(3, 3, 2));
        Assertions.assertEquals(2, findKthNumber(3, 3, 3));
        Assertions.assertEquals(3, findKthNumber(3, 3, 4));
        Assertions.assertEquals(3, findKthNumber(3, 3, 5));
        Assertions.assertEquals(4, findKthNumber(3, 3, 6));
        Assertions.assertEquals(6, findKthNumber(3, 3, 7));
        Assertions.assertEquals(6, findKthNumber(3, 3, 8));
        Assertions.assertEquals(9, findKthNumber(3, 3, 9));
    }

    @Test
    void testMulTable2() {
        // 2x3
        Assertions.assertEquals(1, findKthNumber(2, 3, 1));
        Assertions.assertEquals(2, findKthNumber(2, 3, 2));
        Assertions.assertEquals(2, findKthNumber(2, 3, 3));
        Assertions.assertEquals(3, findKthNumber(2, 3, 4));
        Assertions.assertEquals(4, findKthNumber(2, 3, 5));
        Assertions.assertEquals(6, findKthNumber(2, 3, 6));

        // 3x2
        Assertions.assertEquals(1, findKthNumber(3, 2, 1));
        Assertions.assertEquals(2, findKthNumber(3, 2, 2));
        Assertions.assertEquals(2, findKthNumber(3, 2, 3));
        Assertions.assertEquals(3, findKthNumber(3, 2, 4));
        Assertions.assertEquals(4, findKthNumber(3, 2, 5));
        Assertions.assertEquals(6, findKthNumber(3, 2, 6));
    }

    @Test
    void testMulTable3() {
        // 2x5
        Assertions.assertEquals(1, findKthNumber(2, 5, 1));
        Assertions.assertEquals(2, findKthNumber(2, 5, 2));
        Assertions.assertEquals(2, findKthNumber(2, 5, 3));
        Assertions.assertEquals(3, findKthNumber(2, 5, 4));
        Assertions.assertEquals(4, findKthNumber(2, 5, 5));
        Assertions.assertEquals(4, findKthNumber(2, 5, 6));
        Assertions.assertEquals(5, findKthNumber(2, 5, 7));
        Assertions.assertEquals(6, findKthNumber(2, 5, 8));
        Assertions.assertEquals(8, findKthNumber(2, 5, 9));
        Assertions.assertEquals(10, findKthNumber(2, 5, 10));
    }
}
