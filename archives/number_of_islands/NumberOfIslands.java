package number_of_islands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class NumberOfIslands {
    Solution solution = new Solution();

    @Test
    void example1() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int expected = 1;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }

    @Test
    void example2() {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int expected = 3;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }

    @Test
    void example3() {
        char[][] grid = {
                {'1','1','1','1','1'},
                {'1','1','0','0','1'},
                {'0','0','1','0','1'},
                {'0','0','0','1','1'}
        };
        int expected = 2;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }

    @Test
    void example4() {
        char[][] grid = {
                {'1','1','1','1','1'},
                {'1','1','0','0','1'},
                {'0','0','1','0','1'},
                {'0','0','1','1','1'}
        };
        int expected = 1;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }

    @Test
    void example5() {
        char[][] grid = {
                {'1','1','1','1','1'},
                {'1','1','0','0','1'},
                {'0','0','1','0','1'},
                {'1','0','1','1','1'}
        };
        int expected = 2;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }

    @Test
    void example6() {
        char[][] grid = {
                {'0','0','0','0','1'},
                {'0','0','1','1','0'},
                {'1','0','0','0','1'},
                {'0','1','0','1','1'}
        };
        int expected = 5;
        Assertions.assertEquals(expected, solution.numIslands(grid));
    }
}
