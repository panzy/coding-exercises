package util;

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

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
