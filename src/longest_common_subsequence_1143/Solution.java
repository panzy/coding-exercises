package longest_common_subsequence_1143;

import java.util.Arrays;

/**
 * Recursion + memo.
 *
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class Solution {
    private String s1, s2;
    private int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        s1 = text1;
        s2 = text2;
        memo = new int[s1.length()][s2.length()];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dp(s1.length() - 1, s2.length() - 1);
    }

    private int dp(int i, int j) {
        if (i < 0 || j < 0)
            return 0;

        if (memo[i][j] >= 0)
            return memo[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return memo[i][j] = 1 + dp(i - 1, j - 1);
        else
            return memo[i][j] = Math.max(dp(i, j - 1), dp(i - 1, j));
    }
}
