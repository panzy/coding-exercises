package _lib;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
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

    public static int indexOfMax(int[] arr, int begin, int end) {
        int m = begin;
        for (int i = begin + 1; i < end; ++i) {
            if (arr[i] > arr[m]) m = i;
        }
        return m;
    }

    public static int indexOfMax(int[] arr) {
        return indexOfMax(arr, 0, arr.length);
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

    public static int[] loadFromJsonFile(String filename) throws IOException, ParseException {
        return loadFromJson(new FileReader(filename));
    }

    public static int[] loadFromJson(String text) throws IOException, ParseException {
        return loadFromJson(new StringReader(text));
    }

    public static int[][] load2DFromJsonFile(String filename) throws IOException, ParseException {
        return load2DFromJson(new FileReader(filename));
    }

    public static int[][] load2DFromJson(String text) throws IOException, ParseException {
        return load2DFromJson(new StringReader(text));
    }

    private static int[][] load2DFromJson(Reader fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONArray ja = (JSONArray) obj;
        int n = ja.size();
        int[][] a = new int[n][];
        for (int i = 0; i < n; ++i) {
            JSONArray jaa = (JSONArray) ja.get(i);
            int nn = jaa.size();
            int[] aa = new int[nn];
            for (int j = 0; j < nn; ++j)
                aa[j] = Integer.parseInt(jaa.get(j).toString());
            a[i] = aa;
        }
        return a;
    }

    private static int[] loadFromJson(Reader fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(fileReader);
        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONArray ja = (JSONArray) obj;
        int n = ja.size();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(ja.get(i).toString());
        }
        return a;
    }

    public static boolean isSorted(int[] arr, int begin, int end) {
        int order = 0;
        for (int i = begin; i + 1 < end; ++i) {
            if (arr[i] < arr[i + 1]) {
                if (order == 1)
                    return false;
                order = -1;
            } else if (arr[i] > arr[i + 1]) {
                if (order == -1)
                    return false;
                order = 1;
            }
        }

        return true;
    }

    public static int[] fromList(List<Integer> list) {
        int[] a = new int[list.size()];
        int i = 0;
        for (int e : list) a[i++] = e;
        return a;
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
