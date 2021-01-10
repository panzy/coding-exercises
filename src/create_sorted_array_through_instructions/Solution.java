package create_sorted_array_through_instructions;

import java.util.Arrays;

/**
 * This solution should be correct. But it's too slow to accept.
 *
 * The reason of slowness should be that it simulates the creating of the array.
 *
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class Solution {
    public int createSortedArray(int[] instructions) {
        int[] nums = new int[instructions.length * 2];
        int begin = instructions.length, end = begin;
        long cost = 0;

        for (int num : instructions) {
            // binary search
            int idx = Arrays.binarySearch(nums, begin, end, num);

            // the target position and cost of each of the two approach:
            // 1. shift less numbers left, or
            // 2. shift greater numbers right.
            int pos1, pos2;
            int cost1, cost2;

            if (idx < 0) {
                idx = -idx - 1;

                // if shift left, the range to shift will be [0, idx) and the new number
                // will be [idx-1]
                pos1 = idx - 1;

                // if shift right, the range to shift will be [idx, end) and the new number
                // will be [idx]
                pos2 = idx;
            } else {
                // if shift left, insert the new number BEFORE its duplicates.
                // Notice that the index returned by binary search might not be the first of the duplicates
                pos1 = idx - 1;
                while (pos1 >= begin && nums[pos1] == num)
                    --pos1;

                // if shift right, insert the new number AFTER its duplicates
                pos2 = idx + 1;
                while (pos2 < end && nums[pos2] == num)
                    ++pos2;
            }

            cost1 = Math.max(0, pos1 - begin + 1); // notice that pos1 will be (begin-1) if num is the least
            cost2 = end - pos2;

            if (cost1 < cost2) {
                // shift [begin - 1, pos1) left
                for (int i = begin - 1; i < pos1; ++i)
                    nums[i] = nums[i + 1];
                nums[pos1] = num;
                --begin;
                cost += cost1;
            } else {
                // shift [pos2, end) right
                for (int i = end; i > pos2; --i) {
                    nums[i] = nums[i - 1];
                }
                nums[pos2] = num;
                ++end;
                cost += cost2;
            }
        }

        // "Since the answer may be large, return it modulo 109 + 7"
        return (int) (cost % (1_000_000_000 + 7));
    }
}