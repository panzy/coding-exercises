package merge_intervals_56;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort by left positions.
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

        int n = intervals.length;
        int ansCnt = 1;
        int masterIdx = 0;

        for (int i = 1; i < n; ++i) {
            if (intervals[masterIdx][1] >= intervals[i][0]) { // overlap happens
                // Merge [j] into [masterIdx], then delete [j].
                intervals[masterIdx][1] = Math.max(intervals[masterIdx][1], intervals[i][1]);
                intervals[i][0] = Integer.MIN_VALUE; // mark as deleted
            } else {
                masterIdx = i;
                ++ansCnt;
            }
        }

        int[][] ans = new int[ansCnt][];
        for (int i = 0, j = 0; i < n; ++i) {
            if (intervals[i][0] != Integer.MIN_VALUE) {
                ans[j++] = intervals[i];
            }
        }
        return ans;
    }

}
