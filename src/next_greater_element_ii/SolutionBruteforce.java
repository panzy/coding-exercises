package next_greater_element_ii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A straightforward approach.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class SolutionBruteforce {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        int maxVal = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ++i) {
            if (maxVal == nums[i]) {
                ans[i] = -1;
                continue;
            }

            int j = i + 1;
            for (; j < i + nums.length; ++j) {
                int ej = j < nums.length ? nums[j] : nums[j - nums.length];
                if (ej > nums[i]) {
                    ans[i] = ej;
                    break;
                }
            }

            if (j == i + nums.length) {
                ans[i] = -1;
                maxVal = nums[i];
            }
        }

        return ans;
    }
}

class SolutionTest {
    SolutionBruteforce solution = new SolutionBruteforce();

    @Test
    void example1() {
        int[] nums = new int[]{1, 2, 1};
        int[] expected = new int[]{2, -1, 2};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }

    @Test
    void example2() {
        int[] nums = new int[]{1, 2, 4, 3};
        int[] expected = new int[]{2, 4, -1, 4};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }

    @Test
    void example3() {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int[] expected = new int[]{-1, -1, -1, -1, -1};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }
}
