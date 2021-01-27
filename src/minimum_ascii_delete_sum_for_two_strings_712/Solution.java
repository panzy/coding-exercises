package minimum_ascii_delete_sum_for_two_strings_712;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Recursion + memo = dp.
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution {
    private String s1, s2;
    private int m, n;
    private int[][] memo;

    public int minimumDeleteSum(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        m = s1.length();
        n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dp(0, 0);
    }

    private int dp(int i, int j) {
        if (i == m) {
            return asciiSum(s2, j, n);
        } else if (j == n) {
            return asciiSum(s1, i, m);
        }

        if (memo[i][j] >= 0)
            return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return memo[i][j] = dp(i + 1, j + 1);
        else
            return memo[i][j] = Math.min(
                    s1.charAt(i) + dp(i + 1, j),
                    s2.charAt(j) + dp(i, j + 1));
    }

    private int asciiSum(String s, int begin, int end) {
        int ans = 0;
        while (begin < end)
            ans += s.charAt(begin++);
        return ans;
    }

    @Test
    void testASCII() {
        Assertions.assertEquals(0, minimumDeleteSum("a", "a"));
        Assertions.assertEquals('b', minimumDeleteSum("a", "ab"));
        Assertions.assertEquals('a', minimumDeleteSum("z", "za"));
        Assertions.assertEquals('a' * 2, minimumDeleteSum("ab", "ba"));
        Assertions.assertEquals('b' * 2, minimumDeleteSum("cb", "bc"));
        Assertions.assertEquals('c' * 2, minimumDeleteSum("cbb", "bbc"));
        Assertions.assertEquals('b' * 3, minimumDeleteSum("cbb", "bc"));
        Assertions.assertEquals(231, minimumDeleteSum("sea", "eat"));
        Assertions.assertEquals(403, minimumDeleteSum("delete", "leet"));
    }

}
