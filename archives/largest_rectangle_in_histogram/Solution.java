package largest_rectangle_in_histogram;

/**
 * A easy, correct but slow solution.
 *
 * Created by Zhiyong Pan on 2020-12-31.
 */
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;

        for (int i = 0; i < heights.length; ++i) {
            // Before actually search for the current rect's left and right sides, do some quick check.
            if (heights[i] == 0) continue;
            if (i > 0 && heights[i] == heights[i - 1]) continue; // rect[i] is impossible to be larger than rect[i-1].
            if (ans > (long) heights.length * heights[i]) continue; // rect[i] is impossible to be a better answer.

            // the rect will be in the range [k,j).
            int j = i + 1, k = i;
            while (j < heights.length && heights[i] <= heights[j]) ++j;
            while (k > 0 && heights[i] <= heights[k - 1]) --k;
            ans = Math.max(ans, (j - k) * heights[i]);
        }

        return ans;
    }
}