package get_maximum_in_generated_array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetMaximumInGeneratedArray {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(3, solution.getMaximumGenerated(7));
    }

    @Test
    void example2() {
        Assertions.assertEquals(1, solution.getMaximumGenerated(2));
    }

    @Test
    void example3() {
        Assertions.assertEquals(2, solution.getMaximumGenerated(3));
    }

    @Test
    void example4() {
        Assertions.assertEquals(5, solution.getMaximumGenerated(15));
    }

    @Test
    void example5() {
        Assertions.assertEquals(0, solution.getMaximumGenerated(0));
    }

    @Test
    void example6() {
        Assertions.assertEquals(1, solution.getMaximumGenerated(1));
    }

    @Test
    void largeN_even() {
        Assertions.assertEquals(317811, solution.getMaximumGenerated(1_00_000_000));
    }

    @Test
    void largeN_odd() {
        Assertions.assertEquals(317811, solution.getMaximumGenerated(1_00_000_001));
    }
}
