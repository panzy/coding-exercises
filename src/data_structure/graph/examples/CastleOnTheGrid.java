package data_structure.graph.examples;

import data_structure.graph.GraphBFS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Castle on the Grid
 * https://www.hackerrank.com/challenges/castle-on-the-grid/problem
 *
 * Like a chess game. Your playing piece can move along a row or column any cells as long
 * as it is not blocked by a blocking cell.
 *
 * Still a graph BFS problem. You just have to define the edges in a different way.
 *
 * Created by Zhiyong Pan on 2021-02-10.
 */
public class CastleOnTheGrid {
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        int m = grid.length;
        int n = grid[0].length();
        int[][] map = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; ++i) {
            char[] carr = grid[i].toCharArray();
            map[i] = new int[carr.length];
            for (int j = 0; j < carr.length; ++j)
                map[i][j] = carr[j];

            visited[i] = new boolean[n];
        }

        int start = (startX << 16) + startY;
        int goal = (goalX << 16) + goalY;
        AtomicInteger ans = new AtomicInteger(-1);

        GraphBFS bfs = new GraphBFS((id -> {
            ArrayList<Integer> neibs = new ArrayList<>();
            int r = id >> 16;
            int c = id & 0xffff;

            // go right
            for (int i = c + 1; i < n && map[r][i] == '.'; ++i) {
                neibs.add((r << 16) + i);
            }
            // go left
            for (int i = c - 1; i >= 0 && map[r][i] == '.'; --i) {
                neibs.add((r << 16) + i);
            }
            // go up
            for (int j = r - 1; j >= 0 && map[j][c] == '.'; --j) {
                neibs.add((j << 16) + c);
            }
            // go bottom
            for (int j = r + 1; j < m && map[j][c] == '.'; ++j) {
                neibs.add((j << 16) + c);
            }

            return neibs;
        }), ((currNode, prevNode, originNode, currLayerIdx) -> {
            int r = currNode >> 16;
            int c = currNode & 0xffff;
            if (visited[r][c])
                return false;
            visited[r][c] = true;

            if (currNode == goal) {
                ans.set(currLayerIdx);
                return false;
            }

            return true;
        }), start);

        while (ans.get() == -1 && !bfs.ended())
            bfs.nextLayer();

        return ans.get();
    }

    @Test
    void test() {
        Assertions.assertEquals(3, minimumMoves(new String[]{".X.", ".X.", "..."}, 0, 0, 0, 2));
        Assertions.assertEquals(2, minimumMoves(new String[]{"...", ".X.", "..."}, 0, 0, 1, 2));
    }
}

