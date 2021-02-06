/**
 * Zhiyong Pan, 2020-12-25
 */
package diagonal_traverse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution s = new Solution();

    @Test
    void findDiagonalOrder_null() {
        int[][] matrix = null;
        int[] expected = {};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_empty() {
        int[][] matrix = new int[][]{ };
        int[] expected = {};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_empty2() {
        int[][] matrix = new int[][]{ {} };
        int[] expected = {};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_singleElement() {
        int[][] matrix = new int[][]{
                {1},
        };
        int[] expected = {1};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_square_oddSize() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] expected = {1, 2, 4, 7, 5, 3, 6, 8, 9};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_square_evenSize() {
        int[][] matrix = new int[][]{
                {1, 2},
                {3, 4},
        };
        int[] expected = {1, 2, 3, 4};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_verticalRect_oddRows() {
        int[][] matrix = new int[][]{
                {1, 2},
                {4, 5},
                {7, 8}
        };
        int[] expected = {1, 2, 4, 7, 5, 8};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_verticalRect_evenRows() {
        int[][] matrix = new int[][]{
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8}
        };
        int[] expected = {1, 2, 3, 5, 4, 6, 7, 8};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_horizontalRect_oddCols() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
        };
        int[] expected = {1, 2, 4, 5, 3, 6};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_horizontalRect_evenCols() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
        };
        int[] expected = {1, 2, 5, 6, 3, 4, 7, 8};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_verticalLine() {
        int[][] matrix = new int[][]{
                {1},
                {2},
                {3}
        };
        int[] expected = {1, 2, 3};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }

    @Test
    void findDiagonalOrder_horizontalLine() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
        };
        int[] expected = {1, 2, 3};
        Assertions.assertArrayEquals(expected, s.findDiagonalOrder(matrix));
    }
}
