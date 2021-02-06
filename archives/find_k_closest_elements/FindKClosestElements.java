package find_k_closest_elements;

import _lib.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class FindKClosestElements {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;
        int[] expected = {1, 2, 3, 4};
        int[] output = IntArrays.fromList(solution.findClosestElements(arr, k, x));
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example2() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = -1;
        int[] expected = {1, 2, 3, 4};
        int[] output = IntArrays.fromList(solution.findClosestElements(arr, k, x));
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example3() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 3, x = 999;
        int[] expected = {3, 4, 5};
        int[] output = IntArrays.fromList(solution.findClosestElements(arr, k, x));
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example4() {
        int[] arr = {1, 2, 3, 4, 5, 7, 8, 9};
        int k = 4, x = 5;
        int[] expected = {3, 4, 5, 7};
        int[] output = IntArrays.fromList(solution.findClosestElements(arr, k, x));
        Assertions.assertArrayEquals(expected, output);
    }

    @Test
    void example5() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 2, x = 4;
        int[] expected = {3, 4};
        int[] output = IntArrays.fromList(solution.findClosestElements(arr, k, x));
        Assertions.assertArrayEquals(expected, output);
    }
}
