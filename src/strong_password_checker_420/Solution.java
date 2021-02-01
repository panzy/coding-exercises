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

    public int strongPasswordChecker(String password) {
        bestAnswer = Integer.MAX_VALUE;

        return check(password,
                true, true, true, 0,
                0, "", 0);
    }

    private int check(String password,
                      boolean requireDigit,
                      boolean requireLowercase,
                      boolean requireUppercase,
                      int dynamicChars,
                      int prevLen,
                      String prev,
                      int prevCost) {

//        System.out.println(prev + " + " + password + " (" + requireDigit + "," + requireLowercase + "," + requireUppercase + ")");

        // Invalid password. Abort.
        if (endsWithAAA(prev))
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

        if (password.length() == 0) {
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
        if (prev.endsWith(password.charAt(0) + "" + password.charAt(0))) {
            // What if we insert a char before this char?
            cost = Math.min(cost, check(password,
                    requireDigit, requireLowercase, requireUppercase, dynamicChars + 1, prevLen + 1, prev + "?", prevCost + 1));

            // What if we remove this char?
            cost = Math.min(cost, check(password.substring(1),
                    requireDigit, requireLowercase, requireUppercase, dynamicChars, prevLen, prev, prevCost + 1));

            // What if we change this char?
            cost = Math.min(cost, check(password.substring(1),
                    requireDigit, requireLowercase, requireUppercase, dynamicChars + 1, prevLen + 1, prev + "?", prevCost + 1));
        } else {
            // Can the current char fulfil a certain type of requirement?
            boolean fulfillDigit = requireDigit && !Character.isDigit(password.charAt(0));
            boolean fulfilLowercase = requireLowercase && !Character.isLowerCase(password.charAt(0));
            boolean fulfilUppercase = requireUppercase && !Character.isUpperCase(password.charAt(0));

            // What if we reserve this char?
            cost = Math.min(cost, check(password.substring(1),
                    fulfillDigit,
                    fulfilLowercase,
                    fulfilUppercase,
                    dynamicChars,
                    prevLen + 1,
                    prev + password.charAt(0),
                    prevCost));

            // What if we change this char?
            // (Only makes sense when some type of char is required.)
            if (fulfillDigit || fulfilLowercase || fulfilUppercase) {
                cost = Math.min(cost, check(password.substring(1),
                        requireDigit, requireLowercase, requireUppercase, dynamicChars + 1, prevLen + 1, prev + "?", prevCost + 1));
            }

            // We don't bother removing a char at this moment.
        }

        return cost;
    }

    private boolean endsWithAAA(String s) {
        int n = s.length();
        if (n < 3)
            return false;
        return  s.charAt(n - 3) == s.charAt(n - 1) && s.charAt(n - 2) == s.charAt(n - 1);
    }

    @Test
    void testRecursion() {
        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(0, check("abcAB0123", true, true, true, 0, 0, "", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("B", false, false, false, 0, 5, "aa0BB", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(3, check("a", true, false, true, 0, 2, "aa", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(0, check("", false, false, false, 0, 6, "aa0BB?", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("BB", false, false, false, 0, 4, "aa0B", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("BBB", false, false, true, 0, 3, "aa0", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aBBB", true, false, true, 0, 2, "aa", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aaBBB", true, false, true, 0, 1, "a", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aaaBBB", true, true, true, 0, 0, "", 0));
    }
}
