package path_with_minimum_effort_1631;

import java.util.PriorityQueue;

/**
 * Dijkstra's shortest path algorithm.
 *
 * Created by Zhiyong Pan on 2021-01-26.
 */
public class Solution3 {
    /** Graph node. */
    static class Node {
        int r, c, effort;
        public Node(int r, int c, int e) {
            this.r = r;
            this.c = c;
            this.effort = e;
        }
    }

    static final int[][] neighbours = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int ans = Integer.MAX_VALUE;
        boolean[][] closed = new boolean[m][n];
        PriorityQueue<Node> openList = new PriorityQueue<>((a, b) -> a.effort - b.effort);

        openList.add(new Node(0, 0, 0));

        while (!openList.isEmpty()) {
            // Pick the node with the smallest effort from the open list and close it.
            Node p = openList.poll();
            closed[p.r][p.c] = true;

            if (p.r == m - 1 && p.c == n - 1) {
                ans = p.effort;
                break;
            }

            // Update its neighbours' effort.
            for (int[] d : neighbours) {
                int r = p.r + d[0];
                int c = p.c + d[1];

                if (0 <= r && r < m && 0 <= c && c < n && !closed[r][c]) {
                    // Note that different from the distance of classic Dijkstra's algorithm,
                    // the effort here is not accumulated along the path, but the max height delta
                    // between two adjacent nodes.
                    int effort2 = Math.max(p.effort, Math.abs(heights[p.r][p.c] - heights[r][c]));

                    // There may be old Node objects for this cell coordinates in the mini heap,
                    // removing them will defeat the purpose of using a mini heap, and leaving
                    // them has no impact on the algorithm's correctness. So we simply leave them
                    // and instantiate new objects.
                    openList.add(new Node(r, c, effort2));
                }
            }
        }

        return ans;
    }
}
