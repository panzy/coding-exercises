package determine_if_two_strings_are_close_1657;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-22.
 */
public class CloseStrings {
    Solution solution = new Solution();

    @Test
    void examle1() {
        Assertions.assertFalse(solution.closeStrings("uau", "ssa"));
        Assertions.assertTrue(solution.closeStrings("uau", "uua"));
        Assertions.assertFalse(solution.closeStrings("suaus", "ssaus"));
        Assertions.assertTrue(solution.closeStrings("suaus", "ssauu"));
        Assertions.assertTrue(solution.closeStrings("", ""));
        Assertions.assertTrue(solution.closeStrings("abc", "bca"));
        Assertions.assertFalse(solution.closeStrings("a", "aa"));
        Assertions.assertTrue(solution.closeStrings("cabbba", "abbccc"));
        Assertions.assertFalse(solution.closeStrings("cabbba", "aabbss"));
        Assertions.assertFalse(solution.closeStrings("uau", "ssx"));
    }
}
