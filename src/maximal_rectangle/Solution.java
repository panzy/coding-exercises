package maximal_rectangle;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Scan rows top-down, collecting rectangles on the way.
 *
 * This solution was accepted but it's slow. There are too many searching in lists.
 *
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

        LinkedList<Rect> rects = new LinkedList<>();
        int maxArea = 0;

        // Use a bit set to keep track of alive rectangles.
        // key = rect vertical positions = (left << 16) | right
        BitSet aliveRects = new BitSet(cols * cols);

        // scan top-down
        for (int y = 0; y < rows; ++y) {
            char[] row = matrix[y];

            LinkedList<Rect> newRects = new LinkedList<>(); // collect new rects during the iterating

            //
            // for each line-segment in this row
            //
            // the line-segment is defined as [left,right).
            //

            int left = 0, right;

            // search the left pos
            while (left < cols && row[left] == '0') ++left;

            while (left < cols) {

                // search the right pos
                right = left + 1;
                while (right < cols && row[right] == '1') ++right;

                // now we have a segment between [left, right).

                // grow existing rectangles
                ListIterator<Rect> itr = rects.listIterator();
                boolean exactMatched = false;
                while (itr.hasNext()) {
                    Rect r = itr.next();
                    if (r.bottom < y) // this rect is no longer relevant
                        continue;
                    if (left <= r.left && right >= r.right) { // this rect will grow to this row
                        r.bottom = y + 1;
                        if (left == r.left && right == r.right) {
                            assert !exactMatched;
                            exactMatched = true;
                        }
                    } else if ((left - r.left) * (r.right - left) > 0 || (r.left - left) * (right - r.left) > 0) {
                        // where partial overlapping of two edges happens, a new rect is hidden there.
                        //      |##########| <- old rect
                        //   |%%%%%%%%%|     <- new rect
                        //      ^------^     <- another new rect
                        // the new rect top is no lower than the old rect
                        newRects.add(new Rect(Math.max(left, r.left), Math.min(right, r.right), r.top, y + 1));
                    }
                }

                if (!exactMatched) { // a new rect started
                    newRects.add(new Rect(left, right, y, y + 1));
                }

                // move to next segment
                left = right + 1;
                while (left < cols && row[left] == '0') ++left;
            }

            // remove unuseful rects
            ListIterator<Rect> itr = rects.listIterator();
            while (itr.hasNext()) {
                Rect r = itr.next();
                if (r.bottom < y) {
                    maxArea = Math.max(maxArea, r.area());
                    itr.remove();
                    aliveRects.clear((r.left << 16) | r.right);
                }
            }

            // append new rects
            if (newRects.size() > 0) {
                for (Rect r2 : newRects) {
                    int key = (r2.left << 16) | r2.right;
                    if (!aliveRects.get(key)) {
                        // but wait, the actual top of this new rect might be hidden above
                        while (r2.top > 0 && allSet(matrix[r2.top - 1], r2.left, r2.right)) --r2.top;
                        rects.add(r2);
                        aliveRects.set(key);
                    }
                }
                newRects.clear();
            }
        }

        // reduce the rectangles to their maximal area
        return rects.stream().reduce(maxArea, (a, r) -> Math.max(a, r.area()), (a1, a2) -> Math.max(a1, a2));
    }

    private boolean allSet(char[] row, int begin, int end) {
        for (int i = begin; i < end; ++i) {
            if (row[i] != '1')
                return false;
        }
        return true;
    }
}
