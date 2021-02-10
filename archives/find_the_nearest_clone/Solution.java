package find_the_nearest_clone;

import algorithm.GraphBFS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Find the nearest clone
 * https://www.hackerrank.com/challenges/find-the-nearest-clone/problem
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        List<Integer>[] graph = buildFromEdges(graphNodes, graphFrom, graphTo);
        LinkedList<GraphBFS> bfs = new LinkedList<>();
        int[][] visited = new int[graphNodes + 1][];
        AtomicInteger ans = new AtomicInteger(-1);

        // Init a BFS for each node of the wanted color.
        for (int i = 1; i <= graphNodes; ++i) {
            if (ids[i - 1] == val) {
                // This node is the right color
                bfs.add(new GraphBFS(id -> graph[id], (int id, int prevNode, int start, int layer) -> {
                    if (visited[id] == null) {
                        // First visit
                        visited[id] = new int[]{start, layer};
                        return true;
                    } else if (visited[id][0] == start) {
                        // Already visited by the same traversal (identified by its start node id).
                        return false;
                    } else {
                        // Meet other traversal!
                        ans.set(visited[id][1] + layer);
                        return false;
                    }
                }, i));
                visited[i] = new int[]{i, 0};
            }
        }

        // Check each traversal's next layer.
        boolean allEnded = false;
        while (ans.get() == -1 && !allEnded) {
            allEnded = true;
            for (GraphBFS b : bfs) {
                b.nextLayer();
                if (ans.get() != -1)
                    break;
                if (!b.ended())
                    allEnded = false;
            }
        }

        return ans.get();
    }

    static List<Integer>[] buildFromEdges(int nodeCount, int[] graphFrom, int[] graphTo) {
        List<Integer>[] graph = new List[nodeCount + 1];

        // collect graph graph
        for (int i = 0; i < graphFrom.length; ++i) {
            int start = graphFrom[i];
            int end = graphTo[i];
            // Append j to i's list.
            if (graph[start] == null)
                graph[start] = new LinkedList<>();
            graph[start].add(end);

            // Append i to j's list.
            if (graph[end] == null)
                graph[end] = new LinkedList<>();
            graph[end].add(start);
        }

        return graph;
    }


    @Test
    void test() {
        Assertions.assertEquals(3, findShortest(5, new int[]{1, 1, 2, 3}, new int[]{2, 3, 4, 5},
                new long[]{1, 2, 3, 3, 2}, 2));

        Assertions.assertEquals(1, findShortest(6, new int[]{1, 1, 2, 3, 5}, new int[]{2, 3, 4, 5, 6},
                new long[]{1, 2, 3, 3, 2, 2}, 2));

        Assertions.assertEquals(-1, findShortest(5, new int[]{1, 2, 3}, new int[]{2, 4, 5},
                new long[]{1, 2, 3, 3, 2}, 2));
    }

    @Test
    void testcase10() throws IOException {
        Scanner scanner = new Scanner(new FileInputStream("src/find_the_nearest_clone/input10.txt"));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);
        Assertions.assertEquals(-1, ans);

        scanner.close();
    }
}
