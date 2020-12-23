/* Zhiyong Pan, 2020-12-23 */
package next_greater_element_iii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    void nextGreaterElement_min() {
        Assertions.assertEquals(-1, solution.nextGreaterElement(1));
    }

    @Test
    void nextGreaterElement_max() {
        Assertions.assertEquals(2_147_483_647, Math.pow(2, 31) - 1);
        Assertions.assertEquals(-1, solution.nextGreaterElement(2_147_483_647));
    }

    @Test
    void nextGreaterElement_singleDigit() {
        Assertions.assertEquals(-1, solution.nextGreaterElement(8));
    }

    @Test
    void nextGreaterElement_twoDifferentDigits() {
        Assertions.assertEquals(21, solution.nextGreaterElement(12));
    }

    @Test
    void nextGreaterElement_twoDifferentDigits_false() {
        Assertions.assertEquals(-1, solution.nextGreaterElement(32));
    }

    @Test
    void nextGreaterElement_twoSameDigits() {
        Assertions.assertEquals(-1, solution.nextGreaterElement(33));
    }

    @Test
    void nextGreaterElement_3digits_swapRightMostTwo() {
        Assertions.assertEquals(132, solution.nextGreaterElement(123));
    }

    @Test
    void nextGreaterElement_3digits_swapLeftMostTwo() {
        Assertions.assertEquals(201, solution.nextGreaterElement(120));
    }

    @Test
    void nextGreaterElement_3digits_swapBetweenNonAdjacent() {
        Assertions.assertEquals(314, solution.nextGreaterElement(143));
    }

    @Test
    void nextGreaterElement_rightSideDigitsNeedReorder() {
        Assertions.assertEquals(12536789, solution.nextGreaterElement(12398765));
    }

    @Test
    void nextGreaterElement_230241() {
        Assertions.assertEquals(230412, solution.nextGreaterElement(230241));
    }

    @Test
    void nextGreaterElement_1999999999() {
        Assertions.assertEquals(-1, solution.nextGreaterElement(1999999999));
    }
}
