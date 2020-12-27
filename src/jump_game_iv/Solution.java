package jump_game_iv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Breadth First Search.
 * --
 * Zhiyong Pan, 2020-12-27
 */
class Solution {

    public int minJumps(int[] arr) {
        return breadthFirstSearch(arr, buildGraph(arr));
    }

    /**
     * record the shortcuts for each node.
     * @param arr
     * @return
     */
    HashMap<Integer, LinkedList<Integer>> buildGraph(int[] arr) {
        // for querying node indices by its value
        HashMap<Integer, LinkedList<Integer>> valuePositions = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; ++i) {

            // if [i] equals to [i-1] and [i+1], we should never jump to it during BFS.
            // this optimization is important to efficiently handle this situation:
            // 7, 7, 7, 7, ... 7, 11
            if (i > 0 && i + 1 < arr.length && arr[i] == arr[i - 1] && arr[i] == arr[i + 1])
                continue;

            LinkedList<Integer> positions = valuePositions.get(arr[i]);
            if (positions == null) {
                positions = new LinkedList<>();
                positions.push(i);
                valuePositions.put(arr[i], positions);
            } else {
                positions.push(i);
            }
        }
        return valuePositions;
    }

    int breadthFirstSearch(int[] arr, HashMap<Integer, LinkedList<Integer>> graph) {
        if (arr.length == 1) return 0;

        int n = arr.length;

        // current layer of nodes
        LinkedList<Integer> steps = new LinkedList<>();
        // next layer of nodes
        LinkedList<Integer> nextSteps = new LinkedList<>();
        boolean found = false;
        int depth = 0;

        // start from [0]
        steps.add(0);

        // never revisit a node
        BitSet visited = new BitSet();

        while (!found) {
            ++depth;
//            System.out.printf("Searching... depth %d, breadth %d%n", depth, steps.size());

            // collect all next steps
            nextSteps.clear();
            for (Integer curr : steps) {
                // move to right?
                if (curr + 1 < n && !visited.get(curr + 1)) {
                    if (curr + 1 == n - 1) {
                        found = true;
                        break;
                    } else {
                        nextSteps.push(curr + 1);
                        visited.set(curr + 1);
                    }
                }

                // move to left?
                if (curr > 0 && !visited.get(curr - 1)) {
                    nextSteps.push(curr - 1);
                    visited.set(curr - 1);
                }

                // move to any shortcut?
                LinkedList<Integer> shortcuts = graph.get(arr[curr]);
                if (shortcuts != null) {
                    for (Integer i : shortcuts) {
                        if (!visited.get(i)) {
                            if (i == n - 1) {
                                found = true;
                                break;
                            }
                            nextSteps.push(i);
                            visited.set(i);
                        }
                    }
                    if (found) break;
                }
            }

            LinkedList<Integer> t = steps;
            steps = nextSteps;
            nextSteps = t;
        }

        return depth;
    }

    @Test
    void test_buildGraph_singleNode() {
        int[] arr = new int[]{1};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);
        Assertions.assertNull(graph.get(0));
    }

    @Test
    void test_buildGraph_none() {
        int[] arr = new int[]{1,2};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);
        Assertions.assertNull(graph.get(0));
    }

    @Test
    void test_buildGraph_twoMuture() {
        int[] arr = new int[]{1,1};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);
        Assertions.assertEquals(2, graph.get(1).size());
        Assertions.assertTrue(graph.get(1).contains(0));
        Assertions.assertTrue(graph.get(1).contains(1));
    }

    @Test
    void test_buildGraph_moreThanOneShortcutsFromOnePoint() {
        int[] arr = new int[]{1,1,0,1};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);
        Assertions.assertEquals(3, graph.get(1).size());
        Assertions.assertTrue(graph.get(1).contains(0));
        Assertions.assertTrue(graph.get(1).contains(1));
        Assertions.assertTrue(graph.get(1).contains(3));
        Assertions.assertEquals(1, graph.get(0).size());
        Assertions.assertTrue(graph.get(0).contains(2));
    }

    @Test
    void test_buildGraph_moreThanOneShortcutsFromMultiplePoints() {
        int[] arr = new int[]{1,1,0,1,0};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);

        // 1 @ 0, 1, 3
        Assertions.assertEquals(3, graph.get(1).size());
        Assertions.assertTrue(graph.get(1).contains(0));
        Assertions.assertTrue(graph.get(1).contains(1));
        Assertions.assertTrue(graph.get(1).contains(3));

        // 0 @ 2, 4
        Assertions.assertEquals(2, graph.get(0).size());
        Assertions.assertTrue(graph.get(0).contains(2));
        Assertions.assertTrue(graph.get(0).contains(4));
    }

    @Test
    void test_buildGraph_example1() {
        int[] arr = new int[]{100,-23,-23,404,100,23,23,23,3,404};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);

        // 100 @ 0, 4
        Assertions.assertEquals(2, graph.get(100).size());
        Assertions.assertTrue(graph.get(100).contains(0));
        Assertions.assertTrue(graph.get(100).contains(4));

        // -23 @ 1, 2
        Assertions.assertEquals(2, graph.get(-23).size());
        Assertions.assertTrue(graph.get(-23).contains(1));
        Assertions.assertTrue(graph.get(-23).contains(2));

        // 404 @ 3, 9
        Assertions.assertEquals(2, graph.get(404).size());
        Assertions.assertTrue(graph.get(404).contains(3));
        Assertions.assertTrue(graph.get(404).contains(9));

        // 23 @ 5, 7
        Assertions.assertEquals(2, graph.get(23).size());
        Assertions.assertTrue(graph.get(23).contains(5));
        Assertions.assertTrue(graph.get(23).contains(7));

        // 3 @ 8
        Assertions.assertEquals(1, graph.get(3).size());
        Assertions.assertTrue(graph.get(3).contains(8));
    }

    /**
     * If there is a sequence of duplicate number, ignore them except the head and the tail.
     */
    @Test
    void test_buildGraph_ignoreStreakBody() {
        int[] arr = new int[]{7,7,7,7,7,7,7,7,9};
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);

        // 7 @ 0,7
        Assertions.assertEquals(2, graph.get(7).size());
        Assertions.assertTrue(graph.get(7).contains(0));
        Assertions.assertFalse(graph.get(7).contains(1));
        Assertions.assertFalse(graph.get(7).contains(2));
        Assertions.assertFalse(graph.get(7).contains(3));
        Assertions.assertFalse(graph.get(7).contains(4));
        Assertions.assertFalse(graph.get(7).contains(5));
        Assertions.assertFalse(graph.get(7).contains(6));
        Assertions.assertTrue(graph.get(7).contains(7));

        // 9 @ 8
        Assertions.assertEquals(1, graph.get(9).size());
        Assertions.assertTrue(graph.get(9).contains(8));
    }

    @Test
    void test_buildGraph_hugeN_worstCase() {
        int[] arr = new int[10000];
        Arrays.fill(arr, 7);
        arr[arr.length - 1] = 11;
        HashMap<Integer, LinkedList<Integer>> graph = buildGraph(arr);
    }

    @Test
    void test_minJumps_singleNode() {
        int[] arr = new int[]{7};
        Assertions.assertEquals(0, minJumps(arr));
    }

    @Test
    void test_minJumps_noShortcuts() {
        int[] arr = new int[]{6,1,9};
        Assertions.assertEquals(2, minJumps(arr));
    }

    @Test
    void test_minJumps_example1() {
        int[] arr = new int[]{100,-23,-23,404,100,23,23,23,3,404};
        Assertions.assertEquals(3, minJumps(arr));
    }

    @Test
    void test_minJumps_example3() {
        int[] arr = new int[]{7,6,9,6,9,6,9,7};
        Assertions.assertEquals(1, minJumps(arr));
    }

    @Test
    void test_minJumps_example5() {
        int[] arr = new int[]{11,22,7,7,7,7,7,7,7,22,13};
        Assertions.assertEquals(3, minJumps(arr));
    }

    @Test
    void test_minJumps_hugeN_random() {
        int[] arr = new int[50000];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (int) (Math.random() * 2 * 100000000 - 100000000);
        }
        // I don't care the result, just finish it quickly.
        minJumps(arr);
    }

    @Test
    void test_breadth_hugeN_worstCase() {
        int[] arr = new int[50000];
        Arrays.fill(arr, 7);
        arr[arr.length - 1] = 11;
        Assertions.assertEquals(2, minJumps(arr));
    }
}
