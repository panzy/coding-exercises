package roads_and_libraries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Graphs;

import java.util.*;

/**
 * Roads and Libraries
 * https://www.hackerrank.com/challenges/torque-and-development/problem
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib <= c_road)
            return (long)n * c_lib;

        HashMap<Integer, List<Integer>> graph = Graphs.buildFromEdges(cities);
        HashSet<Integer> visited = new HashSet<>();
        long cost = 0;

        for (int i = 1; i <= n; ++i) {
            if (visited.contains(i))
                continue;
            visited.add(i);

            // Build a library here.
            cost += c_lib;

            Stack<Integer> neibs = new Stack<>();
            neibs.add(i);

            while (!neibs.isEmpty()) {
                int a = neibs.pop();
                for (int b : graph.getOrDefault(a, new LinkedList<>())) {
                    if (!visited.contains(b)) {
                        visited.add(b);
                        neibs.add(b);
                        cost += c_road;
                    }
                }
            }
        }

        return cost;
    }

    @Test
    void test() {
        Assertions.assertEquals(4, roadsAndLibraries(3, 2, 1, new int[][]{{1, 2}, {3, 1}, {2, 3}}));
        Assertions.assertEquals(6, roadsAndLibraries(3, 2, 1, new int[][]{}));
        Assertions.assertEquals(5, roadsAndLibraries(3, 2, 1, new int[][]{{1, 2}}));
        Assertions.assertEquals(12, roadsAndLibraries(6, 2, 5, new int[][]{{1, 3}, {3, 4}, {2, 4}, {1, 2}, {2, 3}, {5, 6}}));
    }
}
