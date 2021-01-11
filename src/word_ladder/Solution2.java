package word_ladder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rewrite the previous solution, replacing functional stream operations with for-loops.
 *
 * LeetCode runtime reduced from 2043ms to 776ms.
 *
 * Created by Zhiyong Pan on 2021-01-09.
 */
public class Solution2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> visited = new HashSet<>();
        List<String> layer = new LinkedList<>();
        int seqLen = 1;
        boolean found = false;
        layer.add(beginWord);

        while (!found && !layer.isEmpty()) {
            ++seqLen;
            List<String> layer2 = new LinkedList<>();

            for (String w : layer) {
                for (String w2 : wordList) {
                    if (!visited.contains(w2) && connected(w, w2)) {
                        visited.add(w2);
                        if (w2.equals(endWord)) {
                            found = true;
                            break;
                        }
                        layer2.add(w2);
                    }
                }

                if (found)
                    break;

                layer = layer2;
            }

            // removed visited words from word list
            wordList = wordList.stream().filter(w -> !visited.contains(w)).collect(Collectors.toList());
        }

        return found ? seqLen : 0;
    }

    /**
     * Judge whether two words are connected in the graph, that is, only one edit is required to transform
     * one to another.
     * @param word1
     * @param word2
     * @return
     */
    static boolean connected(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0, n = word1.length(); i < n; ++i) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++diffCnt > 1)
                    break;
            }
        }
        return diffCnt == 1;
    }
}
