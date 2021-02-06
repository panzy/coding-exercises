package game_of_life;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution2 solution = new Solution2();

    @Test
    void gameOfLife_example1() {
        int[][] board = new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        solution.gameOfLife(board);
        Assertions.assertArrayEquals(new int[]{0,0,0}, board[0]);
        Assertions.assertArrayEquals(new int[]{1,0,1}, board[1]);
        Assertions.assertArrayEquals(new int[]{0,1,1}, board[2]);
        Assertions.assertArrayEquals(new int[]{0,1,0}, board[3]);
    }

    @Test
    void gameOfLife_example2() {
        int[][] board = new int[][]{
                {1,1},
                {1,0},
        };
        solution.gameOfLife(board);
        Assertions.assertArrayEquals(new int[]{1,1}, board[0]);
        Assertions.assertArrayEquals(new int[]{1,1}, board[1]);
    }
}
