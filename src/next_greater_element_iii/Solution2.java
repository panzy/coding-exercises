/* Zhiyong Pan, 2020-12-23 */
package next_greater_element_iii;

public class Solution2 {
    public int nextGreaterElement(int n) {
        String s = Integer.toString(n);
        final int LEN = s.length();
        int[] digits = new int[LEN];

        for (int i = 0; i < LEN; ++i) {
            digits[i] = Character.digit(s.charAt(i), 10);
        }

        // The idea is the same as the previous solution but the implementation
        // has been improved, being inspired by C++ std::next_permutation
        // (https://gcc.gnu.org/onlinedocs/libstdc++/libstdc++-html-USERS-4.4/a01347.html#:~:text=next_permutation)
        //
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
            // Optimization #1:
            // If [i]>=[i+1], we can assert that there is no |j| for this |i|.
            // Because if there was, we'd have already found the answer.
            if (digits[i] >= digits[i + 1]) continue;

            // Optimization #2:
            // To find the best |j|, we don't have to scan whole range of [i+1,LEN-1].
            // Because basing on Optimization #1, we can assert that the range is in desc order.
            // So, the rightmost |j| is the best |j|.
            int j = LEN - 1;
            while (digits[j] <= digits[i]) --j;

            // swap [j] and [i]
            int t = digits[i];
            digits[i] = digits[j];
            digits[j] = t;

            // sort digits in range [i+1, LEN-1].
            //
            // Optimization #3:
            // Note that at this point, the range is guaranteed to be in desc order
            // so there's no need to perform a sort algorithm. Reversing is enough.
            reverse(digits, i + 1, LEN);

            // done
            found = true;
            break;
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

    static void reverse(int[] a, int fromIndex, int toIndex) {
        for (int i = fromIndex, j = --toIndex; i < j; ++i, --j) {
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}
