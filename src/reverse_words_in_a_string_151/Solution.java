package reverse_words_in_a_string_151;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-02-03.
 */
class Solution {
    public String reverseWords(String s) {
        char[] A = s.toCharArray();
        int n = A.length;
        StringBuilder sb = new StringBuilder(n);
        int wend = n;
        boolean added = false;

        // Consider A[-1] a sentinel whitespace.
        for (int i = n - 1; i >= -1; --i) {
            if (i == -1 || A[i] == ' ') {
                if (i + 1 < wend) {
                    if (added) {
                        sb.append(' ');
                    } else {
                        added = true;
                    }
                    sb.append(Arrays.copyOfRange(A, i + 1, wend));
                }
                wend = i;
            }
        }

        return sb.toString();
    }

    @Test
    void examples() {
        Assertions.assertEquals("blue is sky the", reverseWords("the sky is blue"));
        Assertions.assertEquals("example good a", reverseWords("a good   example"));
        Assertions.assertEquals("Alice Loves Bob", reverseWords("  Bob    Loves  Alice   "));
        Assertions.assertEquals("bob like even not does Alice", reverseWords("Alice does not even like bob"));
    }
}
