package minimum_ascii_delete_sum_for_two_strings_712;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Follow a different direction from {@link Solution2A}, just to show
 * that the direction doesn't matter.
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution2B {

    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] = result of s1[0:i] and s2[0:j].
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= m; ++i) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; ++j) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(
                            dp[i - 1][j] + s1.charAt(i - 1),
                            dp[i][j - 1] + s2.charAt(j - 1));
            }
        }

        return dp[m][n];
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
