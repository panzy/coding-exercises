package minimum_ascii_delete_sum_for_two_strings_712;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Non-recursive DP.
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution2A {

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] = result of s1[i:m] and s2[j:n].
        int[][] dp = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; --i) {
            dp[i][n] = dp[i + 1][n] + s1.charAt(i);
        }
        for (int j = n - 1; j >= 0; --j) {
            dp[m][j] = dp[m][j + 1] + s2.charAt(j);
        }

        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1];
                else
                    dp[i][j] = Math.min(
                            s1.charAt(i) + dp[i + 1][j],
                            s2.charAt(j) + dp[i][j + 1]);
            }
        }

        return dp[0][0];
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
