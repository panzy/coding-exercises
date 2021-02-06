package merge_intervals_56;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class MergeIntervals {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }

    @Test
    void example2() {
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] expected = {{1, 5}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }

    @Test
    void example3() {
        int[][] intervals = {{1, 4}};
        int[][] expected = {{1, 4}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }

    @Test
    void example4() {
        int[][] intervals = {{1, 4}, {5, 6}};
        int[][] expected = {{1, 4}, {5, 6}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }

    @Test
    void example5() {
        int[][] intervals = {{1, 4}, {0, 2}, {3, 5}};
        int[][] expected = {{0, 5}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }

    @Test
    void example6() {
        int[][] intervals = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        int[][] expected = {{1, 3}, {4, 7}};
        Assertions.assertArrayEquals(expected, solution.merge(intervals));
    }
}
