package maximal_rectangle;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Zhiyong Pan on 2021-01-03.
 */
public class Solution {
    private static class Rect {
        int left;
        int right;
        int bottom;
        int top;

        Rect(int l, int r, int t, int b) {
            left = l;
            right = r;
            top = t;
            bottom = b;
        }

        int area() {
            return (right - left) * (bottom - top);
        }
    }

    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0) return 0;

        int ans = scanTopDown(matrix);

        // flip the matrix and try again
        flipMatrix(matrix);
        ans = Math.max(ans, scanTopDown(matrix));

        // rotate the matrix 90 degree and try again
        char[][] mat2 = rotate90(matrix);
        ans = Math.max(ans, scanTopDown(mat2));

        // flip the rotated matrix and try again
        flipMatrix(mat2);
        ans = Math.max(ans, scanTopDown(mat2));

        return ans;
    }

    /**
     * Flip a matrix upside-down.
     * @param matrix
     */
    private void flipMatrix(char[][] matrix) {
        int rows = matrix.length;
        for (int y1 = 0, y2 = rows - 1; y1 < y2; ++y1, --y2) {
            char[] t = matrix[y1];
            matrix[y1] = matrix[y2];
            matrix[y2] = t;
        }
    }

    /**
     * Rotate a matrix by 90 degrees.
     * @param matrix
     * @return
     */
    private char[][] rotate90(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] m = new char[cols][rows];
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                m[c][r] = matrix[r][c];
            }
        }
        return m;
    }

    private int scanTopDown(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        LinkedList<Rect> rects = new LinkedList<>();

        for (int y = 0; y < rows; ++y) {
            char[] row = matrix[y];

            //
            // for each line-segment in this row
            //

            int left = 0;
            while (left < cols && row[left] == '0') ++left;

            while (left < cols) {
                int right = left + 1;
                while (right < cols && row[right] == '1') ++right;

                // now we have a segment between [left, right).
                ListIterator<Rect> itr = rects.listIterator();
                boolean exactMatched = false;
                while (itr.hasNext()) {
                    Rect r = itr.next();
                    if (y == r.bottom && left <= r.left && right >= r.right) { // this rect will grow to this row
                        if (left == r.left && right == r.right) {
                            assert !exactMatched;
                            exactMatched = true;
                        }
                        r.bottom = y + 1;
                    }
                }

                if (!exactMatched) { // a new rect started
                    Rect r = new Rect(left, right, y, y + 1);
                    rects.add(r);
                    // but wait, the actual top of this new rect might be hidden above
                    while (r.top > 0 && allSet(matrix[r.top - 1], left, right)) --r.top;
                }

                // move to next segment
                left = right + 1;
                while (left < cols && row[left] == '0') ++left;
            }
        }

        int ans = 0;
        for (Rect r : rects) {
            ans = Math.max(ans, r.area());
        }

        return ans;
    }

    private boolean allSet(char[] row, int begin, int end) {
        for (int i = begin; i < end; ++i) {
            if (row[i] != '1')
                return false;
        }
        return true;
    }
}
