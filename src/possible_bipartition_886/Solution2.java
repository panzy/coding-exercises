package possible_bipartition_886;

import _lib.graph.Graphs;

import java.util.*;

/**
 * For each person, find out the connected graph component he belongs to and try to "color" all the nodes
 * there.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution2 {
    int N;

    // key = a person's index (1-based)
    // value = that person's dislikes
    HashMap<Integer, List<Integer>> edges;

    // key = a person's index (1-based)
    // value = that person's group color (0 or 1).
    HashMap<Integer, Integer> colors;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        this.N = N;
        edges = Graphs.buildFromEdges(dislikes);
        colors = new HashMap<>(1 + N);

        for (int i = 1; i <= N; ++i) {
            if (!bipartition(i))
                return false;
        }

        return true;
    }

    /**
     * Bi-partition a whole isolated graph component.
     */
    boolean bipartition(int seed) {
        if (colors.containsKey(seed)) // already assigned
            return true;

        Queue<Integer> queue = new ArrayDeque<>();
        BitSet bfsVisited = new BitSet(1 + N);

        // Since seed is the first person in his isolated graph component,
        // either group is ok for him. We choose group A.
        queue.add(seed);
        bfsVisited.set(seed);
        colors.put(seed, 0);

        boolean failed = false;

        while (!failed && !queue.isEmpty()) {
            int i = queue.poll();
            int g = colors.get(i);
            List<Integer> neighbours = edges.get(i);
            if (neighbours == null)
                continue;
            for (int j : neighbours) {
                if (!colors.containsKey(j)) {
                    // Put j to the other side.
                    colors.put(j, 1 - g);
                } else if (colors.get(j) == g) {
                    // Conflict detected.
                    failed = true;
                    break;
                }

                if (!bfsVisited.get(j)) {
                    bfsVisited.set(j);
                    queue.add(j);
                }
            }
        }

        return !failed;
    }
}
