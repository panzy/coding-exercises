package sort_colors;

/**
 * Insertion sort is simple in-place sort algorithm.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class SolutionFollowup1 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        for (int i = 0; i + 1 < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] > nums[j]) {
                    int t = nums[i];
                    nums[i] = nums[j];
                    nums[j] = t;
                }
            }
        }
    }
}
