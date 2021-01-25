package prime_palindrome_866;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-25.
 */
public class Solution {
    public int primePalindrome(int N) {
        while (true) {
            // If a integer equals its inverse then it's a palindrome.
            if (N == reverse(N) && isPrime(N))
                return N;
            N++;

            // Ignore all numbers with even digits (except 11),
            // because none of them can be palindromic and prime at the same time.
            // Why? Because every such palindrome is divisible by 11.
            if (1_000 < N && N < 10_000)
                N = 10_000;
            else if (100_000 < N && N < 1_000_000)
                N = 1_000_000;
            else if (10_000_000 < N && N < 100_000_000)
                N = 100_000_000;
        }
    }

    public boolean isPrime(int N) {
        if (N < 2) return false;
        int R = (int) Math.sqrt(N);
        for (int d = 2; d <= R; ++d)
            if (N % d == 0) return false;
        return true;
    }

    public int reverse(int N) {
        int ans = 0;
        while (N > 0) {
            ans = 10 * ans + (N % 10);
            N /= 10;
        }
        return ans;
    }

    @Test
    void testPrimePalindrome() {
        Assertions.assertEquals(7, primePalindrome(6));
        Assertions.assertEquals(11, primePalindrome(8));
        Assertions.assertEquals(101, primePalindrome(13));
    }
}

