package find_the_most_competitive_subsequence_1673;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class FindMostCompetitiveSubsequence {
    public int[] mostCompetitive(int[] nums, int k) {
        return new Solution2().mostCompetitive(nums, k);
    }

    @Test
    void example1() {
        Assertions.assertArrayEquals(new int[]{2, 6}, mostCompetitive(new int[]{3, 5, 2, 6}, 2));
    }

    @Test
    void example2() {
        Assertions.assertArrayEquals(new int[]{2, 3, 3, 4}, mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4));
    }

    @Test
    void example3() {
        Assertions.assertArrayEquals(new int[]{1, 3, 7}, mostCompetitive(new int[]{1, 4, 5, 3, 7}, 3));
    }

    @Test
    void example4() {
        Assertions.assertArrayEquals(new int[]{2, 2, 2, 2, 2}, mostCompetitive(new int[]{2, 2, 2, 2, 2}, 5));
    }

    @Test
    void example5() {
        Assertions.assertArrayEquals(new int[]{3, 8, 9}, mostCompetitive(new int[]{4, 10, 4, 3, 8, 9}, 3));
    }

    @Test
    void example6() throws IOException, ParseException {
        int[] nums = IntArrays.loadFromJsonFile("./src/find_the_most_competitive_subsequence_1673/test-case-84.json");
        int k = 50000;
        int[] expected = new int[k];
        for (int i = 0; i < k; ++i)
            expected[i] = i + 1;
        Assertions.assertArrayEquals(expected, mostCompetitive(nums, k));
    }

    @Test
    void example7() {
        Assertions.assertArrayEquals(new int[]{1, 2, 2}, mostCompetitive(new int[]{1, 2, 3, 2, 5, 6, 7}, 3));
    }

    @Test
    void example8() {
        Assertions.assertArrayEquals(new int[]{1, 2, 0}, mostCompetitive(new int[]{1, 2, 3, 2, 5, 6, 0}, 3));
    }

    @Test
    void example9() {
        int[] nums = new int[100_000];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = 100_000 - i;
        }
        int k = 50_000;
        int[] expected = new int[k];
        for (int i = 0; i < k; ++i)
            expected[i] = 50_000 - i;
        Assertions.assertArrayEquals(expected, mostCompetitive(nums, k));
    }
}
