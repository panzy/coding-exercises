package find_kth_largest_xor_coordinate_value_1736;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public int kthLargestValue(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] x = new int[m][n];

        x[0][0] = matrix[0][0];
        for (int r = 1; r < m; ++r) {
            x[r][0] = matrix[r][0] ^ x[r - 1][0];
        }
        for (int c = 1; c < n; ++c) {
            x[0][c] = matrix[0][c] ^ x[0][c - 1];
        }
        for (int r = 1; r < m; ++r) {
            for (int c = 1; c < n; ++c) {
                x[r][c] = matrix[r][c] ^ x[r][c - 1] ^ x[r - 1][c] ^ x[r - 1][c - 1];

            }
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                heap.add(x[r][c]);
            }
        }

        for (int i = 0; i + 1 < k; ++i) {
            heap.poll();
        }
        return heap.poll();
    }

    @Test
    void testXor() {
        Assertions.assertEquals(7, kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 1));
        Assertions.assertEquals(5, kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 2));
        Assertions.assertEquals(4, kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 3));
        Assertions.assertEquals(0, kthLargestValue(new int[][]{{5, 2}, {1, 6}}, 4));

        Assertions.assertEquals(5, kthLargestValue(new int[][]{{5}}, 1));

        kthLargestValue(new int[][]{{5, 2, 4, 5, 6}}, 4);
        kthLargestValue(new int[][]{{5, 2, 4, 5}}, 4);
        kthLargestValue(new int[][]{{5}, {2}, {4}, {5}}, 4);
    }
}
