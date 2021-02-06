package find_first_and_last_position_of_element_in_sorted_array_34;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-21.
 */
public class FirstAndLastPositionOfElement {
    public int[] searchRange(int[] nums, int target) {
        return new Solution().searchRange(nums, target);
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
