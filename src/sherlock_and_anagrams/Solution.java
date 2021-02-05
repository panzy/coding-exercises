package sherlock_and_anagrams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-05.
 */
class Solution {
    // freq[c][end] = frequency of char ('a' + c) between [0, end).
    static int[][] freq;

    static int sherlockAndAnagrams(String s) {
        int n = s.length();

        freq = new int[26][1 + n];
        for (int i = 1; i <= n; ++i) {
            for (int c = 0; c < 26; ++c) {
                freq[c][i] = freq[c][i - 1];
            }
            ++freq[s.charAt(i - 1) - 'a'][i];
        }

        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                // Got a substring [i, j).
                for (int p = i + 1; p + (j - i) <= n; ++p) {
                    // Got another substring [p, p + (j - i)).
                    if (check(i, j, p))
                        ++ans;
                }
            }
        }
        return ans;
    }

    // Check whether substrings [i,j) and [p,p+(j-i)) are anagrams.
    private static boolean check(int i, int j, int p) {
        for (int c = 0; c < 26; ++c) {
            if (freq[c][j] - freq[c][i] != freq[c][p + (j - i)] - freq[c][p]) {
                return false;
            }
        }
        return true;
    }

    @Test
    void test() {
        Assertions.assertEquals(10, sherlockAndAnagrams("kkkk"));
        Assertions.assertEquals(3, sherlockAndAnagrams("ifailuhkqq"));
        Assertions.assertEquals(0, sherlockAndAnagrams("abcd"));
        Assertions.assertEquals(4, sherlockAndAnagrams("abba"));
    }
}
