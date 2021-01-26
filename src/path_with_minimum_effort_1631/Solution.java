package path_with_minimum_effort_1631;

/**
 * Backtracking. Stackoverflow for large grid.
 *
 * - Update the global minEffort when a less-effort path is found.
 * - Abandon the current path if its max effort has exceeded the global minEffort -- this is what differentiate
 *      this approach from a brute-force one.
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution {

    // The answer.
    int minEffort;

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        // Initial state of DFS.
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        minEffort = Integer.MAX_VALUE;

        dfs(0, 0, 0, visited, heights, m, n);

        return minEffort;
    }

    void dfs(int rowPos, int colPos, int effort, boolean[][] visited,
            int[][] heights, int m, int n) {

        // Finished a path?
        if (rowPos == m - 1 && colPos == n - 1) {
            System.out.println("Got an answer: " + effort);
            minEffort = effort;
            return;
        }

        // Coordinate increments for enumerating the next steps.
        // There are four directions, and downward and leftward should be preferred so that
        // an answer (although may not be global optimum) can be found earlier.
        int[] deltaRow = {+1, +0, -1, +0,};
        int[] deltaCol = {+0, +1, +0, -1,};

        // For each next step, dive in.
        for (int i = 0; i < 4; ++i) {
            int r = rowPos + deltaRow[i];
            int c = colPos + deltaCol[i];
            if (r < 0 || r >= m || c < 0 || c >= n)
                continue;

            if (visited[r][c])
                continue;

            int e = Math.max(effort, Math.abs(heights[r][c] - heights[rowPos][colPos]));
            if (e >= minEffort)
                continue;

            visited[r][c] = true;
            dfs(r, c, e, visited, heights, m, n);

            // backtracking
            visited[r][c] = false;
        }
    }
}
