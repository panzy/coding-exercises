package path_with_minimum_effort_1631;

import java.util.Arrays;

/**
 * Binary search + DFS.
 *
 * The DFS function is almost identical to the previous solution, except that it doesn't
 * backtracking and try to find other paths with lower effort.
 *
 * Sample output:
 *      try with limit 499692... got an answer: 497467
 *      try with limit 248733... failed
 *      try with limit 373100... failed
 *      try with limit 435283... got an answer: 430152
 *      try with limit 401626... failed
 *      try with limit 415889... failed
 *      try with limit 423020... failed
 *      try with limit 426586... failed
 *      try with limit 428369... failed
 *      try with limit 429260... failed
 *      try with limit 429706... failed
 *      try with limit 429929... failed
 *      try with limit 430040... failed
 *      try with limit 430096... failed
 *      try with limit 430124... failed
 *      try with limit 430138... failed
 *      try with limit 430145... failed
 *      try with limit 430148... failed
 *      try with limit 430150... failed
 *      try with limit 430151... failed
 *      (End. The answer is 430152.)
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution2 {
    boolean verbose = true;

    int m, n;
    int[][] heights;
    boolean[][] visited;
    int currEffort; // effort of current path being searched

    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        m = heights.length;
        n = heights[0].length;
        visited = new boolean[m][n];

        int lo = 0;
        int hi = maxEffort(heights);

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (verbose) System.out.print("try with limit " + mi + "... ");

            // Reset visiting state.
            for (boolean[] row : visited)
                Arrays.fill(row, false);
            visited[0][0] = true;

            currEffort = 0;

            // Search a path with effort <= mi.
            dfs(0, 0, mi);

            if (visited[m - 1][n - 1]) { // found
                hi = currEffort;
            } else {
                if (verbose) System.out.println("failed");
                lo = lo == mi ? mi + 1 : mi;
                if (mi == (lo + hi) / 2)
                    break;
            }
        }
        return hi;
    }

    void dfs(int startRow, int startCol, int effortLimit) {

        // Finished a path?
        if (startRow == m - 1 && startCol == n - 1) {
            if (verbose) System.out.println("got an answer: " + currEffort);
        }

        // Coordinate increments for enumerating the next steps.
        // There are four directions, and downward and leftward should be preferred so that
        // an answer (although may not be global optimum) can be found earlier.
        int[] deltaRow = {+1, +0, -1, +0,};
        int[] deltaCol = {+0, +1, +0, -1,};

        int ans = effortLimit + 1; // a value greater then effortLimit means failed.

        // For each next step, dive in.
        for (int i = 0; i < 4; ++i) {
            int r = startRow + deltaRow[i];
            int c = startCol + deltaCol[i];
            if (r < 0 || r >= m || c < 0 || c >= n)
                continue;

            if (visited[r][c])
                continue;

            int e = Math.abs(heights[r][c] - heights[startRow][startCol]);
            if (e > effortLimit)
                continue;

            if (currEffort < e)
                currEffort = e;

            visited[r][c] = true;
            dfs(r, c, effortLimit);

            // NOTE!
            // We are not doing backtracking, so don't unset [r][c]'s visiting state.
            // visited[r][c] = false;
        }
    }

    private int maxEffort(int[][] heights) {
        int min = heights[0][0], max = heights[0][0];
        for (int[] r : heights) {
            for (int c : r) {
                min = Math.min(min, c);
                max = Math.max(max, c);
            }
        }
        return max - min;
    }
}
