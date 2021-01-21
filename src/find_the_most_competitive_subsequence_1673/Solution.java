package find_the_most_competitive_subsequence_1673;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Brute force. Time limit exceeded.
 *
 * The inefficient part was for each of k elements, I had to call indexOfMinimum(from, to).
 *
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class Solution {
    int[] nums;

    public int[] mostCompetitive(int[] nums, int k) {

        this.nums = nums;
        int n = nums.length;

        int[] ans = new int[k];
        int ansIdx = 0;

        // Pick each element of the most competitive subsequence.
        while (ansIdx < k) {
            ans[ansIdx] = indexOfMinimum(
                    ansIdx > 0 ? ans[ansIdx - 1] + 1 : 0, n - (k - (ansIdx + 1)));
            ++ansIdx;
        }

        // map indices in ans to numbers
        for (int i = 0; i < ans.length; ++i)
            ans[i] = nums[ans[i]];
        return ans;
    }

    private int minIndices(int i, int j) {
        return nums[i] > nums[j] ? j : i;
    }

    private int indexOfMinimum(int from, int to) {
        int ans = from;

        for (int i = from + 1; i < to; ++i) {
            ans = minIndices(ans, i);
        }

        return ans;
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
        int[] nums = IntArrays.loadFromJsonFile("./src/find_the_most_competitive_subsequence/test-case-84.json");
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
}
