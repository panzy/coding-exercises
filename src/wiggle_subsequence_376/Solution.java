package wiggle_subsequence_376;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class Solution {
    public int wiggleMaxLength(int[] nums) {

        if (nums.length <= 1)
            return nums.length;

        int ans = 1;
        int desiredDirection = 0; // 0=any, 1=up, -1=down
        int i = 1;

        for (; i < nums.length; ++i) {
            // Notice that [i-1] is always the last element of the target subsequence
            // even though it might not have contributed to the length, in which case
            // it will at least replace the current tail and make it easier to encounter a desired
            // direction in the future.
            int newDirection = direction(nums[i - 1], nums[i]);

            if (newDirection != 0 && (desiredDirection == 0 || newDirection == desiredDirection)) {
                ++ans;
                desiredDirection = -newDirection;
            }
        }

        return ans;
    }

    int direction(int from, int to) {
        if (from < to)
            return -1;
        else if (from == to)
            return 0;
        else
            return 1;
    }

    @Test
    void example1() {
        Assertions.assertEquals(67, wiggleMaxLength(new int[]{33, 53, 12, 64, 50, 41, 45, 21, 97, 35, 47, 92, 39, 0, 93, 55, 40, 46, 69, 42, 6, 95, 51, 68, 72, 9, 32, 84, 34, 64, 6, 2, 26, 98, 3, 43, 30, 60, 3, 68, 82, 9, 97, 19, 27, 98, 99, 4, 30, 96, 37, 9, 78, 43, 64, 4, 65, 30, 84, 90, 87, 64, 18, 50, 60, 1, 40, 32, 48, 50, 76, 100, 57, 29, 63, 53, 46, 57, 93, 98, 42, 80, 82, 9, 41, 55, 69, 84, 82, 79, 30, 79, 18, 97, 67, 23, 52, 38, 74, 15}));
        Assertions.assertEquals(1, wiggleMaxLength(new int[]{0, 0}));
        Assertions.assertEquals(7, wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        Assertions.assertEquals(6, wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        Assertions.assertEquals(2, wiggleMaxLength(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}
