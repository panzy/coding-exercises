package smallest_range_i;

public class Solution {
    public static int smallestRangeI(int[] A, int K) {
        int minVal = A[0];
        int maxVal = A[0];

        for (int i = 1; i < A.length; ++i) {
            if (minVal > A[i])
                minVal = A[i];
            if (maxVal < A[i])
                maxVal = A[i];
        }

        int hi = maxVal - K;
        int lo = minVal + K;
        return hi > lo ? hi - lo : 0;
    }
}
