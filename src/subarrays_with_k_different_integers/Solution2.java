package subarrays_with_k_different_integers;

import java.util.HashMap;

/**
 * Refactor the previous solution by extracting a Window class.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution2 {
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
        Window win = new Window();

        int n = A.length;

        // [i, j) is the range of the sliding window;
        // cnt is the distinct count of numbers in the sliding window.
        // Init the window to [0, 1).
        int i = 0, j = 1;
        int ans = K == 1 ? 1 : 0;
        win.add(A[0]);

        while (j < n) {
            // add [j] to the window
            win.add(A[j++]);

            // If the window contains too many distinct numbers, increase i.
            while (win.distinctCount > K) {
                win.remove(A[i++]);
            }

            // Found a good subarray?
            if (win.distinctCount == K) {
                ++ans;

                // temporarily increase i to find other good subarrays that ends at j, if there are any.
                int iBak = i;
                while (i < j && win.query(A[i]) > 1) {
                    win.remove(A[i++]);
                    ++ans;
                }
                // restore i
                while (i > iBak) {
                    win.add(A[--i]);
                }
                assert i == iBak;
            }
        }

        return ans;
    }
}

