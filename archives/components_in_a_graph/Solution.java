package components_in_a_graph;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'componentsInGraph' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY gb as parameter.
     */

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        // Write your code here

        // key = node id
        // value = neighbour node ids
        HashMap<Integer, List<Integer>> neibs = new HashMap<>();

        // Collect neighbours.
        for (List<Integer> edge : gb) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            neibs.computeIfAbsent(n1, k -> new ArrayList<>()).add(n2);
            neibs.computeIfAbsent(n2, k -> new ArrayList<>()).add(n1);
        }

        HashSet<Integer> visited = new HashSet<>();
        int minSize = Integer.MAX_VALUE;
        int maxSize = Integer.MIN_VALUE;
        for (Integer nodeId : neibs.keySet()) {
            if (!visited.contains(nodeId)) {
                int size = getComponentSize(nodeId, neibs, visited);
                if (size > 1) {
                    minSize = Math.min(minSize, size);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        res.add(minSize == Integer.MAX_VALUE ? 0 : minSize);
        res.add(maxSize == Integer.MIN_VALUE ? 0 : maxSize);
        return res;
    }

    private static int getComponentSize(
            int nodeId, HashMap<Integer, List<Integer>> neibs, HashSet<Integer> visited) {
        int ans = 0;

        LinkedList<Integer> layers = new LinkedList<>();
        layers.add(nodeId);
        visited.add(nodeId);

        // To avoid stackoverflow, don't use recursion.

        while (!layers.isEmpty()) {
            int thisId = layers.poll();
            ++ans;

            for (int neibId : neibs.getOrDefault(thisId, new ArrayList<>())) {
                if (!visited.contains(neibId)) {
                    layers.add(neibId);
                    visited.add(neibId);
                }
            }
        }
        return ans;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\pzy84\\workspace\\Leetcode\\src\\components_in_a_graph\\input33.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> gb = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                gb.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.componentsInGraph(gb);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

