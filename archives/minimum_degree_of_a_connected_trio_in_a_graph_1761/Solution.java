package minimum_degree_of_a_connected_trio_in_a_graph_1761;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.IntArrays;

import java.io.IOException;
import java.util.*;

/**
 * 1761. Minimum Degree of a Connected Trio in a Graph
 * https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
 * Created by Zhiyong Pan on 2021-02-13.
 */
public class Solution {

    public int minTrioDegree(int n, int[][] edges) {
        int[] degree = new int[n + 1]; // degree of each node
        boolean[][] conn = new boolean[n + 1][n + 1]; // conn[u][v] = whether two nodes are connected.
        boolean hasTrio = false;
        int ans = n * 3; // init to the maximal possible answer

        Arrays.fill(degree, 0);
        for (boolean[] row : conn) {
            Arrays.fill(row, false);
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            ++degree[u];
            ++degree[v];
            conn[u][v] = conn[v][u] = true;
        }

        // For each node set (i, u, v), where i < u < v,
        // check if it's a trio, and count its degree if is.
        for (int i = 1; i + 2 <= n; ++i) {
            if (degree[i] < 2) continue;

            for (int u = i + 1; u + 1 <= n; ++u) {
                if (!conn[i][u]) continue;
                for (int v = u + 1; v <= n; ++v) {
                    if (conn[i][v] && conn[u][v]) {
                        hasTrio = true;
                        ans = Math.min(ans, degree[i] + degree[u] + degree[v] - 6);
                    }
                }
            }
        }

        if (!hasTrio) return -1;
        return ans;
    }

    @Test
    void test() throws IOException, ParseException {
        Assertions.assertEquals(0, minTrioDegree(4, new int[][]{
                {1, 2}, {4, 1}, {4, 2}
        }));
        Assertions.assertEquals(1191, minTrioDegree(400, IntArrays.load2DFromJsonFile("src/minimum_degree_of_a_connected_trio_in_a_graph_1761/test64.json")));
        Assertions.assertEquals(6, minTrioDegree(14, new int[][]{
                {3,1},{14,1},{12,10},{4,6},{5,13},{9,5},{8,9},{9,14},{11,8},{2,5},{2,11},{1,12},{6,2},{7,13},{12,3},{12,9},{3,9},{5,6},{9,1},{10,8},{1,11},{6,1},{1,8},{7,9},{3,2},{12,8},{4,7},{14,5},{11,3},{2,9},{1,7},{13,6},{6,9}
        }));
        Assertions.assertEquals(20, minTrioDegree(15, new int[][]{
                {6,15},{12,10},{14,7},{4,6},{14,10},{3,10},{5,1},{4,15},{14,13},{8,3},{8,6},{10,9},{2,5},
                {1,3},{15,2},{2,14},{15,5},{7,4},{6,2},{10,15},{15,8},{15,14},{1,15},{6,14},{4,5},{3,9},{5,6},{3,6},
                {4,14},{5,9},{8,2},{3,12},{3,15},{8,5},{11,4},{9,4},{5,12},{11,7},{2,4},{1,2},{9,13},{10,11},{2,7},
                {10,8},{1,11},{2,10},{15,7},{1,14},{2,13},{7,9},{6,13},{7,6},{6,10},{8,11},{3,2},{14,5},{3,14},{5,11},
                {4,13},{8,1},{10,4},{11,9},{10,7},{10,13},{1,4},{8,13},{11,6},{1,7},{1,13},{2,9},{2,12},{13,12},{15,9},{14,12}
        }));
        Assertions.assertEquals(3, minTrioDegree(6, new int[][]{
                {6,5},{4,3},{5,1},{1,4},{2,3},{4,5},{2,6},{1,3}
        }));
        Assertions.assertEquals(3, minTrioDegree(6, new int[][]{
                {1, 2}, {1, 3}, {3, 2}, {4, 1}, {5, 2}, {3, 6}
        }));

        Assertions.assertEquals(0, minTrioDegree(7, new int[][]{
                {1,3},{4,1},{4,3},{2,5},{5,6},{6,7},{7,5},{2,6}
        }));
    }
}