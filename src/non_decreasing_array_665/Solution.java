package non_decreasing_array_665;

/**
 * Created by Zhiyong Pan on 2021-02-03.
 */
class Solution {
    public boolean checkPossibility(int[] nums) {
        int modified = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                if (i > 0) {
                    if (nums[i - 1] <= nums[i + 1]) {
                        // Our best option is lowering [i] to [i - 1].
                        nums[i] = nums[i - 1];
                    } else {
                        // Our only option is raising [i+1] to [i].
                        nums[i + 1] = nums[i];
                    }
                } else {
                    // Our best option is lowering [i].
                    nums[i] = nums[i + 1];
                }

                if (++modified > 1)
                    return false;
            }
        }
        return true;
    }
}