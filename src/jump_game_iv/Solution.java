package jump_game_iv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Breadth First Search.
 * --
 * Zhiyong Pan, 2020-12-27
 */
class Solution {

    private int n;
    // record the shortcuts for each node
    private LinkedList<Integer>[] shortcuts;

    public int minJumps(int[] arr) {
        n = arr.length;
        collectShortcuts(arr);
        return breadthFirstSearch(0);
    }

    void collectShortcuts(int[] arr) {
        shortcuts = new LinkedList[arr.length];
        // for querying node indices by its value
        HashMap<Integer, LinkedList<Integer>> valuePositions = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; ++i) {

            // if [i] equals to [i-1] and [i+1], we should never jump to it during BFS.
            // this optimization is important to successfully handle this example:
            // 7, 7, 7, 7, ... 7, 11
            if (i > 0 && i + 1 < arr.length && arr[i] == arr[i - 1] && arr[i] == arr[i + 1])
                continue;

            LinkedList<Integer> positions = valuePositions.get(arr[i]);
            if (positions == null) {
                positions = new LinkedList<>();
                positions.push(i);
                valuePositions.put(arr[i], positions);
            } else {
                for (int j : positions) {
                    recordShortcut(i, j);
                }
                positions.push(i);
            }
        }
    }

    void recordShortcut(Integer i, Integer j) {
        // record i -> j
        LinkedList<Integer> steps = shortcuts[i];
        if (steps != null) {
            steps.add(j);
        } else {
            steps = new LinkedList<>();
            steps.add(j);
            shortcuts[i] = steps;
        }

        // record j -> i
        steps = shortcuts[j];
        if (steps != null) {
            steps.add(i);
        } else {
            steps = new LinkedList<>();
            steps.add(i);
            shortcuts[j] = steps;
        }
    }

    int breadthFirstSearch(int startIndex) {
        if (startIndex == n - 1) return 0;

        LinkedList<Integer> steps = new LinkedList<>();
        AtomicBoolean found = new AtomicBoolean(false);
        int depth = 0;

        // start from [0]
        steps.add(0);

        // never revisit a node
        BitSet visited = new BitSet();

        while (!found.get()) {
            ++depth;

            LinkedList<Integer> nextSteps = new LinkedList<>();
//            System.out.printf("Searching... depth %d, breadth %d%n", depth, steps.size());

            // collect all next steps
            for (Integer curr : steps) {
                LinkedList<Integer> ns = getNextSteps(curr, found);
                if (found.get()) break;
                for (Integer next : ns) {
                    if (!visited.get(next)) {
                        visited.set(next);
                        nextSteps.push(next);
                    }
                }
            }
            if (found.get()) break;
            steps = nextSteps;
        }

        return depth;
    }

    /**
     * Get the indices of all nodes that can be reached in one jump.
     * @param startIndex
     * @param found output true if [n-1] is one of those indices.
     * @return A list of indices.
     */
    private LinkedList<Integer> getNextSteps(int startIndex, AtomicBoolean found) {
        LinkedList<Integer> steps = new LinkedList<>();
        if (startIndex + 1 < n) {
            steps.push(startIndex + 1);
            if (startIndex + 1 == n - 1)
                found.set(true);
        }
        if (startIndex > 0) {
            steps.push(startIndex - 1);
        }
        if (shortcuts[startIndex] != null) {
            for (Integer i : shortcuts[startIndex]) {
                steps.push(i);
                if (i == n - 1)
                    found.set(true);
            }
        }
        return steps;
    }

    @Test
    void test_collectShortcuts_singleNode() {
        int[] arr = new int[]{1};
        collectShortcuts(arr);
        Assertions.assertNull(shortcuts[0]);
    }

    @Test
    void test_collectShortcuts_none() {
        int[] arr = new int[]{1,2};
        collectShortcuts(arr);
        Assertions.assertNull(shortcuts[0]);
    }

    @Test
    void test_collectShortcuts_twoMuture() {
        int[] arr = new int[]{1,1};
        collectShortcuts(arr);
        Assertions.assertEquals(1, shortcuts[0].getFirst());
        Assertions.assertEquals(0, shortcuts[1].getFirst());
    }

    @Test
    void test_collectShortcuts_moreThanOneShortcutsFromOnePoint() {
        int[] arr = new int[]{1,1,0,1};
        collectShortcuts(arr);
        Assertions.assertEquals(2, shortcuts[0].size());
        Assertions.assertEquals(2, shortcuts[1].size());
        Assertions.assertEquals(2, shortcuts[3].size());
    }

    @Test
    void test_collectShortcuts_moreThanOneShortcutsFromMultiplePoints() {
        int[] arr = new int[]{1,1,0,1,0};
        collectShortcuts(arr);

        Assertions.assertEquals(2, shortcuts[0].size());
        Assertions.assertTrue(shortcuts[0].contains(1));
        Assertions.assertTrue(shortcuts[0].contains(3));

        Assertions.assertEquals(2, shortcuts[1].size());
        Assertions.assertTrue(shortcuts[1].contains(0));
        Assertions.assertTrue(shortcuts[1].contains(3));

        Assertions.assertEquals(1, shortcuts[2].size());
        Assertions.assertTrue(shortcuts[2].contains(4));

        Assertions.assertEquals(2, shortcuts[3].size());
        Assertions.assertTrue(shortcuts[3].contains(0));
        Assertions.assertTrue(shortcuts[3].contains(1));

        Assertions.assertEquals(1, shortcuts[4].size());
        Assertions.assertTrue(shortcuts[4].contains(2));
    }

    @Test
    void test_collectShortcuts_example1() {
        int[] arr = new int[]{100,-23,-23,404,100,23,23,23,3,404};
        collectShortcuts(arr);

        // 0 -> 4
        Assertions.assertEquals(1, shortcuts[0].size());
        Assertions.assertTrue(shortcuts[0].contains(4));

        // 1 -> 2
        Assertions.assertEquals(1, shortcuts[1].size());
        Assertions.assertTrue(shortcuts[1].contains(2));

        // 2 -> 1
        Assertions.assertEquals(1, shortcuts[2].size());
        Assertions.assertTrue(shortcuts[2].contains(1));

        // 3 -> 9
        Assertions.assertEquals(1, shortcuts[3].size());
        Assertions.assertTrue(shortcuts[3].contains(9));

        // 4 -> 0
        Assertions.assertEquals(1, shortcuts[4].size());
        Assertions.assertTrue(shortcuts[4].contains(0));

        // 5 -> 7
        Assertions.assertEquals(1, shortcuts[5].size());
        Assertions.assertTrue(shortcuts[5].contains(7));

        // 6 -> x
        Assertions.assertNull(shortcuts[6]);

        // 7 -> 5
        Assertions.assertEquals(1, shortcuts[7].size());
        Assertions.assertTrue(shortcuts[7].contains(5));

        // 8 -> x
        Assertions.assertNull(shortcuts[8]);

        // 9 -> 3
        Assertions.assertEquals(1, shortcuts[9].size());
        Assertions.assertTrue(shortcuts[9].contains(3));
    }

    /**
     * If there is a sequence of duplicate number, ignore them except the head and the tail.
     */
    @Test
    void test_collectShortcuts_ignoreStreakBody() {
        int[] arr = new int[]{7,7,7,7,7,7,7,7,9};
        collectShortcuts(arr);

        // 0 -> 7
        Assertions.assertEquals(1, shortcuts[0].size());
        Assertions.assertTrue(shortcuts[0].contains(7));
        // 1..6 -> x
        Assertions.assertNull(shortcuts[1]);
        Assertions.assertNull(shortcuts[2]);
        Assertions.assertNull(shortcuts[3]);
        Assertions.assertNull(shortcuts[4]);
        Assertions.assertNull(shortcuts[5]);
        Assertions.assertNull(shortcuts[6]);
        // 7 -> 0
        Assertions.assertEquals(1, shortcuts[7].size());
        Assertions.assertTrue(shortcuts[7].contains(0));
    }

    @Test
    void test_collectShortcuts_hugeN_worstCase() {
        int[] arr = new int[10000];
        Arrays.fill(arr, 7);
        arr[arr.length - 1] = 11;
        collectShortcuts(arr);
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
