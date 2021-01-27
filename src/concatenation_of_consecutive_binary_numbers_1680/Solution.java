package concatenation_of_consecutive_binary_numbers_1680;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-27.
 */
public class Solution {
    final int MOD = 1_000_000_007;

    public int concatenatedBinary(int n) {
        long ans = 0;

        // This variable is to compensate the reducing of R by 31.
        // Conceptually, for each i we will add to ans
        //      i << R
        // where R is the total number of bits of numbers from i+1 to n.
        //
        // But actually, we will add this instead:
        //      T * i
        //
        // Whenever we have a R, we accumulate it to T and forget about R.
        long T = 1;

        for (long i = n; i >= 1; --i) {
            ans = (ans + T * i) % MOD;

            // How many zeros to the right of i?
            // It's the total number of bits of numbers from i+1 to n.
            // i should left shift this many bits.
            // Since we have constraint n <= 10^5, the following shifting will not overflow.
            T = (T << countBits(i)) % MOD;
        }

        return (int) ans;
    }

    private int countBits(long i) {
        int ans = 0;
        while (i != 0) {
            ++ans;
            i >>= 1;
        }
        return ans;
    }

    @Test
    void testConcatBins() {
        Assertions.assertEquals(1, concatenatedBinary(1));
        Assertions.assertEquals(27, concatenatedBinary(3));
        Assertions.assertEquals(505379714, concatenatedBinary(12));
        Assertions.assertEquals(727837408, concatenatedBinary(42));
        Assertions.assertEquals(851680433, concatenatedBinary(72387));
    }
}
