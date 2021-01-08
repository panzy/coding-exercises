package reverse_bits;

/**
 * Reverse the bits as if they were in an array.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        for (int i = 0, j = 31; i < j; ++i, --j) {
            int bi = (n >> i) & 1;
            int bj = (n >> j) & 1;
            if (bi != bj) {
                if (bi == 0) {
                    n |= 1 << i; // 0->1
                    n ^= 1 << j; // 1->0
                } else {
                    n ^= 1 << i; // 1->0
                    n |= 1 << j; // 0->1
                }
            }
        }
        return n;
    }
}
