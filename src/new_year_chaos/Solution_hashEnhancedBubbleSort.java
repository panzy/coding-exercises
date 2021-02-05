package new_year_chaos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Based on vanilla bubble sort. Instead of using an O(N^2) loops to find [i] > [i+1],
 * use a hash map to keep track of indices that need to swap.
 *
 * This solution is accepted by HackerRank.
 *
 * Created by Zhiyong Pan on 2021-02-05.
 */
public class Solution_hashEnhancedBubbleSort {
    private static int miniSwaps(int[] q) {
        HashMap<Integer, Integer> bribes = new HashMap<>();
        HashSet<Integer> unordered = new HashSet<>();
        int swaps = 0;
        int n = q.length;

        for (int i = 0; i < n - 1; ++i) {
            if (i + 1 + 2 < q[i])
                return -1;
            else if (i + 1 < q[i])
                unordered.add(i);
        }

        while (!unordered.isEmpty()) {
            ArrayList<Integer> changed = new ArrayList<>();
            for (int i : unordered) {
                if (q[i] > q[i + 1]) {
                    int b = bribes.getOrDefault(q[i], 0);
                    if (b == 2)
                        return -1;
                    swap(q, i, i + 1);
                    ++swaps;

                    changed.add(i);
                    if (i + 2 < n)
                        changed.add(i + 1);
                }
            }

            for (int i : changed)
                if (i + 1 == q[i])
                    unordered.remove(i);
                else
                    unordered.add(i);
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
