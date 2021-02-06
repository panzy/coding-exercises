package smallest_range_i;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    void N1_K0() {
        Assertions.assertEquals(0, Solution.smallestRangeI(new int[]{1}, 0));
    }

    @Test
    void N2() {
        Assertions.assertEquals(6, Solution.smallestRangeI(new int[]{0, 10}, 2));
    }

    @Test
    void N3() {
        Assertions.assertEquals(0, Solution.smallestRangeI(new int[]{1, 3, 6}, 3));
    }
}
