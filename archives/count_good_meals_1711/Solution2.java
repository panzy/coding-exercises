package count_good_meals_1711;

import java.util.HashMap;

/**
 * For each number, calculate its possible pair mates and check them with the help of a hash map.
 *
 * We decide a number's pair mates to be not larger than the number itself. Thus the pair sum will not exceed
 * 2x of the number. So, if a number itself is not a power of two, it has at most one pair mate; otherwise, it
 * has two pair mates: zero and itself.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution2 {
    final static long mod = (long) (1e9 + 7);

    public int countPairs(int[] deliciousness) {
        HashMap<Integer, Integer> numCnts = new HashMap<>();
        long ans = 0;

        for (int d : deliciousness) {
            numCnts.put(d, numCnts.getOrDefault(d, 0) + 1);
        }

        // Pair candidates for the current number.
        int[] mates = new int[2];
        int mateCnt;

        for (int d : numCnts.keySet()) {
            // Find all d2 that d2 <= d and d2 + d is a power of 2.
            int targetPower = nextPowerOf2(d);
            if (targetPower == d) {
                // d itself is a power of 2, so it can pair with 0 as well as itself.
                mateCnt = 2;
                mates[0] = 0;
                mates[1] = d;
            } else if (targetPower - d < d) {
                // Pair with another number that is less than d.
                mateCnt = 1;
                mates[0] = targetPower - d;
            } else {
                // Don't pair with a greater number. Let the other number do it.
                mateCnt = 0;
            }

            for (int i = 0; i < mateCnt; ++i) {
                int d2 = mates[i];

                long cnt2 = numCnts.getOrDefault(d2, 0);
                if (cnt2 > 0) {
                    if (d != d2) {
                        ans += numCnts.get(d) * cnt2; // cross product
                    } else {
                        ans += cnt2 * (cnt2 - 1) / 2; // a 2-combination of this many duplicated numbers.
                    }
                }
            }
        }

        return (int) (ans % mod);
    }

    // https://www.geeksforgeeks.org/smallest-power-of-2-greater-than-or-equal-to-n/
    private static int nextPowerOf2(int n)
    {
        int count = 0;

        // First n in the below
        // condition is for the
        // case where n is 0
        if (n > 0 && (n & (n - 1)) == 0)
            return n;

        while(n != 0)
        {
            n >>= 1;
            count += 1;
        }

        return 1 << count;
    }
}
