package perfect_rectangle;

import _lib.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Tackle the worst scenario faced by solution (failure) #1 with a to-do list.
 *
 * The improvement itself somewhat works, reducing the runtime from ~6s to ~200ms.
 * But the previous solution has been proven wrong due to a fundamental flaw in its core idea,
 * so this improved one is incorrect anyway.
 *
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class SolutionFailure2 {
    // A customized comparator for rectangles.
    // Two rectangles equal if their bottom-left corners are the same.
    Comparator<int[]> compBottomAndLeft = (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];

    // Another comparator.
    // Two rectangles equal if their top-right corners are the same.
    final Comparator<int[]> compTopAndRight = (a, b) -> a[3] == b[3] ? a[2] - b[2] : a[3] - b[3];

    int[][] rectangles;
    int[][] topRightIndex;

    public boolean isRectangleCover(int[][] rectangles) {
        this.rectangles = rectangles;

        // sort by their bottom-left corners
        Arrays.sort(rectangles, compBottomAndLeft);

        topRightIndex = new int[rectangles.length][];
        for (int i = 0; i < rectangles.length; ++i) {
            topRightIndex[i] = Arrays.copyOf(rectangles[i], 4);
        }
        Arrays.sort(topRightIndex, compTopAndRight);

        int remains = rectangles.length;

        LinkedList<Pair<int[], int[]>> todos = new LinkedList<>();

        // merge adjacent rectangles until it's no longer possible to do so.
        // A rectangle that has been merged into another is marked by setting its top to MIN_VALUE of int.
        while (true) {
            int remainsBak = remains;
            boolean failed = false;

            while (!todos.isEmpty()) {
                Pair<int[], int[]> pair = todos.removeFirst();
                int m = merge(pair.getKey(), todos, pair.getValue());
                if (m == -1) {
                    failed = true;
                    break;
                }
                if (m == 1) {
                    --remains;
                }
            }
            if (failed || remains == 1)
                break;

            for (int[] a : rectangles) {
                if (a[3] == Integer.MIN_VALUE)
                    continue;

                int m = merge(a, todos, null);
                if (m == -1) {
                    failed = true;
                    break;
                }
                if (m == 1) {
                    --remains;
                }
            }

            if (failed)
                break;

            // can't merge any further
            if (remains == remainsBak)
                break;
        }

        // If there is only one rectangle left then it's the perfect one.
        return remains == 1;
    }

    int merge(int[] a, LinkedList<Pair<int[], int[]>> todos, int[] hint) {
        if (hint != null) {
            if (a[1] == hint[1]) { // hint is the right neighbour
//                assert a[3] == hint[3];
                a[2] = hint[2]; // extend a to cover the right neighbour
                hint[3] = Integer.MIN_VALUE;
                findBelow(a, todos);
                return 1;
            } else if (a[0] == hint[0]) { // hint is the above neighbour
//                assert a[2] == hint[2];
                a[3] = hint[3]; // extend a to cover the above neighbour
                hint[3] = Integer.MIN_VALUE;
                findLeft(a, todos);
                return 1;
            }
        }

        int right = mergeRight(a);
        if (right == -1) {
            return -1;
        }
        if (right == 1) {
            findBelow(a, todos);
            return 1;
        }

        int above = mergeAbove(a);
        if (above == -1) {
            return -1;
        }
        if (above == 1) {
            findLeft(a, todos);
            return 1;
        }

        return 0;
    }

    private void findLeft(int[] a, LinkedList<Pair<int[], int[]>> todos) {
        // search at the top-left corner
        int i = Arrays.binarySearch(topRightIndex, new int[]{0, 0, a[0], a[3]}, compTopAndRight);
        if (i >= 0) {
            int[] b = topRightIndex[i];
            if (b[1] == a[1]) {
                i = Arrays.binarySearch(rectangles, b, compBottomAndLeft);
                if (i >= 0) {
                    b = rectangles[i];
                    if (b[3] != Integer.MIN_VALUE && b[3] == a[3] && b[2] == a[0])
                        todos.addFirst(new Pair(b, a));
                }
            }
        }
    }

    private void findBelow(int[] a, LinkedList<Pair<int[], int[]>> todos) {
        // search at the bottom-right corner
        int i = Arrays.binarySearch(topRightIndex, new int[]{0, 0, a[2], a[1]}, compTopAndRight);
        if (i >= 0) {
            int[] b = topRightIndex[i];
            if (b[0] == a[0]) {
                i = Arrays.binarySearch(rectangles, b, compBottomAndLeft);
                if (i >= 0) {
                    b = rectangles[i];
                    if (b[3] != Integer.MIN_VALUE && b[2] == a[2] && b[3] == a[1])
                        todos.addFirst(new Pair(b, a));
                }
            }
        }
    }

    private int mergeRight(int[] a) {
        // search at the bottom-right corner
        int i = Arrays.binarySearch(rectangles, new int[]{a[2], a[1]}, compBottomAndLeft);
        if (i >= 0) {
            int[] b = rectangles[i];
            if (b[3] != Integer.MIN_VALUE
                    && a[1] == b[1] // a.bottom == b.bottom
                    && a[2] == b[0] // a.right == b.left
                    && a[3] == b[3] // a.top == b.top
            ) {
                a[2] = b[2]; // extend a.right to b.right
                b[3] = Integer.MIN_VALUE; // mark rectangle[i] as been removed
                return 1;
            }
        }
        return 0;
    }

    private int mergeAbove(int[] a) {
        // search at the top-left corner
        int j = Arrays.binarySearch(rectangles, new int[]{a[0], a[3]}, compBottomAndLeft);
        if (j >= 0) {
            int[] b = rectangles[j];
            if (b[3] != Integer.MIN_VALUE
                    && a[0] == b[0] // a.left == b.left
                    && a[2] == b[2] // a.right == b.right
                    && a[3] == b[1] // a.top == b.bottom
            ) {
                a[3] = b[3]; // extend a.top to b.top
                b[3] = Integer.MIN_VALUE; // mark rectangle[j] as been removed
                return 1;
            }
        }
        return 0;
    }
}
