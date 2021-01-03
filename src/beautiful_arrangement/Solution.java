package beautiful_arrangement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-03.
 */
public class Solution {
    public int countArrangement(int n) {
        // For a number i between [1, n],
        // its possible positions in the beautiful array is given by positions[i-1].
        // Each position is between [1, n].
        int[][] positions = new int[n][];
        for (int i = 0; i < n; ++i) {
            positions[i] = getPossiblePositions(i + 1, n);
        }

        return countArrangement(positions, 0, 0);
    }

    /**
     * Count the remaining arrangements at a given starting point.
     * @param positions it never changes for a given n.
     * @param from the starting number. all numbers less than |from| are considered placed.
     * @param occupied bits indicating whether a position in the beautiful array has been occupied.
     * @return the count
     */
    private int countArrangement(int[][] positions, int from, int occupied) {
        assert from <= positions.length;
        if (from == positions.length)
            return 0;

        int c = 0;
        for (int pos : positions[from]) {
            if (((occupied >> pos) & 1) == 0) { // this pos is not occupied
                // Place the number (which equals |from|+1) at this position.
                // If this is the last number then this placement contributes 1 to the total,
                // otherwise it contributes 1 x count-of-next.
                if (from + 1 == positions.length)
                    ++c;
                else
                    c += countArrangement(positions, from + 1, occupied | (1 << pos));
            }
        }
        return c;
    }

    /**
     * Which positions can number i go?
     * @param i the number to go.
     * @param n array length.
     * @return positions starting from 1.
     */
    private int[] getPossiblePositions(int i, int n) {
        int[] ans = new int[n];
        int len = 0;
        for (int p = 1; p <= n; ++p) {
            if (i % p  == 0 || p % i == 0) {
                ans[len] = p;
                ++len;
            }
        }
        return Arrays.copyOf(ans, len);
    }

    @Test
    void getPossiblePositions_1_2() {
        Assertions.assertArrayEquals(new int[]{1, 2}, getPossiblePositions(1, 2));
    }

    @Test
    void getPossiblePositions_2_2() {
        Assertions.assertArrayEquals(new int[]{1, 2}, getPossiblePositions(2, 2));
    }

    @Test
    void getPossiblePositions_1_15() {
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, getPossiblePositions(1, 15));
    }

    @Test
    void getPossiblePositions_2_15() {
        Assertions.assertArrayEquals(new int[]{1, 2, 4, 6, 8, 10, 12, 14}, getPossiblePositions(2, 15));
    }

    @Test
    void getPossiblePositions_3_15() {
        Assertions.assertArrayEquals(new int[]{1, 3, 6, 9, 12, 15}, getPossiblePositions(3, 15));
    }
}
