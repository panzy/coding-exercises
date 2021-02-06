package word_ladder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Breadth first search in graph.
 *
 * I didn't realize this problem was labelled as hard and approached it in a straightforward, non-optimized manner.
 * This solution was hardly accepted, with a horrible runtime of 2043ms.
 *
 * Created by Zhiyong Pan on 2021-01-09.
 */
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> visited = new HashSet<>();
        List<String> layer = new LinkedList<>();
        int seqLen = 1;
        AtomicBoolean found = new AtomicBoolean(false);
        layer.add(beginWord);

        while (!found.get() && !layer.isEmpty()) {
            ++seqLen;
            layer = layer.stream().flatMap(w ->
                wordList.stream().map(w2 -> {
                    if (!visited.contains(w2) && connected(w, w2)) {
                        visited.add(w2);
                        if (w2.equals(endWord)) {
                            found.set(true);
                        }
                        return w2;
                    } else {
                        return null;
                    }
                }).filter(w3 -> w3 != null)
            ).collect(Collectors.toList());

            // XXX Can not filter wordList because:
            // java: local variables referenced from a lambda expression must be final or effectively final
//            wordList = wordList.stream().filter(w -> !visited.contains(w)).collect(Collectors.toList());
        }

        return found.get() ? seqLen : 0;
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
