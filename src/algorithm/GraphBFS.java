package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A framework (or boilerplate) for graph BFS. The API is designed such that it does not assume how the graph is presented
 * or how each node is be processed. The only requirement is that graph nodes are identified by integer numbers.
 *
 * Notice that it does not keep track of the visiting state. Instead, it is up to the caller.
 *
 * To see an application of this API, check out the FindTheNearestClone class below, which performs multiple BFS
 * simultaneously from different nodes.
 *
 * Multiple source BFS is quicker to find the shortest path between two or more nodes than single source BFS.
 *
 * There is also an application doing BFS on a grid below, the class is named ConnectedCellInAGrid.
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class GraphBFS {
    public interface DataProvider {
        List<Integer> getNeighbours(int id);
    }

    public interface TraversalListener {
        /**
         * @param currNode the new found node's id.
         * @param prevNode from which node does the current node directly reached?
         * @param originNode the origin node from which this traversal was started.
         * @param currLayerIdx the origin node is at layer 0.
         * @return
         */
        boolean onNode(int currNode, int prevNode, int originNode, int currLayerIdx);
    }

    private int originNode;
    private DataProvider dataProvider;
    private TraversalListener traversalListener;
    private LinkedList<Integer> currLayer;
    private int currLayerIdx;

    public GraphBFS(DataProvider dataProvider, TraversalListener traversalListener, int originNode) {
        this.dataProvider = dataProvider;
        this.traversalListener = traversalListener;
        this.originNode = originNode;
        currLayer = new LinkedList<>();
        currLayer.add(originNode);
        currLayerIdx = 0;
    }

    public List<Integer> nextLayer() {
        LinkedList<Integer> nextLayer = new LinkedList<>();
        ++currLayerIdx;

        for (int a : currLayer) {
            List<Integer> neighbours = dataProvider.getNeighbours(a);
            if (neighbours != null) {
                for (int b : neighbours) {
                    if (originNode != b && traversalListener.onNode(b, a, this.originNode, currLayerIdx))
                        nextLayer.add(b);
                }
            }
        }

        currLayer = nextLayer;
        return currLayer;
    }

    public boolean ended() {
        return currLayer.isEmpty();
    }
}

/**
 * Find the nearest clone
 * https://www.hackerrank.com/challenges/find-the-nearest-clone/problem
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
class FindTheNearestClone {
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
}

/**
 * Search the largest connected area in a grid.
 *
 * DFS: Connected Cell in a Grid
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem
 */
class ConnectedCellInAGrid {

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            visited[i] = new boolean[n];
        }

        HashMap<Integer, Integer> areas = new HashMap<>();

        for (int sr = 0; sr < m; ++sr) {
            for (int sc = 0; sc < n; ++sc) {
                if (visited[sr][sc] || grid[sr][sc] == 0)
                    continue;

                int start = (sr << 8) + sc;

                areas.put(start, 1);

                GraphBFS bfs = new GraphBFS(id -> {
                    int r = id >> 8;
                    int c = id & 0xff;
                    List<Integer> neibs = new ArrayList<>(8);

                    for (int[] d : delta) {
                        int r2 = r + d[0];
                        int c2 = c + d[1];
                        if (0 <= r2 && r2 < m && 0 <= c2 && c2 < n && grid[r2][c2] == 1) {
                            neibs.add((r2 << 8) + c2);
                        }
                    }
                    return neibs;
                }, (id, prevNode, originNode, currLayerIdx) -> {
                    int r = id >> 8;
                    int c = id & 0xff;
                    if (visited[r][c]) {
                        return false;
                    } else {
                        visited[r][c] = true;
                        areas.put(start, areas.get(start) + 1);
                        return true;
                    }
                }, start);

                while (!bfs.ended())
                    bfs.nextLayer();
            }
        }

        return areas.values().stream().max(Integer::compare).get();
    }

    @Test
    void test() {
        Assertions.assertEquals(5, maxRegion(new int[][]{
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 0, 0, 0}
        }));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
