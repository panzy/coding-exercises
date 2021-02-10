package data_structure.graph.examples;

import data_structure.graph.GraphBFS;
import data_structure.graph.GridDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Search the largest connected area in a grid.
 * <p>
 * DFS: Connected Cell in a Grid
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
 */
public class ConnectedCellInAGrid {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            visited[i] = new boolean[n];
        }

        HashMap<Integer, Integer> areas = new HashMap<>();

        for (int sr = 0; sr < m; ++sr) {
            for (int sc = 0; sc < n; ++sc) {
                if (visited[sr][sc] || grid[sr][sc] == 0)
                    continue;

                int start = (sr << 16) + sc;

                areas.put(start, 1);

                GraphBFS bfs = new GraphBFS(
                        new GridDataProvider(grid, 1),
                        (id, prevNode, originNode, currLayerIdx) -> {
                            int r = id >> 16;
                            int c = id & 0xffff;
                            if (visited[r][c]) {
                                return false;
                            } else {
                                visited[r][c] = true;
                                areas.put(start, areas.get(start) + 1);
                                return true;
                            }
                        }, start);

                while (!bfs.ended())
                    bfs.nextLayer();
            }
        }

        return areas.values().stream().max(Integer::compare).get();
    }

    @Test
    void test() {
        Assertions.assertEquals(5, maxRegion(new int[][]{
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0}
        }));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
