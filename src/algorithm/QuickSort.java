package algorithm;

import util.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */
public class QuickSort {
    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }

    private static void sort(int[] a, int begin, int end) {
        if (begin + 1 >= end)
            return;

        // Pick median as pivot.
        int p = (begin + end) / 2;
        int i = p - 1, j = p + 1;

        // Partition. After this,
        //  all numbers in [begin, p - 1] will be <= [p], and
        //  all numbers in [p + 1, end] will be > [p].
        while (true) {
            while (i >= begin && a[i] <= a[p])
                --i;
            while (j < end && a[j] > a[p])
                ++j;

            if (i >= begin && j < end) {
                // swap [i] and [j]
                swap(a, i, j);
            } else if (i >= begin) {
                // swap [i] and [p]
                swap(a, i, p);
            } else if (j < end) {
                // swap [p] and [j]
                if (a[p] == a[j]) {
                    ++p;
                    if (p == j)
                        break;
                }
                swap(a, p, j);
            } else {
                break;
            }
        }

        sort(a, begin, p);
        sort(a, p, end);
    }

    // swap [i] and [j]
    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Test
    void example1() {
        int[] a = {5, 2, 1, 3, 2, 2, 3, 7};
        sort(a);
        Assertions.assertTrue(IntArrays.isSorted(a, 0, a.length));
    }

    @Test
    void example2() {
        int[] a = {1, 2, 3, 4, 3};
        sort(a);
        Assertions.assertTrue(IntArrays.isSorted(a, 0, a.length));
    }

    @Test
    void example3() {
        int[] a = {3, 1, 3, 4, 5};
        sort(a);
        Assertions.assertTrue(IntArrays.isSorted(a, 0, a.length));
    }

    @Test
    void example4() {
        int[] a = {3, 1, 3, 4, 3};
        sort(a);
        Assertions.assertTrue(IntArrays.isSorted(a, 0, a.length));
    }

    @Test
    void example5() {
        int[] a = {1, 2, 1, 3, 4, 3, 3};
        sort(a);
        Assertions.assertTrue(IntArrays.isSorted(a, 0, a.length));
    }
}
