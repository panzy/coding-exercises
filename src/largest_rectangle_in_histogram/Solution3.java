package largest_rectangle_in_histogram;

/**
 * A divide & conquer approach -- divide the histogram into ordered parts.
 *
 * It's super easy to find the largest rectangle area in a set of desc ordered bars: all the rectangles
 * share the same left edge, which is the highest bar. So it's an O(N) scan.
 *
 * For asc ordered bars, the same algorithm applies, just reverse the scan direction.
 *
 * To get ordered parts, we can divide the histogram at the lowest-height bars.
 *
 * But doing that may results wrong answer unless the lowest-height is zero. That's because the actual largest might be
 * the rectangle laying on the bottom.
 *
 * To accommodate this, we subtract the histogram by the bottom rectangle.
 *
 * Essentially, we repeatedly subtract the current portion of histogram by its base rectangle,
 * resulting multiple isolated histograms.
 *
 * This solution seems to be fast enough, beating 99.82% of java submissions.
 *
 * Created by Zhiyong Pan on 2020-12-31.
 */
public class Solution3 {
    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length, 0, 0);
    }

    /**
     * Find the largest rectangle area between [begin, end).
     * @param heights
     * @param begin the first bar (inclusive).
     * @param end the ending bar (exclusive).
     * @param baseHeight the height of the rectangle which was laying at the bottom of this histogram and has been
     *                  subtracted from it.
     * @param bestAnswer the largest rectangle area that has been found in other parts so far.
     * @return only accurate if it's not less than |bestAnswer|.
     */
    private int largestRectangleArea(int[] heights, int begin, int end, int baseHeight, int bestAnswer) {

        // trim zero bars
        while (begin + 1 < end && heights[begin] == 0) ++begin;
        while (end - 1 > begin && heights[end - 1] == 0) --end;

        if (begin == end) return baseHeight;
        if (begin + 1 == end) return baseHeight + heights[begin];
        if (begin + 2 == end) return 2 * (baseHeight + Math.min(heights[begin], heights[begin + 1]));

        // inspect the bars
        int minPos = begin;
        int maxPos = begin;
        boolean ascOrder = true;
        boolean descOrder = true;
        for (int i = begin + 1; i < end; ++i) {
            if (heights[i] < heights[minPos]) minPos = i;
            else if (heights[i] > heights[maxPos]) maxPos = i;
            if (heights[i - 1] < heights[i]) descOrder = false;
            else if (heights[i - 1] > heights[i]) ascOrder = false;
        }

        // shortcuts
        if (minPos == maxPos) return (baseHeight + heights[minPos]) * (end - begin);
        if (descOrder) return searchInOrderedBars(heights, begin, end, 1, baseHeight);
        if (ascOrder) return searchInOrderedBars(heights, end - 1, begin - 1, -1, baseHeight);
        // don't bother calculating the accurate area if it never exceeds the best answer.
        if ((baseHeight + heights[maxPos]) * (end - begin) < bestAnswer) return bestAnswer;

        int ans = bestAnswer;
        int currBase = heights[minPos];

        // subtract the histogram by the base rectangle
        if (currBase > 0) {
            for (int i = begin; i < end; ++i) {
                heights[i] -= currBase;
            }
            ans = Math.max(ans, (baseHeight + currBase) * (end - begin));
        }

        // divide & conquer
        for (int i = begin; i < end; ++i) {
            while (i + 1 < end && heights[i] == 0) ++i;
            int j = i + 1;
            while (j < end && heights[j] != 0) ++j;
            if (j > i)
                ans = Math.max(ans, largestRectangleArea(heights, i, j, baseHeight + currBase, ans));
            i = j;
        }

        return ans;
    }

    /**
     * Find the largest rectangle area in a set of desc ordered bars.
     * @param heights
     * @param begin index of the highest bar
     * @param end index of the lowest bar plus |step|
     * @param step 1 if end > begin, -1 if otherwise.
     * @param baseHeight indicates that all heights have been subtracted by |baseHeight|.
     * @return the largest area.
     */
    private int searchInOrderedBars(int[] heights, int begin, int end, int step, int baseHeight) {
        int ans = 0;
        for (int i = begin; i != end; i += step) {
            ans = Math.max(ans, (i + step - begin) * step * (baseHeight + heights[i]));
        }
        return ans;
    }
}
