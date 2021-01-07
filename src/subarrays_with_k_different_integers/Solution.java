package subarrays_with_k_different_integers;

import _lib.IntArrays;

import java.util.HashMap;
import java.util.function.BiFunction;

/**
 * Use two pointers to define a sliding window [i, j), a hash map to record which numbers occurs how many times
 * in that window, and a counter of the distinct numbers.
 *
 * We keep the window as long as possible (with the distinct count not exceeding K).
 *
 * For nested good subarrays, we establish this rule to enumerate them: every subarray aligns to a window's right edge.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        // how many times does each number occurs in the sliding window?
        HashMap<Integer, Integer> nums = new HashMap<>();

        int n = A.length;

        // [i, j) is the range of the sliding window;
        // cnt is the distinct count of numbers in the sliding window.
        // Init the window to [0, 1).
        int i = 0, j = 1, cnt = 1;
        int ans = K == 1 ? 1 : 0;
        nums.put(A[0], 1);
//        if (ans == 1) dump(A, i, j, ans);

        BiFunction<Integer, Integer, Integer> incCounter = (k, v) -> v + 1;
        BiFunction<Integer, Integer, Integer> decCounter = (k, v) -> v - 1;

        while (j < n) {
            // add [j] to the window
            int c = A[j++];
            if (!nums.containsKey(c) || nums.get(c) == 0) {
                nums.put(c, 1);
                ++cnt;
            } else {
                nums.compute(c, incCounter);
            }

            // If the window contains too many distinct numbers, increase i.
            while (cnt > K) {
                assert nums.get(A[i]) > 0;
                nums.compute(A[i], decCounter);
                if (nums.get(A[i]) == 0) {
                    --cnt;
                }
                ++i;
            }

            // Found a good subarray?
            if (cnt == K) {
                ++ans;
//                dump(A, i, j, ans);

                // temporarily increase i to find other good subarrays that ends at j, if there are any.
                int iBak = i;
                while (i < j && nums.get(A[i]) > 1) {
                    nums.compute(A[i], decCounter);
                    ++i;
                    ++ans;
//                    dump(A, i, j, ans);
                }
                // restore i
                while (i > iBak) {
                    nums.compute(A[--i], incCounter);
                }
                assert i == iBak;
            }
        }

        return ans;
    }

    private static void dump(int[] A, int i, int j, int no) {
        System.out.printf("#%d [%d, %d) {%s}%n", no, i, j, IntArrays.join(A, i, j, ", "));
    }
}
