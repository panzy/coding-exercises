package _lib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public static void fillRandom(int[] arr, int begin, int end, int min, int max) {
        for (int i = begin; i < end; ++i) {
            arr[i] = (int) (Math.random() * (max - min) + min);
        }
    }

    public static void fillUniqueRandom(int[] arr, int begin, int end) {
        arr[begin] = 0;
        for (int i = begin + 1; i < end; ++i) {
            arr[i] = arr[i - 1] + (int) (Math.random() * 10);
        }
        shuffle(arr, begin, end);
    }

    public static void fillUniqueRandom(int[] arr) {
        fillUniqueRandom(arr, 0, arr.length);
    }

    public static void shuffle(int[] arr, int begin, int end) {
        for (int r = 0; r < end - begin; ++r) {
            int i = begin + (int) (Math.random() * (end - begin));
            int j = begin + (int) (Math.random() * (end - begin));
            if (i != j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
    }

    public static void shuffle(int[] arr) {
        shuffle(arr, 0, arr.length);
    }

    public static String join(int[] arr, int begin, int end, String delimiter) {
        return Arrays.stream(arr, begin, end).mapToObj(Integer::toString).collect(Collectors.joining(delimiter));
    }

    public static String join(int[] arr) {
        return join(arr, 0, arr.length, ",");
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
