package delete_operation_for_two_strings_583;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Find the longest common subsequence.
 *
 * Created by Zhiyong Pan on 2021-02-01.
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] A = word1.toCharArray();
        char[] B = word2.toCharArray();

        // dp[i][j] = longest common subsequence of s1.substr(0, i) and s2.substr(0, j).
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0 ; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i] == B[j])
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                else
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        return m + n - 2 * dp[m][n];
    }

    @Test
    void test() {
        Assertions.assertEquals(2, minDistance("sea", "eat"));
        Assertions.assertEquals(1, minDistance("ea", "eat"));
        Assertions.assertEquals(1, minDistance("ea567", "eat567"));
    }
}
