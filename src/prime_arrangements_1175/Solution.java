package prime_arrangements_1175;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-25.
 */
public class Solution {
    final static int mod = 1_000_000_007;

    public int numPrimeArrangements(int n) {
        int m = new count_primes_204.Solution().countPrimes(n + 1);
        return (int) (permutations(m) * permutations(n - m) % mod);
    }

    static long permutations(int n) {
        long ans = 1;
        while (n > 1) {
            ans = ans * n % mod;
            --n;
        }
        return ans;
    }

    @Test
    void testPrimeArrangments() {
        Assertions.assertEquals(1, numPrimeArrangements(1));
        Assertions.assertEquals(1, numPrimeArrangements(2));
        Assertions.assertEquals(2, numPrimeArrangements(3));
        Assertions.assertEquals(4, numPrimeArrangements(4));
        Assertions.assertEquals(12, numPrimeArrangements(5));
        Assertions.assertEquals(682289015, numPrimeArrangements(100));
    }
}
