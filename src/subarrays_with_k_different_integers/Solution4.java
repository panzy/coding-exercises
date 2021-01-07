package subarrays_with_k_different_integers;

import java.util.Arrays;

/**
 * Adapted upon the previous solution.
 *
 * Notice the constraint that 1 <= A[i] <= A.length.
 *
 * Instead of using a hash map, we can use an array. It's faster.
 *
 * Created by Zhiyong Pan on 2021-01-07.
 */
public class Solution4 {
    static class Window {
        // how many times does each number occurs in the sliding window?
        int[] nums;
        int distinctCount = 0;

        Window(int n) {
            nums = new int[n + 1];
            Arrays.fill(nums, 0);
        }

        void add(int x) {
            ++nums[x];
            if (nums[x] == 1)
                ++distinctCount;
        }

        void remove(int x) {
            --nums[x];
            if (nums[x] == 0)
                --distinctCount;
        }
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        int n = A.length;

        Window win = new Window(n); // the longest, valid window
        Window win2 = new Window(n); // the next window beside the shortest, valid window.

        // [i, j) is the range of the sliding window;
        // cnt is the distinct count of numbers in the sliding window.
        // Init the window to [0, 1).
        int i = 0, i2 = 0, j = 0;
        int ans = 0;

        while (j < n) {
            // add [j] to the window
            win.add(A[j]);
            win2.add(A[j]);
            ++j;

            // If the window contains too many distinct numbers, increase i.
            while (win.distinctCount > K) {
                win.remove(A[i++]);
            }
            while (win2.distinctCount >= K) {
                win2.remove(A[i2++]);
            }

            // Found a good subarray?
            if (win.distinctCount == K) {
                ans += (i2 - i);
            }
        }

        return ans;
    }
}
