package two_sum_ii_input_array_is_sorted_167;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Iteration x binary search.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0, n = numbers.length; i + 1 < n; ++i) {
            int a = numbers[i];
            int b = target - a;
            if (b < a || b > numbers[n - 1]) continue;
            int j = Arrays.binarySearch(numbers, i + 1, n, b);
            if (j > 0)
                return new int[]{i + 1, j + 1};
        }
        return new int[]{-1, -1};
    }

    @Test
    void example1() {
        Assertions.assertArrayEquals(
                new int[]{1, 2},
                twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    @Test
    void example2() {
        Assertions.assertArrayEquals(
                new int[]{1, 3},
                twoSum(new int[]{2, 3, 4}, 6));
    }

    @Test
    void example3() {
        Assertions.assertArrayEquals(
                new int[]{1, 2},
                twoSum(new int[]{-1, 0}, -1));
    }
}
