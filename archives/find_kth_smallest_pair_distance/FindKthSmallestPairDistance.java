package find_kth_smallest_pair_distance;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-15.
 */
public class FindKthSmallestPairDistance {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        int[] nums = {1, 3, 1};
        Assertions.assertEquals(0, solution.smallestDistancePair(nums, 1));
    }

    @Test
    void example2() {
        int[] nums = {1, 3, 1};
        Assertions.assertEquals(2, solution.smallestDistancePair(nums, 2));
    }

    @Test
    void example3() {
        int[] nums = {1, 3, 9, 17};
        Assertions.assertEquals(6, solution.smallestDistancePair(nums, 2));
    }

    @Test
    void example4() {
        int[] nums = {1, 3};
        Assertions.assertEquals(2, solution.smallestDistancePair(nums, 1));
    }

    @Test
    void example5() {
        int[] nums = {1, 1, 1}; // duplicates
        Assertions.assertEquals(0, solution.smallestDistancePair(nums, 1));
        Assertions.assertEquals(0, solution.smallestDistancePair(nums, 2));
    }

    @Test
    void example6() {
        int[] nums = {1, 3, 5}; // duplicates
        Assertions.assertEquals(2, solution.smallestDistancePair(nums, 1));
        Assertions.assertEquals(2, solution.smallestDistancePair(nums, 2));
    }

    @Test
    void example7() {
        int[] nums = {1, 6, 1};
        Assertions.assertEquals(0, solution.smallestDistancePair(nums, 1));
        Assertions.assertEquals(5, solution.smallestDistancePair(nums, 2));
        Assertions.assertEquals(5, solution.smallestDistancePair(nums, 3));
    }

    @Test
    void example8() {
        int[] nums = {62, 100, 4};
        Assertions.assertEquals(58, solution.smallestDistancePair(nums, 2));
    }

    @Test
    void example9() {
        int[] nums = {9, 10, 7, 10, 6, 1, 5, 4, 9, 8};
        Assertions.assertEquals(2, solution.smallestDistancePair(nums, 18));
    }

    @Test
    void largeN() throws IOException, ParseException {
        int[] nums = IntArrays.loadFromJsonFile("./src/find_kth_smallest_pair_distance/test-case-10000.json"); // N=10_000
        Assertions.assertEquals(0, solution.smallestDistancePair(nums, 2_500_000));
    }
}
