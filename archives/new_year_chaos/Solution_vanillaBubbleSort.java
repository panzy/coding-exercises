package new_year_chaos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Perform a bubble sort and count the swap times.
 *
 * Since it's an O(N^2) algorithm, it won't work for N = 10^5.
 *
 * Created by Zhiyong Pan on 2021-02-05.
 */
public class Solution_vanillaBubbleSort {
    static int miniSwaps(int[] q) {
        HashMap<Integer, Integer> bribes = new HashMap<>();
        int swaps = 0;
        int n = q.length;

        for (int i = 0; i + 1 < n; ++i) {
            for (int j = 0; j + 1 < n - i; ++j) {
                if (q[j] > q[j + 1]) {
                    int b = bribes.getOrDefault(q[j], 0);
                    if (b == 2)
                        return -1;
                    bribes.put(q[j], b + 1);
                    swaps++;
                    swap(q, j, j + 1);
                }
            }
        }
        return swaps;
    }

    private static void swap(int[] q, int i, int j) {
        int t = q[i];
        q[i] = q[j];
        q[j] = t;
    }

    @Test
    void test(){
        Assertions.assertEquals(4, miniSwaps(new int[]{1, 2, 5, 3, 4, 7, 8, 6}));
        Assertions.assertEquals(7, miniSwaps(new int[]{1, 2, 5, 3, 7, 8, 6, 4}));
        Assertions.assertEquals(-1, miniSwaps(new int[]{5, 1, 2, 3, 7, 8, 6, 4}));
    }
}
