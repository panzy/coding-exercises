package three_equal_parts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unfortunately, this solution works only if the parts are not longer than 32 (or 64, if change the type of the
 * working variables from int to long).
 *
 * Created by Zhiyong Pan on 2021-01-12.
 */
public class Solution {
    public int[] threeEqualParts(int[] A) {
        final int n = A.length;
        int[] ans = {-1, -1};
        // init the three parts to be: [0, i), [i, j), [j, n).
        int i = n - 2, j = n - 1;

        int a = valueOf(A, 0, i),
                b = valueOf(A, i, j),
                c = valueOf(A, j, n);

        while (i > 0) {
            if (a == b && b == c) {
                ans[0] = i - 1;
                ans[1] = j;
                break;
            }

            // update c
            if (A[j - 1] == 1) {
                c += 1 << (n - j);
            }

            // update b
            b >>= 1;
            if (i - 1 >= 0 && A[i - 1] == 1) {
                b += 1 << (j - i - 1);
            }
            if (i - 2 >= 0 && A[i - 2] == 1) {
                b += 1 << (j - i);
            }

            // update a
            a >>= 2;

            --j;
            i -= 2;
        }

        return ans;
    }

    private int valueOf(int[] a, int begin, int end) {
        int sum = 0;
        for (int i = Math.max(0, begin); i < end; ++i) {
            if (a[i] == 1) {
                sum += 1 << (end - i - 1);
            }
        }
        return sum;
    }

    @Test
    void testValueOf() {
        int[] a = {1, 0, 1, 0, 1};
        Assertions.assertEquals(1, valueOf(a, 0, 1));
        Assertions.assertEquals(2, valueOf(a, 0, 2));
        Assertions.assertEquals(5, valueOf(a, 0, 3));
        Assertions.assertEquals(10, valueOf(a, 0, 4));
        Assertions.assertEquals(21, valueOf(a, 0, 5));
    }
}
