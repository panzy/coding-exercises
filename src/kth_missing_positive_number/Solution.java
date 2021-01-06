package kth_missing_positive_number;

/**
 * Iterate all positive number until the number of missing ones has reached k.
 *
 * This is inefficient in case of a sparse array. Because the number of iteration times can be much
 * larger than the array length.
 *
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class Solution {
    public int findKthPositive(int[] arr, int k) {
        int ans = 1;
        for (int num = 1, i = 0, missing = 0; missing < k; ++num) {
            if (i >= arr.length) {
                ans = num + (k - missing) - 1;
                break;
            } else if (num == arr[i]) {
                ++i;
            } else {
                if (++missing == k) {
                    ans = num;
                    break;
                }
            }
        }
        return ans;
    }
}
