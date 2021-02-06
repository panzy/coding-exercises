/**
 * Zhiyong Pan, 2020-12-25
 */
package diagonal_traverse;

public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[0];

        final int M = matrix.length;
        final int N = matrix[0].length;
        int[] a = new int[M * N]; // the output array
        int i = 0; // the current position in the output array
        int r = 0; // row index
        int c = 0; // col index
        boolean up = true; // is current traverse direction right-top?

        while (r < M && c < N) {
            a[i] = matrix[r][c];
            ++i;

            // turn if necessary
            if (up && (r == 0 || c == N - 1)) {
                // turn down
                up = false;
                // always try to move right.
                // if that's not possible, move down.
                if (c < N - 1) {
                    ++c;
                } else {
                    ++r;
                }
                continue;
            } else if (!up && (c == 0 || r == M - 1)) {
                // turn up
                up = true;
                // always try to move down.
                // if that's not possible, move right.
                if (r < M - 1) {
                    ++r;
                } else {
                    ++c;
                }
                continue;
            }

            // where in the matrix should be the next element?
            if (up) {
                --r;
                ++c;
            } else {
                ++r;
                --c;
            }
        }

        return a;
    }
}