package palindrome_partitioning_iv_1745;

/**
 * Another way to build the palindrome query table.
 *
 * It's no better than the previous one. Just different.
 * In fact, it's slightly slower, because when it evaluates a table[i][j],
 * the result might be false, while in the previous solution, never will
 * a computation be done for false cells.
 *
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class Solution2 {
    char[] chars;

    // [i][j] = whether range [i,j] (inclusive) is palindromic.
    boolean[][] table;

    public boolean checkPartitioning(String s) {
        int n = s.length();
        chars = s.toCharArray();
        table = new boolean[n][n];

        // Build the table.
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (chars[i] == chars[j]) {
                    table[i][j] = i + 1 <= j - 1 ? table[i + 1][j - 1] : true;
                }
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
