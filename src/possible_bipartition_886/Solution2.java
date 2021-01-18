package possible_bipartition_886;

import java.util.*;

/**
 * For each person, find out the connected graph component he belongs to and try to "color" all the nodes
 * there.
 *
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Solution2 {
    static class Node {
        /**
         * Neighbours' indices.
         */
        List<Integer> neighbours = new LinkedList<>();

        /**
         * -1 = Uncolored.
         * 0 = The 1st color.
         * 1 = The 2nd color.
         */
        int color = -1;
        /**
         * For traverse.
         */
        boolean visited = false;
    }

    int N;

    // key = a person's index (1-based)
    Node[] graph;

    public boolean possibleBipartition(int N, int[][] dislikes) {
        this.N = N;
        graph = buildFromEdges(1 + N, dislikes);

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
        Node seedNode = graph[seed];

        if (seedNode == null) // This person is an isolate node, he can go to either group.
            return true;

        // If this person is colored, then the whole connected graph component he belongs to should have also been colored.
        if (seedNode.color != -1)
            return true;

        Queue<Node> queue = new LinkedList<>();

        // Since seed is the first person in his isolated graph component,
        // either group is ok for him. We choose group A.
        queue.add(seedNode);
        seedNode.visited = true;
        seedNode.color = 0;

        boolean failed = false;

        while (!failed && !queue.isEmpty()) {
            Node i = queue.poll();
            for (int j2 : i.neighbours) {
                Node j = graph[j2];
                if (j.color == -1) {
                    // Put j to the other side.
                    j.color = 1 - i.color;
                } else if (j.color == i.color) {
                    // Conflict detected.
                    failed = true;
                    break;
                }

                if (!j.visited) {
                    j.visited = true;
                    queue.add(j);
                }
            }
        }

        return !failed;
    }

    static Node[] buildFromEdges(int n, int[][] edges) {
        Node[] graph = new Node[n];

        // collect graph graph
        for (int[] pair : edges) {
            // Append j to i's list.
            {
                if (graph[pair[0]] == null)
                    graph[pair[0]] = new Node();
                graph[pair[0]].neighbours.add(pair[1]);
            }

            // Append i to j's list.
            {
                if (graph[pair[1]] == null)
                    graph[pair[1]] = new Node();
                graph[pair[1]].neighbours.add(pair[0]);
            }
        }

        return graph;
    }
}
