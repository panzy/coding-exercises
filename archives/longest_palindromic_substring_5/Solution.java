package longest_palindromic_substring_5;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class Solution {
    private int n;
    private String s;

    public String longestPalindrome(String s) {
        this.s = s;
        n = s.length();

        int[] ans = {0, 1}; // Each single letter is a palindrome

        for (int i = 0; i < n; ++i) {
            if (2 * (n - i) <= ans[1] - ans[0]) // There is no space for a longer answer.
                break;

            // Use letter x as center to search the longest palindrome.
            ans = max(ans, palindromeOfCenter(i, i + 1));

            // Use letter xx as center to search the longest palindrome.
            if (i + 1 < n && s.charAt(i) == s.charAt(i + 1))
                ans = max(ans, palindromeOfCenter(i, i + 2));
        }

        return s.substring(ans[0], ans[1]);
    }

    /**
     * Return the longer range.
     */
    private int[] max(int[] a, int[] b) {
        return a[1] - a[0] < b[1] - b[0] ? b : a;
    }

    /**
     * Expand a palindrome symmetrically.
     * @param c1 the beginning of the seed palindromic string.
     * @param c2 the end (exclusive) of the seed palindromic string.
     * @return the expanded range [begin, end).
     */
    private int[] palindromeOfCenter(int c1, int c2) {
        int i = 0;
        for (; c1 - i >= 0 && c2 - 1 + i < n; ++i) {
            if (s.charAt(c1 - i) != s.charAt(c2 - 1 + i))
                break;
        }
        return new int[]{c1 - (i - 1), c2 + i - 1};
    }
}
