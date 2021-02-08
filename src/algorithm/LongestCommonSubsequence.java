package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-08.
 */
public class LongestCommonSubsequence {
    static int commonChild(String s1, String s2) {
        char[] A = s1.toCharArray();
        char[] B = s2.toCharArray();
        int n = A.length;

        // dp[i][j] = longest common subsequence of s1 range [0,i) and s2 range [0:j).
        int[][] dp = new int[n + 1][n + 1];

        // Unnecessary init because java primitive array is automatically filled with zeros.
        for (int i = 0; i < n; ++i) {
            dp[i][0] = 0;
            dp[0][i] = 0;
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[n][n];
    }

    @Test
    void examples() {
        Assertions.assertEquals(3, commonChild("SHINCHAN", "NOHARAAA"));
        Assertions.assertEquals(2, commonChild("ABCDEF", "FBDAMN"));
    }
}
