package find_first_and_last_position_of_element_in_sorted_array_34;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int pos = Arrays.binarySearch(nums, target);
        if (pos < 0)
            return new int[]{-1, -1};
        else
            return new int[]{
                    leftEdge(nums, target, 0, pos),
                    rightEdge(nums, target, pos, nums.length - 1),
            };
    }

    int leftEdge(int[] nums, int target, int lo, int hi) {

        while (lo <= hi) {
            int mi = (lo + hi) / 2;
            if (nums[mi] == target) {
                if (mi < hi) {
                    return leftEdge(nums, target, lo, mi);
                } else {
                    return mi;
                }
            } else {
                assert nums[mi] < target;
                lo = mi + 1;
            }
        }

        return lo;
    }

    int rightEdge(int[] nums, int target, int lo, int hi) {

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (nums[mi] == target) {
                if (mi + 1 < hi) {
                    if (nums[mi + 1] == target)
                        return rightEdge(nums, target, mi + 1, hi);
                    else
                        return mi;
                } else {
                    return nums[mi + 1] == target ? mi + 1 : mi;
                }
            } else {
                assert nums[mi] > target;
                hi = mi - 1;
            }
        }

        return lo;
    }
}
