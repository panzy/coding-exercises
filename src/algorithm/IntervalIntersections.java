package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a set of intervals, how to find the maximum number of intersections among them.
 * https://stackoverflow.com/questions/35739542/given-a-set-of-intervals-how-to-find-the-maximum-number-of-intersections-among
 *
 * See also https://www.hackerrank.com/challenges/crush/problem
 *
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class IntervalIntersections {
    static long arrayManipulation(int n, int[][] queries) {
        int[] endPoints = new int[n + 1];

        for (int[] q : queries) {
            // This tuple describes an interval.
            int a = q[0] - 1; // begin
            int b = q[1]; // end
            int k = q[2]; // weight

            endPoints[a] += k;
            endPoints[b] -= k;
        }

        long sum = 0;
        long maxSum = 0;
        for (int p : endPoints) {
            sum += p;
            if (maxSum < sum)
                maxSum = sum;
        }

        return maxSum;
    }

    @Test
    void example1() {
        Assertions.assertEquals(10, arrayManipulation(10, new int[][]{
                {1, 5, 3}, {4, 8, 7}, {6, 9, 1}
        }));
    }
}
