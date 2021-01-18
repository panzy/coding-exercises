package possible_bipartition_886;

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

    // Group colors. Possible values are {0, 1}.
    HashMap<Integer, Integer> groups;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        this.N = N;

        edges = collectEdges(dislikes);

        groups = new HashMap<>(1 + N);

        for (int i = 1; i <= N; ++i) {
            if (!bipartition(i))
                return false;
        }

        return true;
    }

    private static HashMap<Integer, List<Integer>> collectEdges(int[][] dislikes) {
        HashMap<Integer, List<Integer>> edges = new HashMap<>();

        // collect graph edges
        for (int[] pair : dislikes) {
            // Append j to i's list.
            List<Integer> lst = edges.getOrDefault(pair[0], new ArrayList());
            lst.add(pair[1]);
            edges.put(pair[0], lst);

            // Append i to j's list.
            lst = edges.getOrDefault(pair[1], new ArrayList());
            lst.add(pair[0]);
            edges.put(pair[1], lst);
        }

        return edges;
    }

    /**
     * Bi-partition a whole isolated graph component.
     */
    boolean bipartition(int seed) {
        if (groups.containsKey(seed)) // already assigned
            return true;

        Queue<Integer> queue = new ArrayDeque<>();
        BitSet bfsVisited = new BitSet(1 + N);

        // Since seed is the first person in his isolated graph component,
        // either group is ok for him. We choose group A.
        queue.add(seed);
        bfsVisited.set(seed);
        groups.put(seed, 0);

        boolean failed = false;

        while (!failed && !queue.isEmpty()) {
            int i = queue.poll();
            int g = groups.get(i);
            List<Integer> neighbours = edges.get(i);
            if (neighbours == null)
                continue;
            for (int j : neighbours) {
                if (!groups.containsKey(j)) {
                    // Put j to the other side.
                    groups.put(j, 1 - g);
                } else if (groups.get(j) == g) {
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
