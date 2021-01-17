package count_sorted_vowel_strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Recursion with memoization.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    // Memorize the answer to countVowelStrings(int, int).
    int[][] memo;

    public int countVowelStrings(int n) {
        memo = new int[1 + n][5];
        return countVowelStrings(n, 0);
    }

    /**
     * How many strings of length n and start with the given vowel char?
     * @param n string length.
     * @param startFrom 0 = 'a', 4 = 'u'
     * @return count
     */
    private int countVowelStrings(int n, int startFrom) {
        if (n == 1) return 5 - startFrom;

        if (memo[n][startFrom] != 0)
            return memo[n][startFrom];

        int sum = 0;
        for (int c = startFrom; c < 5; ++c) {
            sum += countVowelStrings(n - 1, c);
        }

        memo[n][startFrom] = sum;
        return sum;
    }

    @Test
    void example1() {
        Assertions.assertEquals(5, countVowelStrings(1));
    }

    @Test
    void example2() {
        Assertions.assertEquals(15, countVowelStrings(2));
    }

    @Test
    void example3() {
        Assertions.assertEquals(66045, countVowelStrings(33));
    }
}
