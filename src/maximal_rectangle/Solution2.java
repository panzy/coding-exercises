package maximal_rectangle;

import java.util.Arrays;

/**
 * Learned this amazing approach from this post:
 * https://leetcode.com/problems/maximal-rectangle/discuss/979263/C%2B%2B-DP-solution-99.07-faster-using-the-idea-of-Largest-Rect-in-Histogram
 *
 * Created by Zhiyong Pan on 2021-01-04.
 */
public class Solution2 {
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0) return 0;

        int ans = 0;

        // rectangles determined by each point of the current row
        //
        // for a point matrix[r][c], the rectangle being determined is defined by (height[c], left[c], right[c]).
        int[] height = new int[cols];
        int[] left = new int[cols];
        int[] right = new int[cols];

        Arrays.fill(height, 0);

        // left[] are init to the leftmost value because for each row we will update them
        // by scanning the row from left to right.
        // For the same reason, whenever we find a '0' point, we will reset that column's left position to 0.
        Arrays.fill(left, 0);

        // right[] are init to the rightmost value because for each row we will update them
        // by scanning the row from right to left.
        // For the same reason, whenever we find a '0' point, we will reset that column's right position to |cols|.
        Arrays.fill(right, cols);

        for (int r = 0; r < rows; ++r) {

            // update height and left
            for (int c = 0, segmentBegin = 0; c < cols; ++c) {
                if (matrix[r][c] == '1') {
                    ++height[c]; // +1 on the height[c] of the previous row
                    if (left[c] < segmentBegin) // the rect has becoming narrow at its left edge
                        left[c] = segmentBegin;
                } else {
                    height[c] = 0; // the rect collapsed
                    left[c] = 0; // the left pos doesn't matter for this row, but matters for the next row
                    segmentBegin = c + 1; // current segment ends here, move to the next
                }
            }

            // update right
            for (int c = cols - 1, segmentEnd = cols; c >= 0; --c) {
                if (matrix[r][c] == '1') {
                    if (right[c] > segmentEnd) // don't allow the rect to become wider -- that's other points' business
                        right[c] = segmentEnd;
                } else {
                    right[c] = cols; // the right pos doesn't matter for this row, but matters for the next row
                    segmentEnd = c;
                }
            }

            // collect the maximal area produced by this row
            for (int c = 0; c < cols; ++c) {
                ans = Math.max(ans, (right[c] - left[c]) * height[c]);
            }
        }

        return ans;
    }
}
