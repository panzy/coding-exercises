package number_of_subsequences_that_satisfy_the_given_sum_condition_1498;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class Solution {
    final static BigInteger mod = BigInteger.valueOf((long) (1e9 + 7));
    final static BigInteger ONE = BigInteger.ONE;
    final static BigInteger TWO = BigInteger.TWO;

    static class Node {
        int num;
        int freq;
        int pos;

        Node next;
        Node prev;

        void connect(Node tail) {
            next = tail;
            tail.prev = this;
        }
    }

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;

        Node list = new Node(); // dummy head
        Node tail = list;
        int distinctCnt = 0; // for debug purpose

        Arrays.sort(nums);

        if (n > 0) {
            Node curr = new Node();
            curr.num = nums[0];
            curr.pos = 0;
            curr.freq = 0;
            tail.connect(curr);
            tail = curr;
            distinctCnt = 1;

            for (int i = 0; i < n; ++i) {
                if (nums[i] == curr.num) {
                    ++curr.freq;
                } else {
                    curr = new Node();
                    curr.num = nums[i];
                    curr.pos = i;
                    curr.freq = 1;
                    tail.connect(curr);
                    tail = curr;
                    ++distinctCnt;
                }
            }
        }

        // Number of invalid subsequences.
        BigInteger invalidCnt = BigInteger.valueOf(0);

        for (Node i = list.next; i != null; i = i.next) {

            // Find a pair (i,j) that i+j>target.
            for (Node j = tail; j.num >= i.num; j = j.prev) {
                if (i.num + j.num <= target)
                    break;

                // Got a pair of minimum and maximum.

                if (i.num == j.num) {
                    // The subsequence contains one or more of this value and no other values.
                    invalidCnt = invalidCnt
                            .add(TWO.modPow(BigInteger.valueOf(i.freq), mod))
                            .subtract(ONE);
                } else {
                    // How many elements are greater than a and less than b?
                    int optionalElementCount = j.pos - (i.pos + i.freq);

                    invalidCnt = invalidCnt.add(
                            // the minimum appears at least once
                            TWO.modPow(BigInteger.valueOf(i.freq), mod).subtract(ONE)
                                    // the maximum appears at least once
                                    .multiply(TWO.modPow(BigInteger.valueOf(j.freq), mod).subtract(ONE))
                                    // other numbers are free to appear or not
                                    .multiply(TWO.modPow(BigInteger.valueOf(optionalElementCount), mod))
                    );
                }
            }
        }

        return TWO.pow(n).subtract(ONE).subtract(invalidCnt).mod(mod).intValue();
    }

    @Test
    void test() {
        Assertions.assertEquals(352867371, numSubseq(new int[]{
                87,84,58,64,18,55,68,35,94,44,77,100,72,80,82,39,40,61,29,13,42,17,25,31,81,91,86,33,19,62,14,43,92,60,90,80,26,70,44,89,95,29,13,14,61,57,51,50,79,89,67,71,63,15,49,59,96,76,20,73,26,40,45,77,94,19,40,77,22,29,85,25,31,59,31,16,59,41,35,69,44,94,61,70,26,97,100,88,39,49,34,90,11,68,79,65,88,22,91,47,16,23,78,26,62,85,77,31,17,34,64,86,59,70,15,56,92,91,11,97,13,12,39,42,21,64,59,61,69,16,80
        }, 90));
        Assertions.assertEquals(963594139, numSubseq(new int[]{
                        6, 10, 12, 3, 29, 21, 12, 25, 17, 19, 16, 1, 2, 24, 9, 17, 25, 22, 12, 22, 26, 24, 24, 11, 3,
                        7, 24, 5, 15, 30, 23, 5, 20, 10, 19, 20, 9, 27, 11, 4, 23, 4, 4, 12, 22, 27, 16, 11, 26, 10, 23,
                        26, 16, 21, 24, 21, 17, 13, 21, 9, 16, 17, 27},
                26));
        Assertions.assertEquals(56, numSubseq(new int[]{7, 10, 7, 3, 7, 5, 4}, 12));
        Assertions.assertEquals(2, numSubseq(new int[]{5, 1}, 8));
        Assertions.assertEquals(0, numSubseq(new int[]{5, 5}, 8));
        Assertions.assertEquals(0, numSubseq(new int[]{5, 5, 5}, 8));
        Assertions.assertEquals(6, numSubseq(new int[]{3, 3, 6, 8}, 10));
        Assertions.assertEquals(4, numSubseq(new int[]{3, 5, 6, 7}, 9));
        Assertions.assertEquals(61, numSubseq(new int[]{2, 3, 3, 4, 6, 7}, 12));
        Assertions.assertEquals(127, numSubseq(new int[]{5, 2, 4, 1, 7, 6, 8}, 16));
    }

    @Test
    void testHuge() throws IOException, ParseException {
        Assertions.assertEquals(525465186,
                numSubseq(IntArrays.loadFromJsonFile(
                        "./src/number_of_subsequences_that_satisfy_the_given_sum_condition_1498/test-case-50.json"),
                        3759));
    }
}
