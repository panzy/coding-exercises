package word_ladder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bidirectional breadth first search.
 *
 * Based on the previous solution, introduce another BFS starting from endWord.
 *
 * LeetCode runtime reduced from 776ms to 558ms.
 *
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class Solution3 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> visitedA = new HashSet<>();
        HashSet<String> visitedB = new HashSet<>();
        List<String> layerA = new LinkedList<>();
        List<String> layerB = new LinkedList<>();
        int seqLen = 1; // the begin word counts 1
        boolean found = false;
        layerA.add(beginWord);

        if (wordList.contains(endWord)) {
            layerB.add(endWord);
            visitedB.add(endWord);
            // notice seqLen shouldn't +1 here
        } else {
            return 0;
        }

        while (!found && !layerA.isEmpty()) {
            // expand layer A
            ++seqLen;
            List<String> layerA2 = new LinkedList<>();
            for (String w : layerA) {
                for (String w2 : layerB) {
                    if (connected(w, w2)) {
                        found = true;
                        break;
                    }
                }
                for (String w2 : wordList) {
                    if (!visitedA.contains(w2) && connected(w, w2)) {
                        visitedA.add(w2);
                        if (visitedB.contains(w2)) {
                            found = true;
                            break;
                        }
                        layerA2.add(w2);
                    }
                }

                if (found)
                    break;

                layerA = layerA2;
            }

            // expand layer B
            if (!found) {
                ++seqLen;
                List<String> layerB2 = new LinkedList<>();
                for (String w : layerB) {
                    for (String w2 : wordList) {
                        if (!visitedB.contains(w2) && connected(w, w2)) {
                            visitedB.add(w2);
                            if (visitedA.contains(w2)) {
                                found = true;
                                break;
                            }
                            layerB2.add(w2);
                        }
                    }

                    if (found)
                        break;

                    layerB = layerB2;
                }
            }

            // removed visited words from word list
            if (!found) {
                wordList = wordList.stream()
                        .filter(w -> !visitedA.contains(w) && !visitedB.contains(w))
                        .collect(Collectors.toList());
            }
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
