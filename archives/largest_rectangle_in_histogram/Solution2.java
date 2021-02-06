package largest_rectangle_in_histogram;

/**
 * A easy, correct but slow solution.
 *
 * Created by Zhiyong Pan on 2020-12-31.
 */
public class Solution2 {
    public int largestRectangleArea(int[] heights) {
        final int n = heights.length;

        if (n == 0) return 0;

        int ans = 0;
        int a = 0, b = 1;

        // search the horizontal range of the first rect
        while (b < n && heights[b] >= heights[a]) ++b;
        ans = heights[0] * (b - a);

        for (int i = 1; i < n; ++i) {
            if (heights[i] > heights[i - 1]) {
                // A[i] == i
                // B[i] <= B[i-1]
                a = i;
                b = i + 1;
                while (b < n && heights[b] >= heights[a]) ++b;
            } else if (heights[i] < heights[i - 1]) {
                // A[i] <= A[i-1]
                // B[i] >= B[i-1]
                while (a > 0 && heights[a - 1] >= heights[i]) --a;
                while (b < n && heights[b] >= heights[i]) ++b;
            } else {
                // A[i] == A[i-1]
                // B[i] == B[i-1]
                continue;
            }

            ans = Math.max(ans, (b - a) * heights[i]);
        }

        return ans;
    }
}
