package subarrays_with_k_different_integers;

import java.util.HashMap;

/**
 * Optimized upon the previous solution.
 *
 * Instead of using one sliding window, this time we use two: [i, j) and [i2, j),
 * with [i, j) being the longest good subarray that ends at j, and [i2, j) being the shortest.
 *
 * By introducing another window ends at the same point, we recognize that
 * for every int p between i and i2, [p, j) is a good, nested subarray.
 * So we don't have to simulate moving i to i2 one by one and reverse all that for each j,
 * as we did in the previous solution, which is painful when the interval between i and i2 is very large.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution3 {
    static class Window {
        // how many times does each number occurs in the sliding window?
        HashMap<Integer, Integer> nums = new HashMap<>();
        int distinctCount = 0;

        void add(int x) {
            nums.put(x, nums.getOrDefault(x, 0) + 1);
            if (nums.get(x) == 1)
                ++distinctCount;
        }

        void remove(int x) {
            nums.put(x, nums.get(x) - 1);
            if (nums.get(x) == 0)
                --distinctCount;
        }

        int query(int x) {
            return nums.getOrDefault(x, 0);
        }
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        Window win = new Window(); // longest
        Window win2 = new Window(); // shortest

        int n = A.length;

        // [i, j) is the range of the sliding window;
        // cnt is the distinct count of numbers in the sliding window.
        // Init the window to [0, 1).
        int i = 0, i2 = 0, j = 1;
        int ans = K == 1 ? 1 : 0;
        win.add(A[0]);
        win2.add(A[0]);

        while (j < n) {
            // add [j] to the window
            win.add(A[j]);
            win2.add(A[j]);
            ++j;

            // If the window contains too many distinct numbers, increase i.
            while (win.distinctCount > K) {
                win.remove(A[i++]);
            }
            while (win2.distinctCount > K) {
                win2.remove(A[i2++]);
            }
            while (win2.query(A[i2]) > 1) {
                win2.remove(A[i2++]);
            }

            // Found a good subarray?
            if (win.distinctCount == K) {
                ans += 1 + (i2 - i);
            }
        }

        return ans;
    }
}
