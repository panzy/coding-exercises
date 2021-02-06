package kth_missing_positive_number;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class KthMissingPositiveNumber {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        Assertions.assertEquals(9, solution.findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
    }

    @Test
    void example2() {
        Assertions.assertEquals(6, solution.findKthPositive(new int[]{1, 2, 3, 4}, 2));
    }

    @Test
    void test1() {
        Assertions.assertEquals(1, solution.findKthPositive(new int[]{2}, 1));
    }

    @Test
    void test2() {
        Assertions.assertEquals(3, solution.findKthPositive(new int[]{2}, 2));
    }

    @Test
    void test3() {
        Assertions.assertEquals(2, solution.findKthPositive(new int[]{3, 10}, 2));
    }

    @Test
    void emptyArrayButLargeK() {
        Assertions.assertEquals(12345679, solution.findKthPositive(new int[]{1}, 12345678));
    }

    @Test
    void largeGapInArray() {
        Assertions.assertEquals(1_000_002, solution.findKthPositive(new int[]{1, 1_000_000}, 1_000_000));
    }
}
