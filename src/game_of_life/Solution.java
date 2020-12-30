package game_of_life;

import java.util.Arrays;

/**
 * Generate the next state of a Game of Life board.
 *
 * This is a straightforward solution, using a snapshot of the current state.
 *
 * --
 * Zhiyong Pan, 2020-12-30
 */
public class Solution {
    int m;
    int n;
    int[][] snapshot;

    public void gameOfLife(int[][] board) {
        m = board.length;
        n = board[0].length;

        snapshot = new int[m][];
        for (int i = 0; i < m; ++i) {
            snapshot[i] = Arrays.copyOf(board[i], n);
        }

        for (int i = 0; i < m; ++i) {
            snapshot[i] = Arrays.copyOf(board[i], n);

            for (int j = 0; j < n; ++j) {
                int c = countNeighbours(i, j);
                if (c == 3) {
                    board[i][j] = 1; // either remain alive or resurrect
                } else if (c < 2 || c > 3) {
                    board[i][j] = 0; // die from under-population or over-population
                }
            }
        }
    }

    int countNeighbours(int i, int j) {
        int c = 0;

        if (i > 0) {
            if (j > 0) c += snapshot[i - 1][j - 1];
            c += snapshot[i - 1][j];
            if (j + 1 < n) c += snapshot[i - 1][j + 1];
        }

        if (j > 0) c += snapshot[i][j - 1];
        if (j + 1 < n) c += snapshot[i][j + 1];

        if (i + 1 < m) {
            if (j > 0) c += snapshot[i + 1][j - 1];
            c += snapshot[i + 1][j];
            if (j + 1 < n) c += snapshot[i + 1][j + 1];
        }

        return c;
    }
}
