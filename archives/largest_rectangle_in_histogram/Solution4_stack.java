package largest_rectangle_in_histogram;

import java.util.Stack;

/**
 * Use a stack to delay computing the rectangle area defined by a height.
 *
 * This is not as fast as the divide & conquer solution, but it's much simple to implement and its performance isn't
 * bad at all.
 *
 * More importantly, it demonstrates an advanced usage of stack -- keep it monolithic.
 *
 * Created by Zhiyong Pan on 2021-02-10.
 */
public class Solution4_stack {
    public int largestRectangleArea(int[] heights) {
        final int n = heights.length;

        // stack element = the left and top of a rectangle which we don't know where it will end.
        Stack<int[]> activeCorners = new Stack();
        int area = 0;

        for (int i = 0; i <= n; ++i) {
            int h = i < n ? heights[i] : 0; // the non-existing height[n] is the sentinel.
            int[] newCorner = new int[]{i, h};

            while (!activeCorners.isEmpty() && activeCorners.peek()[1] > h) {
                int[] c = activeCorners.pop();
                area = Math.max(area, (i - c[0]) * c[1]);
                newCorner[0] = c[0]; // Extend this new corner's left position.
            }
            activeCorners.push(newCorner);
        }

        return area;
    }
}
