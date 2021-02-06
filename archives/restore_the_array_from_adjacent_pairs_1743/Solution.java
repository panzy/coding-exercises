package restore_the_array_from_adjacent_pairs_1743;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhiyong Pan on 2021-01-30.
 */
public class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;

        // key = a number of the nums array
        // value = [ neighbourCnt, neighbourA, neighbourB ]
        HashMap<Integer, int[]> graph = new HashMap<>(n);

        for (int[] p : adjacentPairs) {
            int a = p[0];
            int b = p[1];

            {
                int[] neibs = graph.get(a);
                if (neibs == null) {
                    neibs = new int[3];
                    graph.put(a, neibs);
                }
                if (neibs[0] == 0) {
                    neibs[1] = b;
                } else {
                    assert neibs[0] == 1;
                    neibs[2] = b;
                }
                ++neibs[0];
            }

            {
                int[] neibs = graph.get(b);
                if (neibs == null) {
                    neibs = new int[3];
                    graph.put(b, neibs);
                }
                if (neibs[0] == 0) {
                    neibs[1] = a;
                } else {
                    assert neibs[0] == 1;
                    neibs[2] = a;
                }
                ++neibs[0];
            }
        }

        int[] ans = new int[n];

        for (Map.Entry<Integer, int[]> e : graph.entrySet()) {
            if (e.getValue()[0] == 1) {
                ans[0] = e.getKey();
                break;
            }
        }

        for (int i = 1; i < n; ++i) {
            int[] neibs = graph.get(ans[i - 1]);
            if (neibs[0] == 1 || neibs[2] == ans[i - 2]) {
                ans[i] = neibs[1];
            } else {
                ans[i] = neibs[2];
            }
        }

        return ans;
    }

    @Test
    void test1() {
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4}, restoreArray(new int[][]{
                {2, 1}, {3, 4}, {3, 2}
        }));
        Assertions.assertArrayEquals(new int[]{-2, 4, 1, -3}, restoreArray(new int[][]{
                {4, -2}, {1, 4}, {-3, 1}
        }));
        Assertions.assertArrayEquals(new int[]{100_000, -100_000}, restoreArray(new int[][]{
                {100_000, -100_000}
        }));
    }
}
