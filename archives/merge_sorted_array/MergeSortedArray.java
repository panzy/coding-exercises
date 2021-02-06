package merge_sorted_array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class MergeSortedArray {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }

    @Test
    void example2() {
        int[] nums1 = {1};
        int m = 1;
        int[] nums2 = {};
        int n = 0;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{1}, nums1);
    }

    @Test
    void example3() {
        int[] nums1 = {1, 2, 3, 0};
        int m = 3;
        int[] nums2 = {0};
        int n = 1;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{0, 1, 2, 3}, nums1);
    }

    @Test
    void example4() {
        int[] nums1 = {0, 0, 0, 0};
        int m = 0;
        int[] nums2 = {1, 2, 3, 4};
        int n = 4;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4}, nums1);
    }

    @Test
    void example5() {
        int[] nums1 = {1, 2, 3, 0};
        int m = 3;
        int[] nums2 = {4};
        int n = 1;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4}, nums1);
    }

    @Test
    void example6() {
        int[] nums1 = {1, 0};
        int m = 1;
        int[] nums2 = {2};
        int n = 1;
        solution.merge(nums1, m, nums2, n);
        Assertions.assertArrayEquals(new int[]{1, 2}, nums1);
    }
}
