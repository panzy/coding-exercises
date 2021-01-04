package maximal_square;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-03.
 */
public class MaximalSquare {
    Solution solution = new Solution();

    @Test
    void example1() {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        Assertions.assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    void example2() {
        char[][] matrix = { };
        Assertions.assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    void example3() {
        char[][] matrix = {{'0'}};
        Assertions.assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    void example4() {
        char[][] matrix = {{'1'}};
        Assertions.assertEquals(1, solution.maximalSquare(matrix));
    }

    @Test
    void example5() {
        char[][] matrix = {{'0', '0'}};
        Assertions.assertEquals(0, solution.maximalSquare(matrix));
    }

    @Test
    void test6() {
        char[][] matrix = {
                {'0','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','0'}
        };
        Assertions.assertEquals(25, solution.maximalSquare(matrix));
    }

    /**
     * the horizontal edges of the maximal rectangle are not standing out.
     */
    @Test
    void test7() {
        char[][] matrix = {
                {'1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','0','0','0'},
                {'0','1','1','1','1','0','0','0'}
        };
        Assertions.assertEquals(16, solution.maximalSquare(matrix));
    }

    @Test
    void test61() {
        char[][] matrix = {
                {'0','0','1','0'},
                {'1','1','1','1'},
                {'1','1','1','1'},
                {'1','1','1','0'},
                {'1','1','0','0'},
                {'1','1','1','1'},
                {'1','1','1','0'}
        };
        Assertions.assertEquals(9, solution.maximalSquare(matrix));
    }

    @Test
    void test66_reduced() {
        char[][] matrix = {
                {'0','1','1','1','1','1','1','0','1','0'},
                {'0','0','1','1','1','1','1','1','1','0'},
        };
        Assertions.assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    void test66() {
        char[][] matrix = {
                {'0','1','1','0','0','1','0','1','0','1'},
                {'0','0','1','0','1','0','1','0','1','0'},
                {'1','0','0','0','0','1','0','1','1','0'},
                {'0','1','1','1','1','1','1','0','1','0'},
                {'0','0','1','1','1','1','1','1','1','0'},
                {'1','1','0','1','0','1','1','1','1','0'},
                {'0','0','0','1','1','0','0','0','1','0'},
                {'1','1','0','1','1','0','0','1','1','1'},
                {'0','1','0','1','1','0','1','0','1','1'}
        };
        Assertions.assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    void test67() {
        char[][] matrix = {
                {'1','0','1','1','0','1'},
                {'1','1','1','1','1','1'},
                {'0','1','1','0','1','1'},
                {'1','1','1','0','1','0'},
                {'0','1','1','1','1','1'},
                {'1','1','0','1','1','1'}
        };
        Assertions.assertEquals(4, solution.maximalSquare(matrix));
    }

    @Test
    void test69() throws IOException, ParseException {
        int[][] m = IntArrays.load2DFromJsonFile("./src/maximal_rectangle/test-case-69.json");
        int rows = m.length;
        int cols = m[0].length;
        char[][] matrix = new char[rows][];
        for (int i = 0; i < rows; ++i) {
            matrix[i] = new char[cols];
            for (int j = 0; j < cols; ++j) {
                matrix[i][j] = m[i][j] == 1 ? '1' : '0';
            }
        }

        Assertions.assertEquals(36, solution.maximalSquare(matrix));
    }
}
