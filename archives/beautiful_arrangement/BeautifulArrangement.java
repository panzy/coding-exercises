package beautiful_arrangement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-03.
 */
public class BeautifulArrangement {
    Solution solution = new Solution();

    @Test
    void countArrangement_example1() {
        // [1,2]
        // [2,1]
        Assertions.assertEquals(2, solution.countArrangement(2));
    }

    @Test
    void countArrangement_example2() {
        Assertions.assertEquals(1, solution.countArrangement(1));
    }

    @Test
    void countArrangement_example3() {
        // [1,2,3]
        // [2,1,3]
        // [3,2,1]
        Assertions.assertEquals(3, solution.countArrangement(3));
    }

    @Test
    void countArrangement_example4() {
        Assertions.assertEquals(8, solution.countArrangement(4));
    }

    @Test
    void countArrangement_example15() {
        Assertions.assertEquals(24679, solution.countArrangement(15));
    }
}
