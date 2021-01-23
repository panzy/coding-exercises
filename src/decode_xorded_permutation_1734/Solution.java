package decode_xorded_permutation_1734;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public int[] decode(int[] encoded) {
        /* Let the original array be [p0, p1, ... p[n-1]]

        (1) xor_all_elements
            = p0 ^ p1 ^ p2 ^ ... p[n-1]
            = p0 ^ (p1 ^ p2) ^ (p3 ^ p4) ^ ... (p[n-2] ^ p[n-1])
            = p0 ^ e1 ^ e3 ^ ... e[n-2]

        (2) Since we knew the array is a permutation of the first n positive integers, we have

            xor_all_elements = 1 ^ 2 ^ 3 ... n

        Combining (1) and (2), the value of p0 can be determined.
        */

        int n = encoded.length + 1;
        int all = 1;
        for (int i = 2; i <= n; ++i)
            all ^= i;

        int[] p = new int[n];

        // p0
        p[0] = all;
        for (int i = 1; i <= n - 2; i += 2) {
            p[0] ^= encoded[i];
        }

        // The rest of p.
        for (int i = 1; i < n; ++i) {
            // Because e[i-1] == p[i-1] ^ p[i]
            p[i] = encoded[i-1] ^ p[i - 1];
        }

        return p;
    }

    @Test
    void example1() {
        Assertions.assertArrayEquals(new int[]{1, 2, 3}, decode(new int[]{3, 1}));
    }

    @Test
    void example2() {
        Assertions.assertArrayEquals(new int[]{2, 4, 1, 5, 3}, decode(new int[]{6, 5, 4, 6}));
    }
}
