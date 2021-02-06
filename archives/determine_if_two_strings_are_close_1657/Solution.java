package determine_if_two_strings_are_close_1657;

/**
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class Solution {
    public boolean closeStrings(String word1, String word2) {
        // A[i] = the frequency of the (i+1)-th letter in word1.
        // 'a' is the 1-th letter, and 'z' is the 26-th.
        int[] A = new int[26];
        // B[i] = the frequency of the (i+1)-th letter in word2.
        int[] B = new int[26];

        int N = word1.length();

        // Both operations can not change string length.
        if (N != word2.length())
            return false;

        // Collect letter frequencies.
        for (int i = 0; i < N; ++i) {
            ++A[word1.charAt(i) - 'a'];
            ++B[word2.charAt(i) - 'a'];
        }

        // We don't worry about order because of Operation 1.
        // We only have to make sure, for each letter in word1,
        // word2 can manage to produce the same count.
        for (int i = 0; i < 26; ++i) {
            // Both operations can not produce new letters.
            if (A[i] != B[i] && (A[i] == 0 || B[i] == 0))
                return false;

            // Try to let A[i] == B[i].
            if (A[i] != B[i]) {
                // Find B[j] == A[i] where j > i (letters with j < i have been used).
                int j = i + 1;
                while (j < 26 && B[j] != A[i])
                    ++j;
                if (j == 26)
                    return false;
                B[j] = B[i]; // operation 2
            }
        }

        return true;
    }
}
