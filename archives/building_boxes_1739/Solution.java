package building_boxes_1739;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public int minimumBoxes(int n) {
        if (n < 4)
            return Math.min(3, n);

        int sum = 4;
        int area = 3;
        int height = 2;

        // Increase the pyramid until close to n.
        while (sum + (area + height + 1) < n) {
            ++height;
            area += height;
            sum += area;
        }

        // Add some extra boxes until n is reached.
        int adjust = 1;
        while (sum < n) {
            ++area;
            sum += adjust;
            ++adjust;
        }

        return area;
    }

    @Test
    void box() {
        Assertions.assertEquals(1, minimumBoxes(1));
        Assertions.assertEquals(2, minimumBoxes(2));
        Assertions.assertEquals(3, minimumBoxes(3));
        Assertions.assertEquals(3, minimumBoxes(4));
        Assertions.assertEquals(4, minimumBoxes(5));
        Assertions.assertEquals(5, minimumBoxes(6));
        Assertions.assertEquals(6, minimumBoxes(10));
        Assertions.assertEquals(9, minimumBoxes(15));
    }
}
