package perfect_rectangle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Merge adjacent rectangles until it's no longer possible to do so. Slow in worst cases.
 *
 * It doesn't work because in some cases, you can't merge any two rectangles yet the whole of them is a perfect one.
 * For an example, so image file impossible-to-merge.png, which was taken from https://www.geogebra.org/m/z3skhnf6 .
 *
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class SolutionFailure1 {
    public boolean isRectangleCover(int[][] rectangles) {
        // A customized comparator for rectangles.
        // Two rectangles equal if their bottom-left corners are the same.
        Comparator<int[]> compBottomAndLeft = (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];

        // sort by their bottom-left corners
        Arrays.sort(rectangles, compBottomAndLeft);

        int remains = rectangles.length;

        // merge adjacent rectangles until it's no longer possible to do so.
        // A rectangle that has been merged into another is marked by setting its top to MIN_VALUE of int.
        while (true) {
            boolean mergeHappened = false;
            for (int[] a : rectangles) {
                if (a[3] == Integer.MIN_VALUE) continue;

                // search at the bottom-right corner
                int i = Arrays.binarySearch(rectangles, new int[]{a[2], a[1]}, compBottomAndLeft);
                if (i >= 0) {
                    int[] b = rectangles[i];
                    if (b[3] != Integer.MIN_VALUE && a[3] == b[3]) {
                        a[2] = b[2]; // extend a.right to b.right
                        mergeHappened = true;
                        b[3] = Integer.MIN_VALUE; // mark rectangle[i] as been removed
                        --remains;
                    }
                }

                // search at the top-left corner
                int j = Arrays.binarySearch(rectangles, new int[]{a[0], a[3]}, compBottomAndLeft);
                if (j >= 0) {
                    int[] b = rectangles[j];
                    if (b[3] != Integer.MIN_VALUE && a[2] == b[2]) {
                        a[3] = b[3]; // extend a.top to b.top
                        mergeHappened = true;
                        b[3] = Integer.MIN_VALUE; // mark rectangle[j] as been removed
                        --remains;
                    }
                }
            }

            if (!mergeHappened) break;
        }

        // If there is only one rectangle left then it's the perfect one.
        return remains == 1;
    }
}
