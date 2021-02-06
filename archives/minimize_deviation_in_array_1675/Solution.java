package minimize_deviation_in_array_1675;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * Make all numbers be their minimum possible values at the beginning, so that we
 * only have to search in one direction -- increasing a number.
 *
 * Created by Zhiyong Pan on 2021-01-30.
 */
public class Solution {
    static class Num {
        int val;
        int orig;

        Num(int v, int o) {
            val = v;
            orig = o;
        }
    }

    public int minimumDeviation(int[] nums) {
        PriorityQueue<Num> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // The lower & upper bounds of the numbers.
        int L = 0, U = 0;

        // The minimal odd value.
        int minOddVal = 1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] % 2 == 1 && (minOddVal == 0 || nums[i] < minOddVal))
                minOddVal = nums[i];
        }

        // Make all numbers be their minimum possible values.
        // Since odd numbers can not be decreased, this process affects even numbers only.
        // By the way, attain U in this loop.
        for (int i = 0; i < nums.length; ++i) {
            int v = nums[i];
            while (v % 2 == 0 && v / 2 >= minOddVal) {
                v /= 2;
            }
            minHeap.add(new Num(v, nums[i]));

            if (U == 0 || U < v)
                U = v;
        }

        // Notice that minOddVal is not necessary the minimum of all, there may be smaller evens.
        L = minHeap.peek().val;

        // Initial answer, will be updated.
        int ans = U - L;

        // x2 the L as long as we can.
        // Notice that at a certain point, we don't know whether x2 will get us closer to the minimized deviation,
        // but we still do it because we known we will encounter it sooner or later during this numeration.
        while (ans > 0) {
            // Get the current max & min values.
            Num aObj = minHeap.poll();
            int a = aObj.val;

            if (a % 2 == 0 && a >= aObj.orig)
                break;

            int b = minHeap.peek().val;

            // a *= 2
            if (2 * a <= aObj.orig && aObj.orig <= U)
                a = aObj.orig; // This is just an optimization. It has no impact on the correctness.
            else
                a *= 2;

            U = Math.max(a, U);
            L = Math.min(a, b);
            aObj.val = a;
            minHeap.add(aObj);
            ans = Math.min(ans, U - L);
        }

        return ans;
    }
}
