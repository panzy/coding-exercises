package recursive_digit_sum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Recursive Digit Sum
 * https://www.hackerrank.com/challenges/recursive-digit-sum
 *
 * Created by Zhiyong Pan on 2021-02-11.
 */
public class SuperDigit {
    static int superDigit(String n, int k) {
        long sum = 0;
        for (char c : n.toCharArray()) {
            sum += c - '0';
        }

        return superDigit(Long.toString(sum * k).toCharArray());
    }

    static int superDigit(char[] n) {
        if (n.length == 1)
            return n[0] - '0';

        long sum = 0;
        for (char c : n) {
            sum += c - '0';
        }
        return superDigit(Long.toString(sum).toCharArray());
    }

    @Test
    void test() {
        Assertions.assertEquals(3, superDigit("148", 3));
    }
}
