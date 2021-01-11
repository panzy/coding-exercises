package create_sorted_array_through_instructions;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class CreateSortedArray {
    Solution2B solution = new Solution2B();

    @Test
    void example1() {
        Assertions.assertEquals(1, solution.createSortedArray(new int[]{1, 5, 6, 2}));
    }

    @Test
    void example2() {
        Assertions.assertEquals(3, solution.createSortedArray(new int[]{1, 2, 3, 6, 5, 4}));
    }

    @Test
    void example3_A() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{1, 1}));
    }

    @Test
    void example3_B() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{1, 3, 3}));
    }

    @Test
    void example3_C() {
        Assertions.assertEquals(1, solution.createSortedArray(new int[]{1, 3, 3, 2}));
    }

    @Test
    void example3_D() {
        Assertions.assertEquals(1, solution.createSortedArray(new int[]{1, 3, 3, 2, 4}));
    }

    @Test
    void example3_E() {
        Assertions.assertEquals(2, solution.createSortedArray(new int[]{1, 3, 3, 2, 4, 2}));
    }

    @Test
    void example3_F() {
        Assertions.assertEquals(2, solution.createSortedArray(new int[]{1, 3, 3, 2, 4, 2, 1}));
    }

    @Test
    void example3() {
        Assertions.assertEquals(4, solution.createSortedArray(new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2}));
    }

    @Test
    void example4() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{}));
    }

    @Test
    void example5() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{1}));
    }

    @Test
    void example6() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    void example7() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }

    @Test
    void example8() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    void example9() {
        Assertions.assertEquals(0, solution.createSortedArray(new int[]{9, 100_000, 8, 7, 5, 4, 3, 2, 1}));
    }

    @Test
    void test_largeN() throws IOException, ParseException {
        // N = 10^5
        int[] instructions = IntArrays.loadFromJsonFile(
                "./src/create_sorted_array_through_instructions/test-case-100000.json");
        Assertions.assertEquals(188426454, solution.createSortedArray(instructions));
    }

    @Test
    void test_largeN_B() throws IOException, ParseException {
        // N = 10^5
        int[] instructions = IntArrays.loadFromJsonFile(
                "./src/create_sorted_array_through_instructions/test-case-100000B.json");
        Assertions.assertEquals(188426454, solution.createSortedArray(instructions));
    }
}