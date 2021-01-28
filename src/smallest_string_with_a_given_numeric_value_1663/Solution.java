package smallest_string_with_a_given_numeric_value_1663;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Essentially, the problem is to distribute weight to chars so that the target string
 * is lexicographically smallest. To do this, we want the starting chars to be as small
 * as possible, thus, the ending chars have to be as large as possible.
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution {
    public String getSmallestString(int n, int k) {
        char[] ans = new char[n];
        for (int i = n - 1; i >= 0; --i) {
            int c = Math.min(26,  k - i);
            ans[i] = (char) ('a' - 1 + c);
            k -= c;

            if (ans[i] == 'a') { // all before should also be 'a'
                Arrays.fill(ans, 0, i, 'a');
                break;
            }
        }
        return new String(ans);
    }

    @Test
    void testSmallestString() {
        Assertions.assertEquals("aay", getSmallestString(3, 27));
        Assertions.assertEquals("aaszz", getSmallestString(5, 73));
    }
}
