package kth_smallest_element_in_a_sorted_matrix_378;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 *
 * Binary search. Learned from the LeetCode official solution.
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/solution/
 *
 * Created by Zhiyong Pan on 2021-01-29.
 */
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (enough(matrix, m, n, k, mi)) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }

        return hi;
    }

    boolean enough(int[][] matrix, int m, int n, int k, int x) {
        int cnt = 0;
        for (int i = 0; i < m; ++i) {
            int pos = Arrays.binarySearch(matrix[i], x);
            if (pos >= 0) {
                // In case [pos] == x, make sure pos is the last element that equals to x.
                while (pos + 1 < n && matrix[i][pos + 1] <= x)
                    ++pos;
                cnt += pos + 1;
            } else
                cnt += -(pos + 1);
        }
        return cnt >= k;
    }

    @Test
    void testSortedMat() {
        Assertions.assertEquals(13, kthSmallest(new int[][]{
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15},
        }, 8));
        Assertions.assertEquals(2000000000, kthSmallest(new int[][]{
                {2000000000},
        }, 1));
        Assertions.assertEquals(25, kthSmallest(new int[][]{
                {2, 6, 6, 7, 10, 14, 18},
                {6, 11, 14, 14, 15, 20, 23},
                {11, 11, 17, 21, 25, 30, 30},
                {11, 12, 20, 25, 25, 35, 37},
                {16, 16, 20, 29, 34, 35, 39},
                {16, 18, 22, 33, 37, 37, 40},
                {17, 23, 26, 36, 38, 41, 42}
        }, 32));
        Assertions.assertEquals(2, kthSmallest(new int[][]{
                {1, 2}, {1, 3}
        }, 3));
    }
}