package largest_submatrix_with_rearrangements;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Let matrix[r][c] be the height of the pillar sitting on it.
        // The height includes [r][c] itself.
        for (int r = 1; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (matrix[r][c] == 1)
                    matrix[r][c] += matrix[r - 1][c];
            }
        }

        int ans = 0;

        // for each row, find the area of largest submatrix with its bottom on that row.
        for (int r = 0; r < m; ++r) {
            Arrays.sort(matrix[r]);
            for (int c = n - 1; c >= 0; --c) {
                // height = matrix[r][c],
                // width = n - c
                ans = Math.max(ans, matrix[r][c] * (n - c));
            }
        }

        return ans;
    }
}
