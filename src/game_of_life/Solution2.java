package game_of_life;

/**
 * Generate the next state of a Game of Life board.
 *
 * Instead of using a snapshot of the current state, this implementation stores the new state in some particular bits
 * of the current board's cell values.
 *
 * For a cell value x:
 * 1. (x & 1) is the original meaning of x (0=dead, 1=live).
 * 2. (x & 2) == 1 means it's live in the next state.
 * 3. (x & 4) == 1 means it's dead in the next state.
 *
 * --
 * Zhiyong Pan, 2020-12-30
 */
public class Solution2 {
    int m;
    int n;

    public void gameOfLife(int[][] board) {
        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int c = countNeighbours(i, j, board);
                if (c == 3) {
                    board[i][j] |= 2; // either remain alive or resurrect
                } else if (c < 2 || c > 3) {
                    board[i][j] |= 4; // die from under-population or over-population
                }
            }
        }

        // convert cell values
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((board[i][j] & 2) != 0)
                    board[i][j] = 1;
                else if ((board[i][j] & 4) != 0)
                    board[i][j] = 0;
            }
        }
    }

    int countNeighbours(int i, int j, int[][] board) {
        int c = 0;

        if (i > 0) {
            if (j > 0) c += board[i - 1][j - 1] & 1;
            c += board[i - 1][j] & 1;
            if (j + 1 < n) c += board[i - 1][j + 1] & 1;
        }

        if (j > 0) c += board[i][j - 1] & 1;
        if (j + 1 < n) c += board[i][j + 1] & 1;

        if (i + 1 < m) {
            if (j > 0) c += board[i + 1][j - 1] & 1;
            c += board[i + 1][j] & 1;
            if (j + 1 < n) c += board[i + 1][j + 1] & 1;
        }

        return c;
    }
}
