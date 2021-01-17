package find_k_pairs_with_smallest_sums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhiyong Pan on 2021-01-16.
 */
public class FindKPairsWithSmallestSums {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> output = solution.kSmallestPairs(nums1, nums2, k);
        Assertions.assertEquals(k, output.size());

        String line = output.stream().map(l -> l.toString()).collect(Collectors.joining(","));
        Assertions.assertTrue(line.indexOf("[1, 2]") != -1);
        Assertions.assertTrue(line.indexOf("[1, 4]") != -1);
        Assertions.assertTrue(line.indexOf("[1, 6]") != -1);
    }

    @Test
    void example2() {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {1, 2, 3};
        int k = 2;
        List<List<Integer>> output = solution.kSmallestPairs(nums1, nums2, k);
        Assertions.assertEquals(k, output.size());

        String line = output.stream().map(l -> l.toString()).collect(Collectors.joining(","));
        Assertions.assertTrue(line.indexOf("[1, 1],[1, 1]") != -1);
    }

    @Test
    void example3() {
        int[] nums1 = {1, 2};
        int[] nums2 = {3};
        int k = 3;
        List<List<Integer>> output = solution.kSmallestPairs(nums1, nums2, k);
        Assertions.assertEquals(2, output.size());

        String line = output.stream().map(l -> l.toString()).collect(Collectors.joining(","));
        Assertions.assertTrue(line.indexOf("[1, 3]") != -1);
        Assertions.assertTrue(line.indexOf("[2, 3]") != -1);
    }

    @Test
    void example4() {
        int[] nums1 = {100, 101, 102};
        int[] nums2 = {2, 3, 4};
        int k = 3;
        List<List<Integer>> output = solution.kSmallestPairs(nums1, nums2, k);
        Assertions.assertEquals(k, output.size());

        String line = output.stream().map(l -> l.toString()).collect(Collectors.joining(","));
        Assertions.assertTrue(line.indexOf("[100, 2]") != -1);
        Assertions.assertTrue(line.indexOf("[100, 3]") != -1);
        Assertions.assertTrue(line.indexOf("[101, 2]") != -1);
    }
}
