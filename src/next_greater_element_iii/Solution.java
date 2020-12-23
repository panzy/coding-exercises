/* Zhiyong Pan, 2020-12-23 */
package next_greater_element_iii;

import java.util.Arrays;

public class Solution {
    public int nextGreaterElement(int n) {
        String s = Integer.toString(n);
        final int LEN = s.length();
        int[] digits = new int[LEN];

        for (int i = 0; i < LEN; ++i) {
            digits[i] = Character.digit(s.charAt(i), 10);
        }

        // Scan digits from right to left.
        //
        // For each [i], if there exists a j satisfying
        //  j > i and [j] > [i],
        // then swapping [i] and [j] yields a greater number.
        //
        // If we could find the smallest possible [j], then this number is relative small.
        //
        // And if we further reorder the digits inside range [i+1,LEN-1],
        // then this number is the smallest, i.e., the answer.
        boolean found = false;
        for (int i = LEN - 2; i >= 0; --i) {
            int bestJ = -1;
            for (int j = i + 1; j < LEN; ++j) {
                if (digits[j] > digits[i]) {
                    if (bestJ == -1 || digits[j] < digits[bestJ]) {
                        bestJ = j;
                    }
                }
            }
            if (bestJ != -1) {
                // swap [j] and [i]
                int t = digits[i];
                digits[i] = digits[bestJ];
                digits[bestJ] = t;
                // sort digits in range [i+1, LEN-1].
                Arrays.sort(digits, i + 1, LEN);
                // done
                found = true;
                break;
            }
        }

        if (found) {
            long ans = 0;
            int weight = 1;
            for (int i = digits.length - 1; i >= 0; --i) {
                ans += (long)digits[i] * weight;
                weight *= 10;
            }
            // if ans < 0, then overflow has happened
            return ans < Integer.MAX_VALUE ? (int)ans : -1;
        } else {
            return -1;
        }
    }
}
