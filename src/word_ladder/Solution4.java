package word_ladder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Based on the previous solution, instead of iterating the word list to find connected graph nodes,
 * use intimidate, generic words to connect words. For each word, find its connected words through the
 * intimidate words.
 *
 * Each word has a fixed number of intimidate words. This number equals to the word's length. For example,
 *
 * words | keys | words
 * ===================
 * dog -- *og -- log
 *   |        \- cog
 *   |
 *   | -- d*g -- dig
 *   \ -- do* -- dot
 *
 * This technique was learnt from LeetCode's official solutions.
 *
 * The time complexity of build such word connections at the beginning is
 * O(M*M*N), where M is word length and N is word count.
 *
 * While in previous solutions, the total (there is no dedicated stage of building such a data structure)
 * time complexity is O(N*N*M): for each word of the N, check it against each other word; each check compares
 * each of the M chars.
 *
 * This new solution much faster because M is usually much less than N.
 *
 * LeetCode runtime reduced from 558ms to 68ms.
 *
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class Solution4 {
    HashMap<String, List<String>> wordConnections;

    private static HashMap<String, List<String>> buildWordConnections(List<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();

        int len = wordList.get(0).length();

        wordList.forEach(word -> {
            for (int i = 0; i < len; ++i) {
                String key = word.substring(0, i) + '*' + word.substring(i + 1, len);
                List<String> lst = map.getOrDefault(key, new ArrayList<>());
                lst.add(word);
                map.put(key, lst);
            }
        });
        return map;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;

        // Since all words are of the same length.
        final int L = beginWord.length();

        wordConnections = buildWordConnections(wordList);

        HashSet<String> visitedA = new HashSet<>();
        HashSet<String> visitedB = new HashSet<>();
        List<String> layerA = new LinkedList<>();
        List<String> layerB = new LinkedList<>();
        int seqLen = 1; // the begin word counts 1
        boolean found = false;
        layerA.add(beginWord);

        layerB.add(endWord);
        visitedB.add(endWord);
        // notice seqLen shouldn't +1 here

        while (!found && !layerA.isEmpty()) {
            // expand layer A
            ++seqLen;
            List<String> layerA2 = new LinkedList<>();
            for (String w : layerA) {
                for (int i = 0; i < L; ++i) {
                    String key = w.substring(0, i) + '*' + w.substring(i + 1, L);
                    for (String w2 : wordConnections.getOrDefault(key, new ArrayList<>())) {
                        if (!visitedA.contains(w2)) {
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
                    for (int i = 0; i < L; ++i) {
                        String key = w.substring(0, i) + '*' + w.substring(i + 1, L);
                        for (String w2 : wordConnections.getOrDefault(key, new ArrayList<>())) {
                            if (!visitedB.contains(w2)) {
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
                    }

                    if (found)
                        break;

                    layerB = layerB2;
                }
            }
        }

        return found ? seqLen : 0;
    }
}
