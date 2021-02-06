package longest_palindromic_subsequence_516;

import java.util.Arrays;

/**
 * This solution is based on {@link longest_common_subsequence_1143.Solution} and the observation that
 * the longest palindrome subsequence of S is also the longest common subsequence of S and S', where S'
 * is reversed S.
 *
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class Solution {
    private String s;
    private int n;
    private int[][] memo;

    public int longestPalindromeSubseq(String s) {
        this.s = s;
        n = s.length();
        memo = new int[n][n];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        return dp(n - 1, n - 1);
    }


    /**
     * Return the length of the longest palindrome subsequence of s[0:i] and s'[0:j].
     *
     * @param i end of the first string, inclusive.
     * @param j end of the second string, inclusive.
     * @return
     */
    private int dp(int i, int j) {
        if (i < 0 || j < 0)
            return 0;

        if (memo[i][j] >= 0)
            return memo[i][j];

        // What we want to compare are s[i] and s'[j] = s[n - 1 - j].
        if (s.charAt(i) == s.charAt(n - 1 - j))
            return memo[i][j] = 1 + dp(i - 1, j - 1);
        else
            return memo[i][j] = Math.max(dp(i, j - 1), dp(i - 1, j));
    }
}