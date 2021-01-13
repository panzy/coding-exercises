package max_area_of_island;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class MaxAreaOfIsland {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int expected = 9;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example2() {
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,1,0,0},
                {0,0,0,1,1}
        };
        int expected = 4;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example3() {
        int[][] grid = {
                {1,1,1,1,1},
                {1,1,0,0,1},
                {0,0,1,0,1},
                {0,0,0,1,1}
        };
        int expected = 11;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example4() {
        int[][] grid = {
                {1,1,1,1,1},
                {1,1,0,0,1},
                {0,0,1,0,1},
                {0,0,1,1,1}
        };
        int expected = 13;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example5() {
        int[][] grid = {
                {1,1,1,1,1},
                {1,1,0,0,1},
                {0,0,1,0,1},
                {1,0,1,1,1}
        };
        int expected = 13;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example6() {
        int[][] grid = {
                {0,0,0,0,1},
                {0,0,1,1,0},
                {1,0,0,0,1},
                {0,1,0,1,1}
        };
        int expected = 3;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example7() {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        int expected = 6;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example8() {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        int expected = 6;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }

    @Test
    void example9() {
        int[][] grid = {
                {0,0,0,0,0,0,0,0,0,0,0,0,0},
        };
        int expected = 0;
        Assertions.assertEquals(expected, solution.maxAreaOfIsland(grid));
    }
}
