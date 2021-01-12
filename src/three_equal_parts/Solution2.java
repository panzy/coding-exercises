package three_equal_parts;

/**
 * Rewrite from the brilliant official solution.
 * https://leetcode.com/problems/three-equal-parts/
 *
 * Created by Zhiyong Pan on 2021-01-12.
 */
public class Solution2 {
    public int[] threeEqualParts(int[] A) {
        int[] IMP = {-1, -1}; // answer when it's impossible
        int N = A.length;
        int S = 0; // total number of ones
        int T = 0; // S/3
        // The positions of the first and last one's of each part.
        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1;
        // The number of tailing zeros of each part.
        int z = 0;

        for (int x : A) S += x;

        if (S % 3 != 0)
            return IMP;

        T = S / 3;

        if (T == 0)
            return new int[]{0, N - 1}; // multiple answers, this is just one of them

        for (int i = 0, n = 0; i < N; ++i) {
            if (A[i] == 1) {
                ++n;
                if (n == 1) i1 = i;
                if (n == T) j1 = i;
                if (n == T + 1) i2 = i;
                if (n == 2 * T) j2 = i;
                if (n == 2 * T + 1) i3 = i;
                if (n == 3 * T) j3 = i;
            }
        }

        z = N - 1 - j3;

        // Check overlaps.
        if (j1 + z >= i2) return IMP;
        if (j2 + z >= i3) return IMP;

        // Check whether these three ranges are the same:
        // [i1 + 1, j1),
        // [i2 + 1, j2),
        // [i3 + 1, j3)
        while (i1 < j1) {
            if (A[i1] != A[i2] || A[i2] != A[i3])
                return IMP;
            ++i1;
            ++i2;
            ++i3;
        }

        return new int[]{j1 + z, j2 + z + 1};
    }
}
