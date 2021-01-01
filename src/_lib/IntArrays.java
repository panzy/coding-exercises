package _lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Because java.util.Arrays.asList() and java.util.List.indexOf() do not support primitive types.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public abstract class IntArrays {
    public static int indexOf(int[] arr, int e) {
        return indexOf(arr, 0, arr.length, e);
    }

    public static int indexOf(int[] arr, int begin, int end, int e) {
        for (int i = begin; i < end; ++i) {
            if (arr[i] == e) return i;
        }
        return -1;
    }
}

class IntArrayTest {
    @Test
    void indexOf_first() {
        Assertions.assertEquals(0, IntArrays.indexOf(new int[]{1, 2, 3}, 1));
    }

    @Test
    void indexOf_last() {
        Assertions.assertEquals(2, IntArrays.indexOf(new int[]{1, 2, 3}, 3));
    }

    @Test
    void indexOf_notFound() {
        Assertions.assertEquals(-1, IntArrays.indexOf(new int[]{1, 2, 3}, 4));
    }

    @Test
    void indexOf_range() {
        Assertions.assertEquals(-1, IntArrays.indexOf(new int[]{1, 2, 3}, 1, 3, 1));
        Assertions.assertEquals(0, IntArrays.indexOf(new int[]{1, 2, 3}, 0, 1, 1));
        Assertions.assertEquals(1, IntArrays.indexOf(new int[]{1, 2, 3}, 1, 3, 2));
        Assertions.assertEquals(2, IntArrays.indexOf(new int[]{1, 2, 3}, 1, 3, 3));
    }
}
