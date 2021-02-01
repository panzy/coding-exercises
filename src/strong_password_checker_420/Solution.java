package strong_password_checker_420;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Seems to be correct, but very slow.
 *
 * Created by Zhiyong Pan on 2021-01-31.
 */
public class Solution {
    int bestAnswer;
    int minLen = 6;
    int maxLen = 20;
    int n;
    char[] A;

    public int strongPasswordChecker(String password) {
        bestAnswer = Integer.MAX_VALUE;
        n = password.length();
        A = password.toCharArray();

        return check(0, true, true, true, 0, 0, 0, '?', '?', '?');
    }

    private int check(int offset,
                      boolean requireDigit,
                      boolean requireLowercase,
                      boolean requireUppercase,
                      int dynamicChars,
                      int prevLen,
                      int prevCost, char prevA,
                      char prevB,
                      char prevC) {

        // Invalid password. Abort.
        if (prevA != '?' && prevA == prevB && prevB == prevC)
            return Integer.MAX_VALUE;

        if (prevCost >= bestAnswer)
            return bestAnswer;

        // Fulfill the requirements of particular types of char if there are dynamic chars available.
        if (dynamicChars > 0 && requireDigit) {
            requireDigit = false;
            --dynamicChars;
        }
        if (dynamicChars > 0 && requireLowercase) {
            requireLowercase = false;
            --dynamicChars;
        }
        if (dynamicChars > 0 && requireUppercase) {
            requireUppercase = false;
            --dynamicChars;
        }

        if (offset == n) {
            int appendCost = 0;
            if (requireDigit) {
                assert dynamicChars == 0;
                ++prevLen;
                ++appendCost;
            }
            if (requireLowercase) {
                assert dynamicChars == 0;
                ++prevLen;
                ++appendCost;
            }
            if (requireUppercase) {
                assert dynamicChars == 0;
                ++prevLen;
                ++appendCost;
            }

            bestAnswer = Math.min(bestAnswer, prevCost + appendCost +
                    Math.max(0, minLen - prevLen) +
                    Math.max(0, prevLen - maxLen));
            return bestAnswer;
        }

        int cost = Integer.MAX_VALUE;

        // What if we insert a char before this char or remove this car?
        // (Only makes sense if concatenating prev and password will result in an AAA.
        if (prevB == prevC && prevC == A[offset]) {
            // What if we insert a char before this char?
            cost = Math.min(cost, check(offset,
                    requireDigit, requireLowercase, requireUppercase,
                    dynamicChars + 1, prevLen + 1, prevCost + 1, prevB, prevC, '?'
            ));

            // What if we remove this char?
            cost = Math.min(cost, check(offset + 1,
                    requireDigit, requireLowercase, requireUppercase,
                    dynamicChars, prevLen, prevCost + 1, prevA, prevB, prevC
            ));

            // What if we change this char?
            cost = Math.min(cost, check(offset + 1, requireDigit, requireLowercase, requireUppercase,
                    dynamicChars + 1, prevLen + 1, prevCost + 1, prevB, prevC, '?'
            ));
        } else {
            // Can the current char fulfil a certain type of requirement?
            boolean fulfillDigit = requireDigit && !Character.isDigit(A[offset]);
            boolean fulfilLowercase = requireLowercase && !Character.isLowerCase(A[offset]);
            boolean fulfilUppercase = requireUppercase && !Character.isUpperCase(A[offset]);

            // What if we reserve this char?
            cost = Math.min(cost, check(offset + 1, fulfillDigit, fulfilLowercase, fulfilUppercase,
                    dynamicChars, prevLen + 1, prevCost, prevB, prevC, A[offset]
            ));

            // What if we change this char?
            // (Only makes sense when some type of char is required.)
            if (fulfillDigit || fulfilLowercase || fulfilUppercase) {
                cost = Math.min(cost, check(offset + 1, requireDigit, requireLowercase, requireUppercase,
                        dynamicChars + 1, prevLen + 1, prevCost + 1, prevB, prevC, '?'
                ));
            }

            // We don't bother removing a char at this moment.
        }

        return cost;
    }

    @Test
    void testRecursion() {
        bestAnswer = Integer.MAX_VALUE;
        A = "abcAB0123".toCharArray();
        n = A.length;
        Assertions.assertEquals(0, check(0, true, true, true, 0, 0, 0, '?', '?', '?'));

        bestAnswer = Integer.MAX_VALUE;
        A = "B".toCharArray();
        n = A.length;
        Assertions.assertEquals(1, check(0, false, false, false, 0, 5, 0, '0', 'B', 'B'));

        bestAnswer = Integer.MAX_VALUE;
        A = "a".toCharArray();
        n = A.length;
        Assertions.assertEquals(3, check(0, true, false, true, 0, 2, 0, '?', 'a', 'a'));

        bestAnswer = Integer.MAX_VALUE;
        A = "".toCharArray();
        n = A.length;
        Assertions.assertEquals(0, check(0, false, false, false, 0, 6, 0, 'B', 'B', '?'));

        bestAnswer = Integer.MAX_VALUE;
        A = "BB".toCharArray();
        n = A.length;
        Assertions.assertEquals(1, check(0, false, false, false, 0, 4, 0, 'a', '0', 'B'));

        bestAnswer = Integer.MAX_VALUE;
        A = "BBB".toCharArray();
        n = A.length;
        Assertions.assertEquals(1, check(0, false, false, true, 0, 3, 0, 'a', 'a', '0'));

        bestAnswer = Integer.MAX_VALUE;
        A = "aBBB".toCharArray();
        n = A.length;
        Assertions.assertEquals(2, check(0, true, false, true, 0, 2, 0, '?', 'a', 'a'));

        bestAnswer = Integer.MAX_VALUE;
        A = "aaBBB".toCharArray();
        n = A.length;
        Assertions.assertEquals(2, check(0, true, false, true, 0, 1, 0, '?', 'a', 'a'));

        bestAnswer = Integer.MAX_VALUE;
        A = "aaaBBB".toCharArray();
        n = A.length;
        Assertions.assertEquals(2, check(0, true, true, true, 0, 0, 0, '?', '?', '?'));
    }
}
