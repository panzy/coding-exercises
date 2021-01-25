package count_primes_204;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

/**
 * In mathematics, the sieve of Eratosthenes is an ancient algorithm for finding all prime numbers up to any given limit.
 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Algorithm_complexity
 *
 * Created by Zhiyong Pan on 2021-01-25.
 */
public class Solution {
    public int countPrimes(int n) {
        if (n <= 2)
            return 0;

        // BitSet is slower than a boolean array, but requires less memory.
        BitSet table = new BitSet(n);

        // Init the answer to the number of numbers in range [2,n).
        // Whenever we find a non-prime number for the first time,
        // we reduce the answer by 1.
        int ans = n - 2;

        // Mark any number that is p x q, where q >= p.
        // p should not exceed sqrt(n) because in that case p x p will certainly exceed n.
        for (int p = 2, end = (int) Math.sqrt(n); p <= end; ++p) {
            int q = p;
            while (true) {
                int m = p * q;
                if (m < n) {
                    if (!table.get(m)) {
                        --ans;
                        table.set(m);
                    }
                    ++q;
                } else {
                    break;
                }
            }
        }

        return ans;
    }

    @Test
    void testCountPrimes() {
        Assertions.assertEquals(0, countPrimes(0));
        Assertions.assertEquals(0, countPrimes(1));
        Assertions.assertEquals(0, countPrimes(2));
        Assertions.assertEquals(1, countPrimes(3));
        Assertions.assertEquals(2, countPrimes(4));
        Assertions.assertEquals(2, countPrimes(5));
        Assertions.assertEquals(3, countPrimes(6));
        Assertions.assertEquals(4, countPrimes(10));
        Assertions.assertEquals(10, countPrimes(30));
        Assertions.assertEquals(41537, countPrimes(499979));
    }
}
