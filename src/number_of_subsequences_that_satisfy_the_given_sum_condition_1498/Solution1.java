package number_of_subsequences_that_satisfy_the_given_sum_condition_1498;

import java.util.Arrays;

/**
 * Optimized based on the previous solution.
 *
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class Solution1 {
    final static int mod = (int) (1e9 + 7);

    private static class Node {
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

        // Number of valid subsequences.
        int ans = 0;

        for (Node i = list.next; i != null; i = i.next) {

            if (i.num + i.num > target)
                break;

            // Find the largest range (i,j) that i+j<=target.
            Node j = i;
            while (j.next != null && i.num + j.next.num <= target)
                j = j.next;

            // Got a pair of minimum and maximum.

            if (i.num == j.num) {
                // The subsequence contains one or more of this value and no other values.
                ans = (ans + bigPow(2, i.freq) - 1) % mod;
            } else {
                // How many elements are greater than a and less than b?
                int optionalElementCount = j.pos - (i.pos + i.freq);

                // Formula:
                // count =
                //         // the minimum appears at least once
                //         (2^i - 1) *
                //         // the maximum appears zero or more times
                //         2^j *
                //         // other numbers are free to appear or not
                //         2^optionalElementCount
                ans = (ans
                        + bigPow(2, i.freq + j.freq + optionalElementCount)
                        - bigPow(2, j.freq + optionalElementCount)) % mod;
            }
        }

        while (ans < 0)
            ans += mod;
        return ans;
    }

    // https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/discuss/1005857/C%2B%2B-short-solution
    static int bigPow(int v, int p) {
        if(p == 0) return 1;
        if(p == 1) return v;
        int h = bigPow(v, p>>1), f = (int)(((long)h * h) % mod);
        return ((p&1) == 1) ? (f * v) % mod : f;
    }
}
