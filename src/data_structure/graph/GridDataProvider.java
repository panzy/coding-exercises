package data_structure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-02-10.
 */
public class GridDataProvider implements GraphBFS.DataProvider {
    final static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int[][] grid;
    int m, n;
    int validCellValue;

    public GridDataProvider(int[][] grid, int validCellValue) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        this.validCellValue = validCellValue;
    }

    @Override
    public List<Integer> getNeighbours(int id) {
        int r = id >> 16;
        int c = id & 0xffff;
        List<Integer> neibs = new ArrayList<>(8);

        for (int[] d : delta) {
            int r2 = r + d[0];
            int c2 = c + d[1];
            if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n && grid[r2][c2] == validCellValue) {
                neibs.add((r2 << 16) + c2);
            }
        }
        return neibs;
    }
}
