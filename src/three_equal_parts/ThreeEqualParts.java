package three_equal_parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-12.
 */
public class ThreeEqualParts {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] a = {1, 0, 1, 0, 1};
        Assertions.assertArrayEquals(new int[]{0, 3}, solution.threeEqualParts(a));
    }

    @Test
    void example2() {
        int[] a = {1, 1, 0, 1, 1};
        Assertions.assertArrayEquals(new int[]{-1, -1}, solution.threeEqualParts(a));
    }

    @Test
    void example3() {
        int[] a = {
                1, 0, 1,
                1, 0, 1,
                1, 0, 1
        };
        Assertions.assertArrayEquals(new int[]{2, 6}, solution.threeEqualParts(a));
    }

    @Test
    void example4() {
        int[] a = {
                1, 0, 1,
                0, 0, 0, 1, 0, 1,
                0, 0, 0, 1, 0, 1
        };
        Assertions.assertArrayEquals(new int[]{2, 9}, solution.threeEqualParts(a));
    }

    @Test
    void example5() {
        int[] a = {
                1, 0, 0, 0,
                1, 0, 0, 0,
                1, 0, 0, 0
        };
        Assertions.assertArrayEquals(new int[]{3, 8}, solution.threeEqualParts(a));
    }

    @Test
    void example6() {
        int[] a = {
                1, 0, 0, 0, 1, 0, 0, 0,
                1, 0, 0, 0, 1, 0, 0, 0,
                1, 0, 0, 0, 1, 0, 0, 0
        };
        Assertions.assertArrayEquals(new int[]{7, 16}, solution.threeEqualParts(a));
    }

    @Test
    void example7() {
        int[] a = {
                   1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
                0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1,
                0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1
        };
        Assertions.assertArrayEquals(new int[]{9, 21}, solution.threeEqualParts(a));
    }
}
