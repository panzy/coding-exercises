package word_ladder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Zhiyong Pan on 2021-01-09.
 */
public class WordLadder {
    Solution2 solution = new Solution2();

    @Test
    void example1() {
        Assertions.assertEquals(5, solution.ladderLength(
                "hit", "cog",
                Arrays.asList("hot","dot","dog","lot","log","cog")));
    }

    @Test
    void example2() {
        Assertions.assertEquals(0, solution.ladderLength(
                "hit", "cog",
                Arrays.asList("hot","dot","dog","lot","log")));
    }

    @Test
    void example3() {
        Assertions.assertEquals(2, solution.ladderLength(
                "log", "dog",
                Arrays.asList("hot","dot","dog","lot","log")));
    }

    @Test
    void example4_largeN() {
        int wordSize = 100;
        int wordListSize = 5000;
        String[] wordList = new String[wordListSize];
        for (int i = 0; i < wordListSize; ++i) {
            char[] chars = new char[wordSize];
            for (int j = 0; j < wordSize; ++j) {
                chars[j] = (char) ('a' + Math.random() * 26);
            }
            wordList[i] = new String(chars);
        }

        char[] chars = new char[wordSize];
        for (int j = 0; j < wordSize; ++j) {
            chars[j] = '!';
        }
        String nonExistEndWord = new String(chars);

        Assertions.assertEquals(0,
                solution.ladderLength(
                        wordList[0], nonExistEndWord,
                        Arrays.asList(wordList)));
    }

}
