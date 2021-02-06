package largest_submatrix_with_rearrangements;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class LargestSubmatrixWithRearrangements {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[][] matrix = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
        Assertions.assertEquals(4, solution.largestSubmatrix(matrix));
    }

    @Test
    void example2() {
        int[][] matrix = {{1, 0, 1, 0, 1}};
        Assertions.assertEquals(3, solution.largestSubmatrix(matrix));
    }

    @Test
    void example3() {
        int[][] matrix = {{1, 1, 0}, {1, 0, 1}};
        Assertions.assertEquals(2, solution.largestSubmatrix(matrix));
    }

    @Test
    void example4() {
        int[][] matrix = {{0, 0}, {0, 0}};
        Assertions.assertEquals(0, solution.largestSubmatrix(matrix));
    }
}
