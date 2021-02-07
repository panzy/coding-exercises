package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Java Arrays.binarySearch() doesn't guarantee the result position is the first or the last,
 * while the Python bisect module has more control on these.
 *
 * This code implemented binary search that can find the first and the last position of an
 * element in a sorted array.
 *
 * See also Python bisect.bisect_left() and bisect.bisect_right()
 * https://docs.python.org/3/library/bisect.html
 *
 * See also LeetCode problem:
 * 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class BinarySearchFirstOrLast {
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

    @Test
    void example1() {
        Assertions.assertArrayEquals(new int[]{3, 4}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        Assertions.assertArrayEquals(new int[]{1, 2}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7));
        Assertions.assertArrayEquals(new int[]{-1, -1}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        Assertions.assertArrayEquals(new int[]{0, 0}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 5));
        Assertions.assertArrayEquals(new int[]{5, 5}, searchRange(new int[]{5, 7, 7, 8, 8, 10}, 10));
    }

    @Test
    void example2() {
        Assertions.assertArrayEquals(new int[]{-1, -1}, searchRange(new int[]{}, 10));
    }

    @Test
    void example3() {
        Assertions.assertArrayEquals(new int[]{1, 4}, searchRange(new int[]{5, 7, 7, 7, 7, 10}, 7));
        Assertions.assertArrayEquals(new int[]{1, 5}, searchRange(new int[]{5, 7, 7, 7, 7, 7, 10}, 7));
    }

    @Test
    void example4() {
        Assertions.assertArrayEquals(new int[]{2, 5}, searchRange(new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9}, 3));
    }
}
