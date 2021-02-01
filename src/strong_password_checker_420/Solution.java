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
                true, true, true,
                0, "", 0);
    }

    private int check(String password,
                      boolean requireDigit,
                      boolean requireLowercase,
                      boolean requireUppercase,
                      int dynamicChars,
                      String prev,
                      int prevCost) {

//        System.out.println(prev + " + " + password + " (" + requireDigit + "," + requireLowercase + "," + requireUppercase + ")");

        // Invalid password. Abort.
        if (indexOfAAA(prev) != -1)
            return Integer.MAX_VALUE;

        if (prevCost >= bestAnswer)
            return bestAnswer;

        if (password.length() == 0) {
            int appendCost = 0;
            if (requireDigit) {
                if (dynamicChars > 0) {
                    --dynamicChars;
                } else {
                    prev += "?";
                    ++appendCost;
                }
            }
            if (requireLowercase) {
                if (dynamicChars > 0) {
                    --dynamicChars;
                } else {
                    prev += "?";
                    ++appendCost;
                }
            }
            if (requireUppercase) {
                if (dynamicChars > 0) {
                    --dynamicChars;
                } else {
                    prev += "?";
                    ++appendCost;
                }
            }

            bestAnswer = Math.min(bestAnswer, prevCost + appendCost +
                    Math.max(0, minLen - prev.length()) +
                    Math.max(0, prev.length() - maxLen));
            return bestAnswer;
        }

        if (!requireDigit && !requireLowercase && !requireUppercase && indexOfAAA(prev + password) == -1) {
            int ans;
            int n = (prev + password).length();
            if (n > maxLen)
                ans = prevCost + n - maxLen;
            else if (n < minLen)
                ans = prevCost + minLen - n;
            else
                ans = prevCost;

            bestAnswer = Math.min(bestAnswer, ans);
            return bestAnswer;
        }

        int cost = Integer.MAX_VALUE;


        // What if we insert a char before this char or remove this car?
        // (Only makes sense if concatenating prev and password will result in an AAA.
        if (prev.endsWith(password.charAt(0) + "" + password.charAt(0))) {
            // What if we insert a char before this char?
            cost = Math.min(cost, check(password,
                    requireDigit, requireLowercase, requireUppercase, dynamicChars + 1, prev + "?", prevCost + 1));

            // What if we remove this char?
            cost = Math.min(cost, check(password.substring(1),
                    requireDigit, requireLowercase, requireUppercase, dynamicChars, prev, prevCost + 1));
        } else {
            // What if we reserve this char?
            cost = Math.min(cost, check(password.substring(1),
                    requireDigit && !Character.isDigit(password.charAt(0)),
                    requireLowercase && !Character.isLowerCase(password.charAt(0)),
                    requireUppercase && !Character.isUpperCase(password.charAt(0)),
                    dynamicChars,
                    prev + password.charAt(0),
                    prevCost));
        }

        // What if we change this char?
        // (Only makes sense when we don't need this char.)
        if ((prev + password).length() > minLen &&
                (!requireDigit || !Character.isDigit(password.charAt(0))) &&
                (!requireLowercase || !Character.isLowerCase(password.charAt(0))) &&
                (!requireUppercase || !Character.isUpperCase(password.charAt(0)))
        ) {
            cost = Math.min(cost, check(password.substring(1),
                    requireDigit, requireLowercase, requireUppercase, dynamicChars + 1, prev + "?", prevCost + 1));
        }

        return cost;
    }

    private int indexOfAAA(String s) {
        int n = s.length();
        for (int i = 0; i + 2 < n; ++i) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == s.charAt(i + 2)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    void testRecursion() {
        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(0, check("abcAB0123", true, true, true, 0, "", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("B", false, false, false, 0, "aa0BB", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(3, check("a", true, false, true, 0, "aa", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(0, check("", false, false, false, 0, "aa0BB?", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("BB", false, false, false, 0, "aa0B", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(1, check("BBB", false, false, true, 0, "aa0", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aBBB", true, false, true, 0, "aa", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aaBBB", true, false, true, 0, "a", 0));

        bestAnswer = Integer.MAX_VALUE;
        Assertions.assertEquals(2, check("aaaBBB", true, true, true, 0, "", 0));
    }
}
