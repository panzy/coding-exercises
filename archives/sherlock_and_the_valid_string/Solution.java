package sherlock_and_the_valid_string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Sherlock and the Valid String
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
 *
 * Created by Zhiyong Pan on 2021-02-08.
 */
public class Solution {
    static String isValid(String s) {
        int[] freq = letterFrequency(s);
        Supplier<IntStream> freqStreamSup = () -> Arrays.stream(freq).filter(i -> i > 0);
        int min = freqStreamSup.get().min().getAsInt();
        int max = freqStreamSup.get().max().getAsInt();
        int distinctCnt = (int) freqStreamSup.get().distinct().count();

        boolean valid = (distinctCnt == 1) ||
                (max - min == 1 && distinctCnt == 2 && frequencyOf(freqStreamSup.get(), max) == 1) ||
                (distinctCnt == 2 && min == 1 && frequencyOf(freqStreamSup.get(), min) == 1);
        return valid ? "YES" : "NO";
    }

    private static int frequencyOf(IntStream freq, int f) {
        return freq.reduce(0, (cnt, i) -> cnt + (i == f ? 1 : 0));
    }

    private static int[] letterFrequency(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            ++freq[c - 'a'];
        }
        return freq;
    }

    @Test
    void examples() {
        Assertions.assertEquals("YES", isValid("aaa"));
        Assertions.assertEquals("YES", isValid("aaabbb"));
        Assertions.assertEquals("YES", isValid("aab"));
        Assertions.assertEquals("YES", isValid("aabbccc"));
        Assertions.assertEquals("NO", isValid("aabbcccd"));
        Assertions.assertEquals("YES", isValid("aabbc"));
        Assertions.assertEquals("NO", isValid("aaabbbcc"));
    }
}
