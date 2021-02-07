package triple_sum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Solved HackerRank problem: Triple Sum
 * https://www.hackerrank.com/challenges/triple-sum/problem
 *
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class Solution {
    static long triplets(int[] a, int[] b, int[] c) {
        a = deduplicate(a);
        c = deduplicate(c);

        Arrays.sort(a);
        Arrays.sort(c);

        HashSet<Integer> visitedQ = new HashSet<>();

        long ans = 0;
        for (int q : b) {
            if (visitedQ.contains(q))
                continue;
            visitedQ.add(q);

            long cntP = countLessThan(a, q + 1);
            if (cntP > 0) {
                long cntR = countLessThan(c, q + 1);
                ans += cntP * cntR;
            }
        }

        return ans;
    }

    private static int countLessThan(int[] a, int v) {
        int i = Arrays.binarySearch(a, v);
        return i >= 0 ? i : -(i + 1);
    }

    private static int[] deduplicate(int[] a) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : a) set.add(i);
        return set.stream().mapToInt(v -> v).toArray();
    }

    @Test
    void testCountLessThan() {
        Assertions.assertEquals(0, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 0));
        Assertions.assertEquals(1, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 1));
        Assertions.assertEquals(2, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 2));
        Assertions.assertEquals(3, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 3));
        Assertions.assertEquals(4, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 4));
        Assertions.assertEquals(5, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 5));
        Assertions.assertEquals(6, countLessThan(new int[]{0, 1, 2, 3, 4, 5}, 6));

        Assertions.assertEquals(0, countLessThan(new int[]{0, 2, 4, 6, 8}, 0));
        Assertions.assertEquals(1, countLessThan(new int[]{0, 2, 4, 6, 8}, 1));
        Assertions.assertEquals(1, countLessThan(new int[]{0, 2, 4, 6, 8}, 2));
        Assertions.assertEquals(2, countLessThan(new int[]{0, 2, 4, 6, 8}, 3));
        Assertions.assertEquals(2, countLessThan(new int[]{0, 2, 4, 6, 8}, 4));
        Assertions.assertEquals(3, countLessThan(new int[]{0, 2, 4, 6, 8}, 5));
        Assertions.assertEquals(3, countLessThan(new int[]{0, 2, 4, 6, 8}, 6));
        Assertions.assertEquals(4, countLessThan(new int[]{0, 2, 4, 6, 8}, 7));
        Assertions.assertEquals(4, countLessThan(new int[]{0, 2, 4, 6, 8}, 8));
        Assertions.assertEquals(5, countLessThan(new int[]{0, 2, 4, 6, 8}, 9));
    }

    @Test
    void example1() {
        Assertions.assertEquals(4, triplets(new int[]{3, 5, 7}, new int[]{3, 6}, new int[]{4, 6, 9}));
        Assertions.assertEquals(5, triplets(new int[]{1, 4, 5}, new int[]{2, 3, 3}, new int[]{1, 2, 3}));
        Assertions.assertEquals(4, triplets(new int[]{1, 2}, new int[]{4}, new int[]{1, 2}));
    }
}
