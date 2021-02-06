package kth_missing_positive_number;

/**
 * Iterate the given array until it's exhausted or a big enough missing number has been found,
 * then carefully do some calculations.
 *
 * This approach is faster than the previous one because it relies more on arithmetic than on loop.
 *
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class Solution2 {
    public int findKthPositive(int[] arr, int k) {
        int missing = arr[0] - 1; // how many are missing till arr[i]?
        int i = 1; // notice it might be the end of the array

        for (; missing < k && i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] > 1) {
                missing += arr[i] - arr[i - 1] - 1;
            }
        }

        if (missing < k) {
            return arr[i - 1] + k - missing;
        } else if (missing == k) {
            return arr[i - 1] - 1;
        } else {
            return (arr[i - 1] - 1) - (missing - k);
        }
    }
}
