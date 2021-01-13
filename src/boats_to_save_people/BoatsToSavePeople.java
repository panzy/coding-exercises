package boats_to_save_people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class BoatsToSavePeople {
    Solution solution = new Solution();

    @Test
    void example1() {
        Assertions.assertEquals(1, solution.numRescueBoats(new int[]{1, 2}, 3));
    }

    @Test
    void example2() {
        Assertions.assertEquals(3, solution.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
    }

    @Test
    void example3() {
        Assertions.assertEquals(4, solution.numRescueBoats(new int[]{3, 5, 3, 4}, 5));
    }

    @Test
    void example4() {
        Assertions.assertEquals(1, solution.numRescueBoats(new int[]{5}, 5));
    }

    @Test
    void example5() {
        Assertions.assertEquals(1, solution.numRescueBoats(new int[]{1}, 5));
    }

    @Test
    void example6() {
        Assertions.assertEquals(3, solution.numRescueBoats(new int[]{1, 1, 1, 1, 1}, 5));
    }

    @Test
    void example7() {
        Assertions.assertEquals(2, solution.numRescueBoats(new int[]{5, 1, 4, 2}, 6));
    }
}
