package number_of_islands;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Flood.
 *
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class Solution {
    // Relative coordinates of all neighbours for a given cell.
    // neighbours[i] = {deltaY, deltaX}
    private static int[][] neighbours = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private char[][] grid;
    private int m, n;

    public int numIslands(char[][] grid) {
        int ans = 0;

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        for (int y = 0; y < m; ++y) {
            for (int x = 0; x < n; ++x) {
                if (grid[y][x] == '1') {
                    ++ans;
                    flood(y, x);
                }
            }
        }

        return ans;
    }

    private void flood(int y, int x) {
        assert grid[y][x] == '1';

        Queue<int[]> queue = new ArrayDeque<>();
        grid[y][x] = '2';
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] seed = queue.poll();
            for (int[] delta : neighbours) {
                int y2 = seed[0] + delta[0];
                int x2 = seed[1] + delta[1];
                if (y2 >= 0 && y2 < m && x2 >= 0 && x2 < n && grid[y2][x2] == '1') {
                    grid[y2][x2] = '2';
                    queue.add(new int[]{y2, x2});
                }
            }
        }
    }
}
