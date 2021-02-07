package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;

/**
 * The theory of counting minimum swaps to sort an array of distinct values.
 *
 * Notice that if the array contains duplicated values, it might require less swaps.
 *
 * See also:
 * Lily's Homework
 * https://www.hackerrank.com/challenges/lilys-homework/problem
 *
 * Created by Zhiyong Pan on 2021-02-05.
 */
public class MinimumSwapsToSortDistinctElements {
    static int lilysHomework(int[] arr) {

        // sorted array.
        int[] A = Arrays.copyOf(arr, arr.length);
        Arrays.sort(A);

        // key = number
        // value = it's destination index in the sorted array.
        HashMap<Integer, Integer> dest = collectDestinations(A);

        // Reverse the sorted array and get another set of destinations.
        reverse(A);
        HashMap<Integer, Integer> dest2 = collectDestinations(A);

        // Which order (asc vs dsc) is more effortless to archive?
        return Math.min(calculateSwaps(arr, dest), calculateSwaps(arr, dest2));
    }

    private static void reverse(int[] A) {
        for (int i = 0, j = A.length - 1; i < j; ++i, --j) {
            int t = A[i]; A[i] = A[j]; A[j] = t;
        }
    }

    private static int calculateSwaps(int[] arr, HashMap<Integer, Integer> dest) {
        // Swap times.
        int swaps = 0;

        // Mark a arr position has been visited.
        BitSet visited = new BitSet(arr.length);

        for (int i = 0; i < arr.length; ++i) {
            if (visited.get(i))
                continue;
            visited.set(i);

            // Need to swap?
            int jumpTo = dest.get(arr[i]);
            while (i != jumpTo) {
                visited.set(jumpTo);
                ++swaps;
                jumpTo = dest.get(arr[jumpTo]);
            }
        }

        return swaps;
    }

    private static HashMap<Integer, Integer> collectDestinations(int[] A) {
        HashMap<Integer, Integer> dest = new HashMap<>();

        for (int i = 0; i < A.length; ++i) {
            dest.put(A[i], i);
        }

        return dest;
    }

    @Test
    void example1() {
        Assertions.assertEquals(2, lilysHomework(new int[]{2, 5, 3, 1}));
    }

    @Test
    void example2() {
        Assertions.assertEquals(0, lilysHomework(new int[]{1, 2, 3, 5}));
    }

    @Test
    void example3() {
        Assertions.assertEquals(3, lilysHomework(new int[]{2, 5, 1, 3}));
    }

    @Test
    void example4() {
        Assertions.assertEquals(3, lilysHomework(new int[]{2, 5, 6, 1, 3}));
    }

    @Test
    void example5() {
        Assertions.assertEquals(2, lilysHomework(new int[]{3, 4, 2, 5, 1}));
    }
}
