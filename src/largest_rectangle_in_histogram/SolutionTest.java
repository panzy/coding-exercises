package largest_rectangle_in_histogram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SolutionTest {
    Solution3 solution = new Solution3();

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
    void largestRectangleArea_example4() {
        Assertions.assertEquals(4, solution.largestRectangleArea(new int[]{1, 2, 2}));
    }

    @Test
    void largestRectangleArea_example5() {
        Assertions.assertEquals(14, solution.largestRectangleArea(new int[]{7,0,0,4,2,3,3,1,8,3,0,2,1,4,7,8}));
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
        int[] hist = new int[]{1, 2, 3};
        Assertions.assertEquals(4, solution.largestRectangleArea(hist));
    }

    @Test
    void largestRectangleArea_monoIncreasing2() {
        int[] hist = new int[10000];
        for (int i = 0; i < hist.length; ++i)
            hist[i] = i;
        int a = solution.largestRectangleArea(hist);
        int expectedApproximation = hist.length * hist.length / 4;
        Assertions.assertEquals(expectedApproximation, a);
    }

    @Test
    void largestRectangleArea_largeN_monoIncreasing() {
        int[] hist = new int[1_000_000];
        for (int i = 0; i < hist.length; ++i)
            hist[i] = i;
        solution.largestRectangleArea(hist);
    }

    @Test
    void largestRectangleArea_largeN_monoDecreasing() {
        int[] hist = new int[1_000_000];
        for (int i = 0; i < hist.length; ++i)
            hist[hist.length - i - 1] = i;
        solution.largestRectangleArea(hist);
    }

    @Test
    void largestRectangleArea_largeN_zig() {
        int[] hist = new int[1_000_000];
        int min = 2;
        for (int i = 0; i < hist.length; i += 2) {
            hist[i] = 5;
            hist[i + 1] = min;
        }
        Assertions.assertEquals(hist.length * min, solution.largestRectangleArea(hist));
    }

    @Test
    void largestRectangleArea_largeN_random() {
        int[] hist = new int[10_000_000];
        for (int i = 0; i < hist.length; ++i) {
            hist[i] = (int) (Math.random() * 1_000_000);
        }
        solution.largestRectangleArea(hist);
    }
}
