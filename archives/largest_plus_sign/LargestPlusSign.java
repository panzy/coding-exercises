package largest_plus_sign;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class LargestPlusSign {
    Solution solution = new Solution();

    @Test
    void order1_A() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0},         {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_B() {
        int[][] mines = {
                        {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_C() {
        int[][] mines = {
                {0, 0},         {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_D() {
        int[][] mines = {
                {0, 0}, {0, 1},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_E() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_F() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_G() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0},         {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_H() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                        {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order1_I() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2},
                        {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2},
        };
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order2() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {1, 0}, {1, 1},         {1, 3}, {1, 4},
                {2, 0},                         {2, 4},
                {3, 0}, {3, 1},         {3, 3}, {3, 4},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4},
        };
        int N = 5;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void order3() {
        int[][] mines = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6},
                {1, 0}, {1, 1}, {1, 2},         {1, 4}, {1, 5}, {1, 6},
                {2, 0}, {2, 1}, {2, 2},         {2, 4}, {2, 5}, {2, 6},
                {3, 0},                                         {3, 6},
                {4, 0}, {4, 1}, {4, 2},         {4, 4}, {4, 5}, {4, 6},
                {5, 0}, {5, 1}, {5, 2},         {5, 4}, {5, 5}, {5, 6},
                {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6},
        };
        int N = 7;
        int expected = 3;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example1() {
        int[][] mines = {{4, 2}};
        int N = 5;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example2() {
        int[][] mines = {};
        int N = 2;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example3() {
        int[][] mines = {{0, 0}};
        int N = 1;
        int expected = 0;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_A() {
        int[][] mines = {{0, 0}};
        int N = 3;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_B() {
        int[][] mines = {{0, 1}};
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_C() {
        int[][] mines = {{0, 2}};
        int N = 3;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_D() {
        int[][] mines = {{1, 0}};
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_E() {
        int[][] mines = {{1, 1}};
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_F() {
        int[][] mines = {{2, 1}};
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_G() {
        int[][] mines = {{2, 0}};
        int N = 3;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_H() {
        int[][] mines = {{2, 1}};
        int N = 3;
        int expected = 1;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }

    @Test
    void example4_I() {
        int[][] mines = {{2, 2}};
        int N = 3;
        int expected = 2;
        Assertions.assertEquals(expected, solution.orderOfLargestPlusSign(N, mines));
    }
}
