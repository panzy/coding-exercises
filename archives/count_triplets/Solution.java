package count_triplets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhiyong Pan on 2021-02-05.
 */
class Solution {
    // key = a number
    // value = its indices.
    private static HashMap<Long, ArrayList<Integer>> dict = new HashMap<>();

    static long countTriplets(List<Long> arr, long r) {
        if (r == 1)
            return countTriplets(arr);
        dict.clear();
        long triplets = 0;
        long r2 = r * r;
        int k = 0;
        for (Long num : arr) {
            if (num % r2 == 0) {
                // num could be the 3rd of a triplet.

                // Previous j indices.
                ArrayList<Integer> jIndices = dict.computeIfAbsent(num / r, t -> new ArrayList<>());
                ArrayList<Integer> iIndices = dict.computeIfAbsent(num / r2, t -> new ArrayList<>());

                if (jIndices.size() > 0 && iIndices.size() > 0) {
                    for (int jj = jIndices.size() - 1; jj >= 0; --jj) {
                        int j = jIndices.get(jj);
                        if (j <= iIndices.get(0))
                            break;
                        for (int i : iIndices) {
                            if (i < j) {
                                ++triplets;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            dict.computeIfAbsent(num, t -> new ArrayList<>()).add(k);
            ++k;
        }

        return triplets;
    }

    static long countTriplets(List<Long> arr) {
        HashMap<Long, Integer> freq = new HashMap<>();
        for (Long num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        long ans = 0;
        for (int n : freq.values()) {
            if (n >= 3) {
                // To overcome overflow, have to divide by 6 early.
                // Then to keep correct, have to convert to double.
                ans += n / 6.0  * (n - 1) * (n - 2);
            }
        }
        return ans;
    }

    @Test
    void test() {
        Assertions.assertEquals(1, countTriplets(Arrays.stream(new long[]{
                1, 1, 1
        }).boxed().collect(Collectors.toList()), 1));

        Assertions.assertEquals(4, countTriplets(Arrays.stream(new long[]{
                1, 1, 1, 1
        }).boxed().collect(Collectors.toList()), 1));

        Assertions.assertEquals(161700, countTriplets(Arrays.stream(new long[]{
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
        }).boxed().collect(Collectors.toList()), 1));

        Assertions.assertEquals(2, countTriplets(Arrays.stream(new long[]{
                1, 2, 2, 4
        }).boxed().collect(Collectors.toList()), 2));

        Assertions.assertEquals(6, countTriplets(Arrays.stream(new long[]{
                1, 3, 9, 9, 27, 81
        }).boxed().collect(Collectors.toList()), 3));

        Assertions.assertEquals(4, countTriplets(Arrays.stream(new long[]{
                1, 5, 5, 25, 125
        }).boxed().collect(Collectors.toList()), 5));

        long[] allOnes = new long[1000];
        Arrays.fill(allOnes, 1);
        Assertions.assertEquals(166167000, countTriplets(Arrays.stream(allOnes).boxed().collect(Collectors.toList()), 1));
    }

    @Test
    void test3() {
        long[] allOnes = new long[100_000];
        Arrays.fill(allOnes, 1237);
        Assertions.assertEquals(166661666700000l, countTriplets(Arrays.stream(allOnes).boxed().collect(Collectors.toList()), 1));
    }
}
