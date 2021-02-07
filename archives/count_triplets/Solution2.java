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
class Solution2 {
    static long countTriplets(List<Long> arr, long r) {
        // any number -> its frequency so far.
        HashMap<Long, Long> freqI = new HashMap<>();

        // any potential j of a triplet -> number of valid (i, j) pairs so far.
        HashMap<Long, Long> freqJ = new HashMap<>();

        long triplets = 0;
        long r2 = r * r;
        for (Long num : arr) {

            // Assume this num is a k of a triplet.
            if (num % r2 == 0) {
                triplets += freqJ.getOrDefault(num / r, 0L);
            }

            // Assume this num is a j of a triplet.
            if (num % r == 0) {
                freqJ.put(num, freqI.getOrDefault(num / r, 0L) + freqJ.getOrDefault(num, 0L));
            }

            // Assume this num is an i of a triplet.
            freqI.put(num, freqI.getOrDefault(num, 0L) + 1);
        }

        return triplets;
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
