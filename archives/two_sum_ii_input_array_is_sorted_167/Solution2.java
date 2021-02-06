package two_sum_ii_input_array_is_sorted_167;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Two pointers.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        for (int n = numbers.length, i = 0, j = n - 1; i < j;) {
            int sum = numbers[i] + numbers[j];
            if (sum == target)
                return new int[]{i + 1, j + 1};
            else if (sum < target)
                ++i;
            else
                --j;
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
