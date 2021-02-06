package reordered_power_of_2_869;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-25.
 */
public class Solution {
    public boolean reorderedPowerOf2(int N) {
        // There are only a few powers of 2 given that N <= 10^9.

        // [i] = frequency of digit i.
        int[] freq = new int[10];

        // Collect digit frequencies.
        for (int i = N; i > 0; i /= 10) {
            freq[i % 10]++;
        }

        // For each power of 2, check whether its digit frequencies match that of N.
        int num = 1;
        int bits = 1;
        while (bits < 32) {
            if (freqMatch(num, freq)) {
                return true;
            }
            num <<= 1;
            ++bits;
        }
        return false;
    }

    private boolean freqMatch(int n, int[] freq) {
        // Consume digits in freq.
        for (int i = n; i > 0; i /= 10) {
            --freq[i % 10];
        }

        // Check.
        boolean ans = true;
        for (int f : freq) {
            if (f != 0) {
                ans = false;
                break;
            }
        }

        // Recover the freq.
        for (int i = n; i > 0; i /= 10) {
            ++freq[i % 10];
        }

        return ans;
    }

    @Test
    void testReorderedPowerOf2() {
        Assertions.assertTrue(reorderedPowerOf2(1));
        Assertions.assertFalse(reorderedPowerOf2(10));
        Assertions.assertTrue(reorderedPowerOf2(16));
        Assertions.assertFalse(reorderedPowerOf2(24));
        Assertions.assertTrue(reorderedPowerOf2(64));
    }
}
