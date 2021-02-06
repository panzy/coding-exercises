package longest_harmonious_subsequence_594;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */
class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        // For a harmonious sub array:
        //  1. begin < begin2 < end
        //  2. [begin] + 1 == [begin2] < [end]
        int begin = 0, begin2 = 0, end = 1;

        int ans = 0;

        while (begin2 < n - 1) {
            while (begin2 < n && nums[begin2] == nums[begin])
                ++begin2;
            if (begin2 < n && nums[begin2] - 1 == nums[begin]) {
                end = begin2;
                while (end < n && nums[end] == nums[begin2])
                    ++end;
                ans = Math.max(ans, end - begin);

                if (end < n && nums[end] - 1 == nums[begin2]) {
                    begin = begin2;
                    begin2 = end;
                } else {
                    begin = begin2 = end;
                }
            } else {
                begin = begin2;
            }
        }

        return ans;
    }
}
