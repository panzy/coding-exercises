package sort_the_matrix_diagonally_1329;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    int[][] mat;
    int m, n;

    public int[][] diagonalSort(int[][] mat) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;

        for (int r = 0; r < m - 1; ++r) {
            sort(r, 0);
        }

        for (int c = 1; c < n - 1; ++c) {
            sort(0, c);
        }

        return mat;
    }

    /**
     * Sort a diagonal starting from [r0,c0].
     */
    void sort(int r0, int c0) {
        for (int i = 0; r0 + i + 1 < m && c0 + i + 1 < n; ++i) {
            for (int j = i + 1; r0 + j < m && c0 + j < n; ++j) {
                int a = mat[r0 + i][c0 + i];
                int b = mat[r0 + j][c0 + j];
                if (a > b) {
                    mat[r0 + i][c0 + i] = b;
                    mat[r0 + j][c0 + j] = a;
                }
            }
        }
    }

    @Test
    void testSort() {
        int[][] mat = {
                {3, 3, 1, 1}, {2, 2, 1, 2}, {1, 1, 1, 2}
        };
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;

        sort(0, 0);
        Assertions.assertEquals(1, mat[0][0]);
        Assertions.assertEquals(2, mat[1][1]);
        Assertions.assertEquals(3, mat[2][2]);

        sort(0, 1);
        Assertions.assertEquals(1, mat[0][1]);
        Assertions.assertEquals(2, mat[1][2]);
        Assertions.assertEquals(3, mat[2][3]);

        sort(1, 0);
        Assertions.assertEquals(1, mat[1][0]);
        Assertions.assertEquals(2, mat[2][1]);
    }
}
