package sort_colors;

import java.util.Arrays;

/**
 * It's possible to do an one-way sort only because the number of colors is limited.
 *
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class SolutionFollowup2 {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int c0 = 0; // the number of the 1st color
        int c1 = 0; // the number of the 2nd color

        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0)
                nums[c0++] = 0;
            else if (nums[i] == 1)
                ++c1;
        }

        Arrays.fill(nums, c0, c0 + c1,1);
        Arrays.fill(nums, c0 + c1, n,2);
    }
}
