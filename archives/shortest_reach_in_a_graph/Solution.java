package bfs_shortest_reach_in_a_graph;

import algorithm.GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * BFS: Shortest Reach in a Graph
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem
 *
 * Created by Zhiyong Pan on 2021-02-09.
 */
public class Solution {
    public static class Graph {

        int size;
        List<Integer>[] graph;
        final static List<Integer> EMPTY_NEIBS = new LinkedList<>();

        public Graph(int size) {
            this.size = size;
            graph = new List[size + 1];
        }

        public void addEdge(int first, int second) {
            if (graph[first] == null)
                graph[first] = new LinkedList<>();
            graph[first].add(second);

            if (graph[second] == null)
                graph[second] = new LinkedList<>();
            graph[second].add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int[] visited = new int[size - 1];
            Arrays.fill(visited, -1);

            GraphBFS bfs = new GraphBFS(id -> graph[id], (int id, int prevNode, int _start, int layer) -> {
                int idx = id < startId ? id : id - 1;
                if (visited[idx] == -1) {
                    // First visit
                    visited[idx] = layer * 6;
                    return true;
                } else {
                    return false;
                }
            }, startId);

            while (!bfs.ended())
                bfs.nextLayer();

            return visited;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/_tmp4/input05.txt"));

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                System.out.print(distances[i]);
                System.out.print(" ");
            }
            System.out.println();
        }

        scanner.close();
    }
}

