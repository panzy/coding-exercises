package palindrome_partitioning_iv_1745;

/**
 * Build a palindrome query table in O(N^2).
 * Then enumerate all partitioning plans in another O(N^2).
 *
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class Solution {
    char[] chars;

    // [i][j] = whether range [i,j] (inclusive) is palindromic.
    boolean[][] table;

    public boolean checkPartitioning(String s) {
        int n = s.length();
        chars = s.toCharArray();
        table = new boolean[n][n];

        // Build the table.
        for (int i = 0; i < n; ++i) {
            for (int j = i, k = i; j >= 0 && k < n && chars[j] == chars[k]; --j, ++k) {
                table[j][k] = true;
            }
            for (int j = i, k = i + 1; j >= 0 && k < n && chars[j] == chars[k]; --j, ++k) {
                table[j][k] = true;
            }
        }

        for (int i = 1; i + 1 < n; ++i) {
            if (table[0][i - 1]) {
                for (int j = i + 1; j < n; ++j) {
                    if (table[i][j - 1] && table[j][n - 1])
                        return true;
                }
            }
        }

        return false;
    }
}
