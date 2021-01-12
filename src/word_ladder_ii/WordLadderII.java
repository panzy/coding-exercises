package word_ladder_ii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class WordLadderII {
    Solution solution = new Solution();

    @Test
    void example1() {
        List<List<String>> ladders = solution.findLadders(
                "hit", "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        Assertions.assertEquals(2, ladders.size());
        Assertions.assertEquals(5, ladders.get(0).size());
        String expectedPath0 = "hit-hot-dot-dog-cog";
        String expectedPath1 = "hit-hot-lot-log-cog";
        String actualPath0 = ladders.get(0).stream().collect(Collectors.joining("-"));
        String actualPath1 = ladders.get(1).stream().collect(Collectors.joining("-"));
        Assertions.assertTrue(expectedPath0.equals(actualPath0) || expectedPath0.equals(actualPath1));
        Assertions.assertTrue(expectedPath1.equals(actualPath0) || expectedPath1.equals(actualPath1));
    }

    @Test
    void example2() {
        List<List<String>> ladders = solution.findLadders(
                "hit", "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assertions.assertEquals(0, ladders.size());
    }

    @Test
    void example3() {
        List<List<String>> ladders = solution.findLadders(
                "log", "dog",
                Arrays.asList("hot", "dot", "dog", "lot", "log"));
        Assertions.assertEquals(1, ladders.size());
        Assertions.assertEquals(2, ladders.get(0).size());
        String expectedPath0 = "log-dog";
        String actualPath0 = ladders.get(0).stream().collect(Collectors.joining("-"));
        Assertions.assertTrue(expectedPath0.equals(actualPath0));
    }

    @Test
    void example4() {
        List<List<String>> ladders = solution.findLadders(
                "red", "tax",
                Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));
        // expected:
        // [["red","ted","tad","tax"],["red","ted","tex","tax"],["red","rex","tex","tax"]]
        Assertions.assertEquals(3, ladders.size());
        Assertions.assertEquals(4, ladders.get(0).size());
    }
}
