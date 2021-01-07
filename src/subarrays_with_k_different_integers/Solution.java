package subarrays_with_k_different_integers;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Use two pointers to define a sliding window [i, j), and a bit set to mark which characters present in that range.
 *
 * Everytime ++j, ++i zero or any times to keep the characters between [i, j) unique.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        HashMap<Integer, Integer> nums = new HashMap<>();
        int n = A.length;

        int i = 0, j = 1, cnt = 1;
        nums.put(A[0], 1);

        int ans = 0;

        while (j < n) {
            int c = A[j++];
            if (!nums.containsKey(c)) {
                nums.put(c, 1);
                ++cnt;
            } else {
                nums.compute(c, (k, v) -> v + 1);
            }

            while (cnt > K) {
                assert nums.get(A[i]) > 0;
                nums.compute(A[i], (k, v) -> v - 1);
                if (nums.get(A[i]) == 0) {
                    --cnt;
                }
                ++i;
            }

            if (cnt == K) {
                ++ans;
                System.out.printf("#%d [%d, %d)%n", ans, i, j);
            }
        }

        return ans;
    }
}
