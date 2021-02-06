package new_year_chaos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Here, performing a bubble sort in one pass is possible only because
 * for each position, if the number at it is not correct, there are at
 * most two positions immediately ahead of it need to check. While for a
 * normal sort problem, one will have to check all the positions before.
 *
 * Created by Zhiyong Pan on 2021-02-05.
 */
public class Solution_onePassBubbleSort {
    static int miniSwaps(int[] q) {
        int swaps = 0;
        int n = q.length;

        for (int i = n - 1; i - 1 >= 0; --i) {
            if (i + 1 != q[i]) {
                if (i + 1 == q[i - 1]) {
                    // [i-1] bribes once to come to i.
                    swap(q, i - 1, i);
                    ++swaps;
                } else if (i + 1 == q[i - 2]) {
                    // [i-2] bribes twice to come to i.
                    swap(q, i - 2, i - 1);
                    swap(q, i - 1, i);
                    swaps += 2;
                } else {
                    return -1;
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
