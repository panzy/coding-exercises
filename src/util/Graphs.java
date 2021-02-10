package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zhiyong Pan on 2021-01-18.
 */
public class Graphs {
    public static HashMap<Integer, List<Integer>> buildFromEdges(int[][] edges) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        // collect graph graph
        for (int[] pair : edges) {
            // Append j to i's list.
            List<Integer> lst = graph.getOrDefault(pair[0], new ArrayList());
            lst.add(pair[1]);
            graph.put(pair[0], lst);

            // Append i to j's list.
            lst = graph.getOrDefault(pair[1], new ArrayList());
            lst.add(pair[0]);
            graph.put(pair[1], lst);
        }

        return graph;
    }

    public static HashMap<Integer, List<Integer>> buildFromEdges(int[] graphFrom, int[] graphTo) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        // collect graph graph
        for (int i = 0; i < graphFrom.length; ++i) {
            int start = graphFrom[i];
            int end = graphTo[i];
            // Append j to i's list.
            List<Integer> lst = graph.getOrDefault(start, new ArrayList());
            lst.add(end);
            graph.put(start, lst);

            // Append i to j's list.
            lst = graph.getOrDefault(end, new ArrayList());
            lst.add(start);
            graph.put(end, lst);
        }

        return graph;
    }

}
