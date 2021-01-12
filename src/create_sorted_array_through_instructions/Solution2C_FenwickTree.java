package create_sorted_array_through_instructions;

import java.util.Arrays;

/**
 * Use Fenwick tree.
 *
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class Solution2C_FenwickTree {
    int[] A; // the Fenwick tree

    public int createSortedArray(int[] instructions) {
        final int MAX_NUMBER = 100_000;
        A = new int[1 + MAX_NUMBER]; // one-based indexing

        long cost = 0;

        Arrays.fill(A, 0);

        int insCnt = 0;
        for (int num : instructions) {

            // the costs of each of the two approach:
            // 1. shift less numbers left, or
            // 2. shift greater numbers right.
            int cost1 = sum(num - 1);
            int cost2 = insCnt - sum(num);

            cost += Math.min(cost1, cost2);
            increaseCounter(num);
            ++insCnt;
        }

        // "Since the answer may be large, return it modulo 109 + 7"
        return (int) (cost % (1_000_000_000 + 7));
    }

    private int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += A[i];
            i -= i & (-i); // move index to parent node
        }
        return sum;
    }

    void increaseCounter(int i) {
        while (i <= A.length) {
            ++A[i];
            i += i & (-i);
        }
    }
}