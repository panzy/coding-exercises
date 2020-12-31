package largest_rectangle_in_histogram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void largestRectangleArea_empty() {
        Assertions.assertEquals(0, solution.largestRectangleArea(new int[0]));
    }

    @Test
    void largestRectangleArea_zero() {
        Assertions.assertEquals(0, solution.largestRectangleArea(new int[]{0}));
    }

    @Test
    void largestRectangleArea_single() {
        Assertions.assertEquals(1, solution.largestRectangleArea(new int[]{1}));
    }

    @Test
    void largestRectangleArea_zig() {
        Assertions.assertEquals(5, solution.largestRectangleArea(new int[]{4, 0, 4, 0, 5}));
    }

    @Test
    void largestRectangleArea_example1() {
        Assertions.assertEquals(10, solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void largestRectangleArea_example2() {
        Assertions.assertEquals(3, solution.largestRectangleArea(new int[]{2, 1, 2}));
    }

    @Test
    void largestRectangleArea_example3() {
        Assertions.assertEquals(2147483647, solution.largestRectangleArea(new int[]{2147483647, 0, 2147483647, 0, 2147483647, 0, 2147483647, 0, 2147483647, 0}));
    }

    @Test
    void largestRectangleArea_largeN_allZero() {
        int[] hist = new int[1_000_000];
        Arrays.fill(hist, 0);
        Assertions.assertEquals(0, solution.largestRectangleArea(hist));
    }

    @Test
    void largestRectangleArea_largeN_allTheSame() {
        int[] hist = new int[1_000_000];
        Arrays.fill(hist, 8);
        Assertions.assertEquals(8_000_000, solution.largestRectangleArea(hist));
    }

    @Test
    void largestRectangleArea_monoIncreasing() {
        int[] hist = new int[10000];
        for (int i = 0; i < hist.length; ++i)
            hist[i] = i;
        int a = solution.largestRectangleArea(hist);
        int expectedApproximation = hist.length * hist.length / 4;
        Assertions.assertEquals(expectedApproximation, a);
    }

    @Test
    void largestRectangleArea_largeN_monoIncreasing() {
        int[] hist = new int[10_000];
        for (int i = 0; i < hist.length; ++i)
            hist[i] = i;
        solution.largestRectangleArea(hist);
    }

    @Test
    void largestRectangleArea_largeN_monoDecreasing() {
        int[] hist = new int[10_000];
        for (int i = 0; i < hist.length; ++i)
            hist[hist.length - i - 1] = i;
        solution.largestRectangleArea(hist);
    }
}
