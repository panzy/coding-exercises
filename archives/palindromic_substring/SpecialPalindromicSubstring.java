package palindromic_substring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Special String Again
 * https://www.hackerrank.com/challenges/special-palindrome-again/problem
 *
 * Created by Zhiyong Pan on 2021-02-08.
 */
class SpecialPalindromicSubstring {
    static long substrCount(int n, String s) {
        long ans = 0;

        char[] A = s.toCharArray();

        for (int i = 0; i < n; ++i) {
            ++ans; // the char itself is a special substring

            // As symmetric center
            if (i > 0 && i + 1 < n && A[i - 1] != A[i]) {
                for (int r = 1; i - r >= 0 && i + r < n && A[i - r] == A[i - 1] && A[i + r] == A[i - 1]; ++r) {
                    ++ans;
                }
            }

            // As the beginning of "aaa"
            for (int j = i + 1; j < n && A[j] == A[i]; ++j) {
                ++ans;
            }
        }

        return ans;
    }

    @Test
    void examples() {
        Assertions.assertEquals(10, substrCount(4, "aaaa"));
        Assertions.assertEquals(7, substrCount(5, "asasd"));
    }
}
