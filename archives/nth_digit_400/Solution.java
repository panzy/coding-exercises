package nth_digit_400;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-02-02.
 */
public class Solution {
    public int findNthDigit(int n) {
        int numWidth = 1; // how many digits in a number?
        long digitsOfWidth = 9; // total number digits digits of all numbers of this width.
        long sum = 0; // total number of digits of all widths.

        // Consume all the numbers of a certain width.
        while (sum + digitsOfWidth < n) {
            sum += digitsOfWidth;
            digitsOfWidth = digitsOfWidth / numWidth * (numWidth + 1) * 10;
            ++numWidth;
        }

        // Now, the n-th digit is hidden in a number with width = |numWidth|.
        assert numWidth < 11;

        // Locate where this number is.
        // Notice that if n == sum, then we have just passed that number, that would require a different logic to handle,
        // so we avoid this situation.
        long numIdx = (n - sum) / numWidth;
        if ((n - sum) % numWidth == 0)
            --numIdx;
        sum += numIdx * numWidth;
        assert n > sum;

        // Calculate which number this is.
        long num = (long) (Math.pow(10, numWidth - 1) + numIdx);
        // The answer is at this digit position from the left.
        long digitIdx = n - sum - 1;
        return (int) (num / (int) Math.pow(10, numWidth - digitIdx - 1) % 10);
    }

    @Test
    void testConcept() {
        Assertions.assertEquals(2, findNthDigit(15));
        Assertions.assertEquals(1, findNthDigit(14));
        Assertions.assertEquals(1, findNthDigit(13));
        Assertions.assertEquals(1, findNthDigit(12));
        Assertions.assertEquals(0, findNthDigit(11));
        Assertions.assertEquals(1, findNthDigit(10));
        Assertions.assertEquals(9, findNthDigit(9));
        Assertions.assertEquals(2, findNthDigit(2));
        Assertions.assertEquals(1, findNthDigit(1));
        findNthDigit((int) (Math.pow(2, 31) - 1));
    }

    @Test
    void testVerifyWithProgram() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; ++i) {
            sb.append(i);
        }

        int maxN = sb.length();
        for (int i = 1; i < maxN; ++i) {
            Assertions.assertEquals(sb.charAt(i) - '0', findNthDigit(i));
        }
    }
}
