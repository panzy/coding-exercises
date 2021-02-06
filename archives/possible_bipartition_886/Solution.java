package possible_bipartition_886;

import java.util.*;

/**
 * A slow but accepted solution. Simulate.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution {
    int N;
    HashMap<Integer, List<Integer>> edges;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        this.N = N;

        // key = a person's index (1-based)
        // value = that person's dislikes
        edges = new HashMap<>();

        // Indicates whether a person has been assigned to group A.
        HashSet<Integer> groupA = new HashSet<>(1 + N);

        // Indicates whether a person has been assigned to group B.
        HashSet<Integer> groupB = new HashSet(1 + N);

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

        Queue<Integer> pendingPeople = new ArrayDeque<>();

        for (int i = 1; i <= N; ++i) {
            if (edges.get(i) != null)
                pendingPeople.add(i);
        }

        while (!pendingPeople.isEmpty()) {
            int i = pendingPeople.poll();

            List<Integer> lst = edges.get(i);

            // Is these groups available for i?
            boolean groupAvailableA = true, groupAvailableB = true;

            for (int j : lst) {
                if (groupA.contains(j)) {
                    groupAvailableA = false;
                } else if (groupB.contains(j)) {
                    groupAvailableB = false;
                }
            }

            // i might have been assigned to a group earlier, when we assigned one of its dislikes.
            // That assignment of i might be invalid.
            if (!groupAvailableA && groupA.contains(i) || !groupAvailableB && groupB.contains(i))
                return false;

            if (!groupAvailableA && !groupAvailableB) {
                return false;
            } else if (groupAvailableA && groupAvailableB) {
                // If both groups are empty, then it doesn't matter which group will i go to,
                // but otherwise, we don't know at this point where i should go.
                if ((groupA.isEmpty() || !connected(groupA.iterator().next(), i)) &&
                        (groupB.isEmpty() || !connected(groupB.iterator().next(), i))) {
                    groupA.add(i);
                    for (int j : lst) {
                        groupB.add(j);
                    }
                } else {
                    pendingPeople.add(i);
                }
            } else if (groupAvailableA) {
                groupA.add(i);
                for (int j : lst) {
                    groupB.add(j);
                }
            } else if (groupAvailableB) {
                groupB.add(i);
                for (int j : lst) {
                    groupA.add(j);
                }
            } else {
                assert false; // impossible
            }
        }

        return true;
    }

    /**
     * Find out whether two graph nodes are in the same isolated graph component.
     */
    boolean connected(int from, int to) {
        // BFS
        Queue<Integer> queue = new ArrayDeque<>();
        BitSet visited = new BitSet(1 + N);
        queue.add(from);
        visited.set(from);

        boolean found = false;

        while (!found && !queue.isEmpty()) {
            int i = queue.poll();
            List<Integer> neighbours = edges.get(i);
            if (neighbours.contains(to)) {
                found = true;
            } else {
                for (int j : neighbours) {
                    if (!visited.get(j)) {
                        visited.set(j);
                        queue.add(j);
                    }
                }
            }
        }

        return found;
    }
}
