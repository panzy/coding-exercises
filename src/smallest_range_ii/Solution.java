/**
 * This version of solution is based on the official one from LeetCode website.
 *
 * The code is so short. It took me a while to understand what's going on.
 *
 * Then it became deadly simple.
 *
 * For any array A and number K, the desired array B is always generated by one of the following two methods:
 *
 *      1. for any i, B[i] = A[i] + K or A[i] - K.
 *      2. for some i, B[i] = A[i] + K; for the rest, B[i] = A[i] - K.
 *
 * We determine which method should be used by trying both of them to see which one works better.
 *
 * For method #2, we determine which elements should add K and which should subtract K by, again, trying all of them to
 * see which one works the best. Fortunately, the number of possible arrangements is not 2^N, but N.
 *
 * That's because, if A is sorted, then all those elements which need to +K will be on the left part, and -K elements
 * will be on the right side. Any element on the left part subtracting K will enlarge the range.
 *
 * If elements between [0,i] add K and [i+1,N-1] subtract K, then the range is determined by these four numbers:
 *      B[0], B[i], B[i+1], B[N-1].
 * Other elements don't matter here because they either between B[0] and B[i], or between B[i+1], B[N-1]. In other words,
 * a range containing these four numbers will contain the whole B.
 *
 * Given the facts that B[0] <= B[i] and B[i+1] <= B[N-1], this range is defined by:
 *      low = min(B[0], B[i+1])
 *      high = max(B[i], B[N-1])
 *
 * --
 * Zhiyong Pan, 2020-12-24
 */
package smallest_range_ii;

import java.util.Arrays;

public class Solution {
    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int N = A.length;

        // The answer is initialized to the range of B generated by method #1.
        // We'll update it whenever we see a smaller range generated by method #2 with a particular i.
        int ans = A[N - 1] - A[0];

        for (int i = 0; i < N - 1; ++i) {
            int lo = Math.min(A[0] + K, A[i + 1] - K); // B[0] = A[0] + K, B[i+1] = A[i+1] - K.
            int hi = Math.max(A[i] + K, A[N - 1] - K); // B[i] = A[i] + K, B[N-1] = A[N-1] - K.
            if (ans > hi - lo) {
                ans = hi - lo;
            }
        }

        return ans;
    }
}