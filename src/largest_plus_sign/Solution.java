package largest_plus_sign;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Adapted from {@link maximal_rectangle.Solution2}.
 *
 * Consider each cell as a centroid so we can iterate all the plus signs.
 *
 * Created by Zhiyong Pan on 2021-01-05.
 */
public class Solution {
    /**
     * N will be an integer in the range [1, 500].
     * mines will have length at most 5000.
     * mines[i] will be length 2 and consist of integers in the range [0, N-1].
     */
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N < 1) return 0;

        // store the array of mined points in a bitset to allow access by coordinates.
        BitSet minesSet = new BitSet(N * N);
        for (int[] pnt : mines)
            minesSet.set(pnt[0] * N + pnt[1]);

        int ans = 0;

        // from a point matrix[r][c], there are three arms:
        // (1) upward size = height[c]
        // (2) leftward size = c - left[c] + 1
        // (3) right size = right[c] - c
        int[] height = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];

        // heights are accumulated on previous rows
        Arrays.fill(height, 0);

        // lefts and rights are independent on previous rows
        // -- this is where this problem is simpler than the rectangle one.
        // So they will being built from scratch for each row and we don't have to init them now.

        for (int r = 0; r < N; ++r) {

            // update height and left
            for (int c = 0, segmentBegin = 0; c < N; ++c) {
                if (!minesSet.get(r * N + c)) {
                    ++height[c]; // +1 on the height[c] of the previous row
                    left[c] = segmentBegin;
                } else {
                    height[c] = 0; // the rect collapsed
                    left[c] = N;
                    segmentBegin = c + 1; // current segment ends here, move to the next
                }
            }

            // update right
            for (int c = N - 1, segmentEnd = N; c >= 0; --c) {
                if (!minesSet.get(r * N + c)) {
                    right[c] = segmentEnd;
                } else {
                    right[c] = 0; // the right pos doesn't matter for this row, but matters for the next row
                    segmentEnd = c;
                }
            }

            // collect the maximal plus size produced by this row
            for (int c = 0; c < N; ++c) {
                int size = height[c];
                if (size <= ans)
                    continue;

                size = Math.min(size, N - r); // maximal depth (downward-arm length)
                if (size <= ans)
                    continue;

                size = Math.min(size, c - left[c] + 1);
                if (size <= ans)
                    continue;

                size = Math.min(size, right[c] - c);
                if (size <= ans)
                    continue;

                // check downwards
                for (int depth = 1; r + depth < N && depth < size; ++depth) {
                    if (minesSet.get((r + depth) * N + c)) {
                        size = depth;
                        break;
                    }
                }

                ans = Math.max(ans, size);
            }
        }

        return ans;
    }
}
