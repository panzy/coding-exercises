package minimum_genetic_mutation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class MinimumGeneticMutation {
    Solution solution = new Solution();

    @Test
    void example1() {
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        int expected = 1;
        Assertions.assertEquals(expected, solution.minMutation(start, end, bank));
    }

    @Test
    void example2() {
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        int expected = 2;
        Assertions.assertEquals(expected, solution.minMutation(start, end, bank));
    }

    @Test
    void example3() {
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        int expected = 3;
        Assertions.assertEquals(expected, solution.minMutation(start, end, bank));
    }

    @Test
    void example4() {
        String start = "AAAAACCC";
        String end = "AACCCCCT";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        int expected = -1;
        Assertions.assertEquals(expected, solution.minMutation(start, end, bank));
    }
}
