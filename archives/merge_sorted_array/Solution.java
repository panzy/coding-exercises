package merge_sorted_array;

/**
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0; // the first element of nums1 that has not been merged
        int j = 0; // the first element of nums2 that has not been merged
        int k = 0; // the end of the merged array

        if (m == 0) {
            // will copy nums2 to nums1 later.
            // nothing to do at this moment
        } else if (n == 0) {
            // nothing to do
        } else if (nums1[m - 1] < nums2[0]) {
            // no merge is needed, just concatenate.
            i = k = m;
        } else {
            // move nums1 elements by n towards the end of the array, making room for merging
            for (int p = m - 1; p >= 0; --p) {
                nums1[p + n] = nums1[p];
            }
            i = nums1.length - m;
            m = i + m; // because the original nums1 elements have been moved towards the end

            while (i < m && j < n) {
                if (nums1[i] <= nums2[j]) {
                    nums1[k++] = nums1[i++];
                } else {
                    nums1[k++] = nums2[j++];
                }
            }
        }

        if (i < m)
            assert k == i;

        // copy what's remained in nums2
        while (j < n) {
            nums1[k++] = nums2[j++];
        }
    }
}
