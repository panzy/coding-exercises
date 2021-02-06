package word_ladder_ii;

import java.util.*;

/**
 * Based on {@link word_ladder.Solution4}.
 *
 * Instead of returning immediately after seeing the two front-lines meet, this time, we do a cross-product
 * on the two front-lines.
 *
 * In order to restore the path, I wrap the words with a simple data structure, Node, which has a prev pointer.
 *
 * Created by Zhiyong Pan on 2021-01-11.
 */
public class Solution {
    int L; // word length. all words are of the same length.
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

    /**
     * Expand a whole layer of words (from the view of BFS) to the next layer.
     *
     * @param layer This layer. will be cleared and filled with next layer's words.
     * @param visited Record words that have been visited by this direction's BFS.
     * @param otherVisited Record words that have been visited by the other direction's BFS.
     * @return whether the two BFS meet after this expansion.
     */
    private List<Node> expandLayer(List<Node> layer, HashSet<String> visited, HashSet<String> otherVisited) {
        List<Node> meetNodes = null;
        List<Node> nextLayer = new LinkedList<>();
        HashSet<String> newVisited = new HashSet<>();

        for (Node p : layer) {
            String w = p.word;
            for (int i = 0; i < L; ++i) {
                String key = w.substring(0, i) + '*' + w.substring(i + 1, L);
                for (String w2 : wordConnections.getOrDefault(key, new ArrayList<>())) {
                    if (!visited.contains(w2)) {
                        // Update the global visited map after this whole layer has been processed,
                        // so that the following words in this layer still have the chance
                        // to connect with the same word from the other side.
                        newVisited.add(w2);
                        if (otherVisited.contains(w2)) {
                            if (meetNodes == null)
                                meetNodes = new ArrayList<>();
                            meetNodes.add(new Node(w2, p));
                        } else {
                            nextLayer.add(new Node(w2, p));
                        }
                    }
                }
            }
        }

        for (String w : newVisited) {
            visited.add(w);
        }

        layer.clear();
        if (meetNodes == null) // not found
            layer.addAll(nextLayer);
        else
            layer.addAll(meetNodes);

        return meetNodes;
    }

    private List<List<String>> concatenatePath(List<Node> layerA, List<Node> layerB) {
        List<List<String>> ans = new LinkedList<>();

        for (Node a : layerA) {
            for (Node b : layerB) {
                if (a.word.equals(b.word)) {
                    LinkedList<String> path = new LinkedList<>();
                    for (Node i = a; i != null; i = i.prev) {
                        path.addFirst(i.word);
                    }
                    for (Node i = b.prev; i != null; i = i.prev) {
                        path.addLast(i.word);
                    }
                    ans.add(path);
                }
            }
        }

        return ans;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord))
            return new ArrayList<>();

        L = beginWord.length();

        wordConnections = buildWordConnections(wordList);

        HashSet<String> visitedA = new HashSet<>();
        HashSet<String> visitedB = new HashSet<>();
        List<Node> layerA = new LinkedList<>();
        List<Node> layerB = new LinkedList<>();
        int seqLen = 1; // the begin word counts 1
        List<Node> meetNodes = null;
        List<List<String>> ans = new ArrayList<>();
        layerA.add(new Node(beginWord, null));

        layerB.add(new Node(endWord, null));
        visitedB.add(endWord);
        // notice seqLen shouldn't +1 here

        while (meetNodes == null && !layerA.isEmpty()) {
            // expand layer A
            ++seqLen;
            meetNodes = expandLayer(layerA, visitedA, visitedB);
            if (meetNodes != null) {
                ans = concatenatePath(meetNodes, layerB);
            }

            // expand layer B
            if (meetNodes == null) {
                ++seqLen;
                meetNodes = expandLayer(layerB, visitedB, visitedA);

                if (meetNodes != null) {
                    ans = concatenatePath(layerA, meetNodes);
                }
            }
        }

        return ans;
    }

    static class Node {
        String word;
        Node prev;

        Node(String w, Node p) {
            word = w;
            prev = p;
        }
    }
}
