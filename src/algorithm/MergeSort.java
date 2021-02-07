package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * In this Merge Sort implementation, inversions are counted.
 *
 * See also HackerRank problem:
 * Merge Sort: Counting Inversions
 * https://www.hackerrank.com/challenges/ctci-merge-sort/problem
 *
 * This HackerRank problem itself doesn't require sorting, but it turns out, as its name suggests, Merge Sort is the
 * efficient approach to count the inversions, where inversion is defined as:
 *      i < j and arr[i] > arr[j]
 *
 * From this definition, it looks like a sorted set can help, because a sorted set can tell us
 * how many distinct elements are there that are smaller than a given value:
 *      TreeSet.subSet(fromElement, toElement).size()
 *
 * It's not hard to accommodate duplicates there (like wrapping each value in a different object),
 * but TreeSet approach exceeded time limit. Maybe subSet() is expensive.
 *
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class MergeSort {
    // O(n) space for Merge Sort.
    static int[] tmpArr;

    static long countInversions(int[] arr) {
        int n = arr.length;
        tmpArr = new int[arr.length];
        return mergeSort(arr, 0, n);
    }

    private static long mergeSort(int[] arr, int begin, int end) {
        long inversions = 0;

        if (begin + 1 >= end) {
            // nothing to do
        } else if (begin + 2 == end) {
            if (arr[begin] > arr[begin + 1]) {
                inversions++;
                swap(arr, begin, begin + 1);
            }
        } else {
            int mi = (begin + end) / 2;
            inversions += mergeSort(arr, begin, mi);
            inversions += mergeSort(arr, mi, end);

            // Merge the two parts.
            int i = 0, j = 0;
            while (begin + i < mi && mi + j < end && i + j < end - begin) {
                if (arr[begin + i] <= arr[mi + j]) {
                    tmpArr[i + j] = arr[begin + i];
                    i++;
                } else {
                    // Each of the remaining element from the left part forms
                    // an inversion with this j-th element from the right part.
                    inversions += mi - (begin + i);
                    tmpArr[i + j] = arr[mi + j];
                    ++j;
                }
            }
            while (begin + i < mi) {
                tmpArr[i + j] = arr[begin + i];
                i++;
            }
            while (mi + j < end) {
                tmpArr[i + j] = arr[mi + j];
                j++;
            }

            for (i = 0; begin + i < end; ++i) {
                arr[begin + i] = tmpArr[i];
            }
        }

        return inversions;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    void example1() {
        tmpArr = new int[100];
        Assertions.assertEquals(0, mergeSort(new int[]{1, 2}, 0, 2));
        Assertions.assertEquals(1, mergeSort(new int[]{2, 1}, 0, 2));
        Assertions.assertEquals(2, mergeSort(new int[]{3, 1, 2}, 0, 3));
        Assertions.assertEquals(1, mergeSort(new int[]{1, 2, 1, 2, 3}, 0, 5));
        Assertions.assertEquals(4, mergeSort(new int[]{2, 1, 3, 1, 2}, 0, 5));

        Assertions.assertEquals(6, countInversions(new int[]{7, 5, 3, 1}));
        Assertions.assertEquals(2, countInversions(new int[]{2, 4, 1}));
        Assertions.assertEquals(4, countInversions(new int[]{2, 1, 3, 1, 2}));
    }
}
