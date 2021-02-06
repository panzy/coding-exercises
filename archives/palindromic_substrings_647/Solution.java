package palindromic_substrings_647;

/**
 * Created by Zhiyong Pan on 2021-01-19.
 */
public class Solution {
    private int n;
    private String s;

    public int countSubstrings(String s) {
        this.s = s;
        n = s.length();

        int ans = 0;

        for (int i = 0; i < n; ++i) {
            // Use letter x as center to search the longest palindrome.
            // The number of palindromes is 1 + radius-of-longest-palindrome.
            // The radius doesn't include the center itself, which is also a palindrome,
            // hence the constant 1 in the formula.
            ans += 1 + palindromeOfCenter(i, i + 1);

            // Use letter xx as center to search the longest palindrome.
            if (i + 1 < n && s.charAt(i) == s.charAt(i + 1))
                ans += 1 + palindromeOfCenter(i, i + 2);
        }

        return ans;
    }

    /**
     * Expand a palindrome symmetrically.
     * @param c1 the beginning of the seed palindromic string.
     * @param c2 the end (exclusive) of the seed palindromic string.
     * @return the increment of radius.
     */
    private int palindromeOfCenter(int c1, int c2) {
        int i = 0;
        while (c1 - i - 1 >= 0 && c2 + i < n && s.charAt(c1 - i - 1) == s.charAt(c2 + i))
            ++i;
        return i;
    }
}
