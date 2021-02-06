package count_good_meals_1711;

import _lib.IntArrays;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class CountGoodMeals {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        Assertions.assertEquals(4, solution.countPairs(new int[]{1, 3, 5, 7, 9}));
    }

    @Test
    void example2() {
        Assertions.assertEquals(15, solution.countPairs(new int[]{1, 1, 1, 3, 3, 3, 7}));
    }

    @Test
    void test5() {
        Assertions.assertEquals(53, solution.countPairs(new int[]{
                2160, 1936, 3, 29, 27, 5, 2503, 1593, 2, 0, 16, 0, 3860, 28908, 6, 2, 15, 49, 6246, 1946, 23, 105, 7996, 196, 0, 2, 55, 457, 5, 3, 924, 7268, 16, 48, 4, 0, 12, 116, 2628, 1468
        }));
    }

    @Test
    void test63() throws IOException, ParseException {
        Assertions.assertEquals(45967552, solution.countPairs(IntArrays.loadFromJsonFile(
                "./src/count_good_meals_1711/test-case-63.json")));
    }

    @Test
    void test69() throws IOException, ParseException {
        Assertions.assertEquals(999949972, solution.countPairs(IntArrays.loadFromJsonFile(
                "./src/count_good_meals_1711/test-case-69.json")));
    }
}
