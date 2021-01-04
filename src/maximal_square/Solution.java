package maximal_square;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * This solution was copied from the my solution to another question, Maximal Rectangle, and the only other modification
 * other than renaming the member function from "maximalRectangle" to "maximalSquare" was changing the {@link Rect#area()},
 * making it return the area of a square that the rectangle can be cropped to.
 *
 * Although it's not very efficient, it's fun to conquer another problem with such little modification.
 *
 * Adapted from {@link maximal_rectangle.Solution}, git commit 4e5d63ef7fad7542fd2c6cb9a2edeadf5bb1905c.
 *
 * Created by Zhiyong Pan on 2021-01-04.
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
            int size = Math.min(right - left, bottom - top);
            return size * size;
        }
    }

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;
        if (cols == 0) return 0;

        LinkedList<Rect> rects = new LinkedList<>();
        int maxArea = 0;

        // Use a hash map to keep track of alive rectangles.
        // key = rect vertical positions = (left << 16) | right
        HashMap<Integer, Rect> aliveRects = new HashMap();

        // scan top-down
        for (int y = 0; y < rows; ++y) {
            char[] row = matrix[y];

            // new rectangles created for this matrix row
            LinkedList<Rect> newRowRects = new LinkedList<>();

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

                // a rect with its top edge being [left, right) may or may not need to create.
                Rect newSegmentRect = null;

                // if an existing rect sitting exactly between [left, right) has been found.
                boolean exactMatched = false;

                for (Rect r : rects) {

                    // all other rectangles should have been removed from the list
                    assert r.bottom == y || r.bottom == y + 1;

                    if (left <= r.left && right >= r.right) { // widen
                        r.bottom = y + 1;
                        if (left == r.left && right == r.right) {
                            assert !exactMatched;
                            exactMatched = true;
                        }
                    } else if (left >= r.left && right <= r.right) { // narrow
                        // notice that:
                        // (1) the new rect might have already been created in previous iterations,
                        // (2) the new rect top is no lower than the current one, which is wider.
                        if (newSegmentRect == null) {
                            newSegmentRect = new Rect(left, right, r.top, y + 1);
                            aliveRects.put((newSegmentRect.left << 16) | newSegmentRect.right, newSegmentRect);
                        } else {
                            newSegmentRect.top = Math.min(newSegmentRect.top, r.top);
                        }
                    } else if ((left < r.left && r.left < right) || (r.left < left && left < r.right)) {
                        // where partial overlapping of two edges happens, a new rect is hidden there.
                        //      |##########| <- old rect
                        //   |%%%%%%%%%|     <- new rect
                        //      ^------^     <- another new rect
                        // the new rect top is no lower than the old rect
                        int left2 = Math.max(left, r.left);
                        int right2 = Math.min(right, r.right);
                        int key = (left2 << 16) | right2;
                        Rect r2 = aliveRects.get(key);
                        if (r2 == null) {
                            r2 = new Rect(left2, right2, r.top, y + 1);
                            newRowRects.add(r2);
                            aliveRects.put(key, r2);
                        } else {
                            r2.top = Math.min(r2.top, r.top);
                        }
                    }
                }

                if (!exactMatched) { // a new rect started
                    if (newSegmentRect == null) {
                        newSegmentRect = new Rect(left, right, y, y + 1);
                        aliveRects.put((newSegmentRect.left << 16) | newSegmentRect.right, newSegmentRect);
                    }
                    newRowRects.add(newSegmentRect);
                }

                // move to next segment
                left = right + 1;
                while (left < cols && row[left] == '0') ++left;
            }

            // remove rectangles that end by this row
            ListIterator<Rect> itr = rects.listIterator();
            while (itr.hasNext()) {
                Rect r = itr.next();
                if (r.bottom <= y) {
                    maxArea = Math.max(maxArea, r.area());
                    itr.remove();
                    aliveRects.remove((r.left << 16) | r.right);
                }
            }

            // If a rect will not exceed the maximal area even when if reaches the bottom of the matrix,
            // then it's safe to remove it.
            itr = rects.listIterator();
            while (itr.hasNext()) {
                Rect r = itr.next();
                if ((rows - r.top) * (r.right - r.left) < maxArea) {
                    itr.remove();
                    aliveRects.remove((r.left << 16) | r.right);
                }
            }

            // add new rectangles to the list
            if (newRowRects.size() > 0) {
                rects.addAll(newRowRects);
                newRowRects.clear();
            }
        }

        // reduce the rectangles to their maximal area
        return rects.stream().reduce(maxArea,
                (a, r) -> Math.max(a, r.area()),
                (a1, a2) -> Math.max(a1, a2));
    }
}
