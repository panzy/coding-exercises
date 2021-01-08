package reverse_bits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class ReverseBits {
    Solution solution = new Solution();

    /**
     * Input: n = 00000010100101000001111010011100
     * Output:    964176192 (00111001011110000010100101000000)
     * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.
     */
    @Test
    void example1() {
        Assertions.assertEquals(964176192, solution.reverseBits(43261596));
    }

    /**
     * Input: n = 11111111111111111111111111111101
     * Output:   3221225471 (10111111111111111111111111111111)
     * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
     *
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
     */
    @Test
    void example2() {
        Assertions.assertEquals(-1073741825, solution.reverseBits(-3));
    }
}
