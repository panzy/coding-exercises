package next_greater_element_i;

import _lib.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A straightforward approach.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class SolutionBruteforce {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            int j = IntArrays.indexOf(nums2, nums1[i]) + 1;
            while (j < nums2.length && nums2[j] <= nums1[i]) ++j;
            ans[i] = j < nums2.length ? nums2[j] : -1;
        }
        return ans;
    }
}

class SolutionBruteforceTest {
    SolutionBruteforce solution = new SolutionBruteforce();

    @Test
    void example1() {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] expected = new int[]{-1, 3, -1};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void example2() {
        int[] nums1 = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};
        int[] expected = new int[]{3, -1};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
    }
}
