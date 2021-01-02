package perfect_rectangle;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-02.
 */
public class PerfectRectangle {
    SolutionFailure2 solution = new SolutionFailure2();

    @Test
    void example1() {
        int[][] rectangles = new int[][]{
                {1,1,3,3},
                {3,1,4,2},
                {3,2,4,4},
                {1,3,2,4},
                {2,3,3,4}
        };
        Assertions.assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    void example2() {
        int[][] rectangles = new int[][]{
                {1,1,2,3},
                {1,3,2,4},
                {3,1,4,2},
                {3,2,4,4}
        };
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void example3() {
        int[][] rectangles = new int[][]{
                {1,1,3,3},
                {3,1,4,2},
                {1,3,2,4},
                {3,2,4,4}
        };
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void example4() {
        int[][] rectangles = new int[][]{
                {1,1,3,3},
                {3,1,4,2},
                {1,3,2,4},
                {2,2,4,4}
        };
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void example5() {
        int[][] rectangles = new int[][]{
                {0, 0, 2, 1},
                {0, 1, 2, 2},
                {0, 2, 1, 3},
                {1, 0, 2, 1}
        };
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void example6() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJson(
                "[[0,0,4,1],[7,0,8,2],[6,2,8,3],[5,1,6,3],[4,0,5,1],[6,0,7,2],[4,2,5,3],[2,1,4,3],[0,1,2,2],[0,2,2,3],[4,1,5,2],[5,0,6,1]]");
        Assertions.assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    void largeN_3982() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJsonFile("src/perfect_rectangle/huge-test-case.json");
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void largeN_10000() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJsonFile("src/perfect_rectangle/huge-test-case2.json");
        Assertions.assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    void largeN_10779() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJsonFile("src/perfect_rectangle/huge-test-case3.json");
        Assertions.assertFalse(solution.isRectangleCover(rectangles));
    }

    @Test
    void largeN_11000() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJsonFile("src/perfect_rectangle/huge-test-case4.json");
        Assertions.assertTrue(solution.isRectangleCover(rectangles));
    }

    @Test
    void largeN_3892() throws IOException, ParseException {
        int[][] rectangles = IntArrays.load2DFromJsonFile("src/perfect_rectangle/huge-test-case5.json");
        Assertions.assertTrue(solution.isRectangleCover(rectangles));
    }
}
