package check_array_formation_through_concatenation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class SolutionTest {
    Solution2 solution = new Solution2();

    @Test
    void canFormArray_example1() {
        int[] arr = new int[]{85};
        int[][] pieces = new int[][]{{85}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example2() {
        int[] arr = new int[]{15, 88};
        int[][] pieces = new int[][]{{88}, {15}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example3() {
        int[] arr = new int[]{49, 18, 16};
        int[][] pieces = new int[][]{{16, 18, 49}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example4() {
        int[] arr = new int[]{91, 4, 64, 78};
        int[][] pieces = new int[][]{{78}, {4, 64}, {91}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91}, {4}, {64}, {78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91, 4}, {64}, {78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{4, 91}, {64}, {78}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91, 4, 64}, {78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{91, 64, 4}, {78}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{64, 91, 4}, {78}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91, 4, 64, 78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91}, {4, 64}, {78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{91}, {64, 4}, {78}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91}, {4, 64, 78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{91}, {4, 78, 64}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));
        pieces = new int[][]{{91}, {78, 4, 64}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));

        pieces = new int[][]{{91}, {4}, {64, 78}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example5() {
        int[] arr = new int[]{1, 3, 5, 7};
        int[][] pieces = new int[][]{{2, 4, 6, 8}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example6() {
        int[] arr = new int[]{49, 18, 16};
        int[][] pieces = new int[][]{{49, 16, 18}};
        Assertions.assertFalse(solution.canFormArray(arr, pieces));
    }

    @Test
    void canFormArray_example7() {
        int[] arr = new int[]{32, 66, 73, 15, 3, 70, 53};
        int[][] pieces = new int[][]{{66, 73}, {15}, {3}, {32}, {70}, {53}};
        Assertions.assertTrue(solution.canFormArray(arr, pieces));
    }
}
