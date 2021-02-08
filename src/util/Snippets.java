package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class Snippets {
    void streamWithIndices() {
        char[] A = "abcdefg".toCharArray();
        int n = A.length;
        int[] anchors = IntStream.range(0, n).filter(i -> A[i] == 'c').toArray();
    }

    private static void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; ++i, --j) {
            swap(a, i, j);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int[] deduplicate(int[] a) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : a) set.add(i);
        return set.stream().mapToInt(v -> v).toArray();
    }

    private static int countLessThan(int[] a, int v) {
        int i = Arrays.binarySearch(a, v);
        return i >= 0 ? i : -(i + 1);
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
}
