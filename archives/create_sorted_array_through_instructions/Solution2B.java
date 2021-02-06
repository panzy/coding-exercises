package create_sorted_array_through_instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Based on the previous solution, I turned the array into an implicit full tree:
 *
 * - The range [0, N) is the original array, each element represents a number's occurrence times.
 *   It's also the bottom layer of the tree.
 * - The range [N, N + n1) is the 2nd layer from the bottom. Each element is the sum of 100
 *   numbers' occurrence times.
 * - The range [N + n1, N + n1 + n2) is the 3rd layer from the bottom, Each element is the sum of 1000
 *   numbers' occurrence times.
 * - And so on.
 *
 * This solution has a not-bad speed, beating 43% of Java submissions.
 *
 * Then, I learnt that Binary Indexed Tree or Fenwick Tree is designed exactly for this task.
 *
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class Solution2B {
    /**
     * The original array's size.
     *
     * Since 1 <= instructions[i] <= 100_000, N should be no less than 100_000 - 1 + 1 = 100_000.
     */
    int N;
    /**
     * Tree node's degree.
     */
    int degree;
    /**
     * How many numbers does a leaf node represents?
     */
    int leafNodeSize;
    /**
     * [0, N) is the original array. The following is the tree.
     */
    int[] index;

    public int createSortedArray(int[] instructions) {
        final int MIN_NUMBER = 1;
        final int MAX_NUMBER = 100_000;
        initIndex(MAX_NUMBER - MIN_NUMBER + 1, 10, 100);

        long cost = 0;

        Arrays.fill(index, 0);

        int insCnt = 0;
        for (int num : instructions) {

            // the costs of each of the two approach:
            // 1. shift less numbers left, or
            // 2. shift greater numbers right.
            int cost1 = countRange(0, num - MIN_NUMBER);
            int cost2 = insCnt - cost1 - index[num - MIN_NUMBER];

            cost += Math.min(cost1, cost2);
            increaseCounter(num - MIN_NUMBER);
            ++insCnt;
        }

        // "Since the answer may be large, return it modulo 109 + 7"
        return (int) (cost % (1_000_000_000 + 7));
    }

    /**
     * Allocate the implicit tree.
     * @param n total number of numbers. It has to be a power of the |degree|.
     * @param degree how many children does a tree node have?
     * @param leafNodeSize how many numbers does a leaf node represents?
     */
    private void initIndex(int n, int degree, int leafNodeSize) {
        this.N = n;
        this.degree = degree;
        int totalNodes = n;
        for (int layerNodes = degree; n / layerNodes >= leafNodeSize; layerNodes *= degree) {
            totalNodes += layerNodes;
            this.leafNodeSize = n / layerNodes;
        }

        // a implicit tree
        index = new int[totalNodes];
    }

    void increaseCounter(int num) {
        ++index[num];

        int offset = N;
        int nodeSize = leafNodeSize;
        while (nodeSize < N) {
            ++index[offset + num / nodeSize];
            offset += N / nodeSize;
            nodeSize *= degree;
        }
    }

    int countRange(int beginNum, int endNum) {
        int cost = 0;

        // find the appropriate node size
        int nodeSize = N / degree;
        while (beginNum + nodeSize > endNum) {
            nodeSize /= degree;
        }

        if (nodeSize >= leafNodeSize) {
            int nodePos = N;
            for (int nodeSize2 = leafNodeSize; nodeSize2 < nodeSize; nodeSize2 *= degree) {
                nodePos += N / nodeSize2;
            }
            nodePos += beginNum / nodeSize;

            while (beginNum + nodeSize <= endNum) {
                cost += index[nodePos];
                beginNum += nodeSize;
                ++nodePos;
            }
            if (beginNum < endNum)
                cost += countRange(beginNum, endNum);
        } else {
            for (int i = beginNum; i < endNum; ++i) {
                cost += index[i];
            }
        }

        return cost;
    }

    @Test
    void testIndex() {
        // init a small index

        initIndex(100, 10, 10);
        Assertions.assertEquals(110, index.length);

        // all empty
        Assertions.assertEquals(0, countRange(0, 100));

        increaseCounter(0);
        Assertions.assertEquals(1, index[0]);
        Assertions.assertEquals(1, index[100]);
        Assertions.assertEquals(1, countRange(0, 100));

        increaseCounter(21);
        Assertions.assertEquals(1, index[21]);
        Assertions.assertEquals(1, index[102]);
        Assertions.assertEquals(1, countRange(0, 21));
        Assertions.assertEquals(2, countRange(0, 22));

        increaseCounter(99);
        Assertions.assertEquals(1, index[99]);
        Assertions.assertEquals(1, index[109]);
        Assertions.assertEquals(2, countRange(0, 99));
        Assertions.assertEquals(3, countRange(0, 100));
    }

    @Test
    void testIndex_2() {
        // init a small index

        initIndex(1000, 10, 10);
        Assertions.assertEquals(1110, index.length);

        // all empty
        Assertions.assertEquals(0, countRange(0, 1000));

        increaseCounter(0);
        Assertions.assertEquals(1, index[0]);
        Assertions.assertEquals(1, index[1000]);
        Assertions.assertEquals(1, index[1100]);
        Assertions.assertEquals(1, countRange(0, 1000));

        increaseCounter(21);
        Assertions.assertEquals(1, index[21]);
        Assertions.assertEquals(1, index[1002]);
        Assertions.assertEquals(2, index[1100]);
        Assertions.assertEquals(1, countRange(0, 21));
        Assertions.assertEquals(2, countRange(0, 22));

        increaseCounter(999);
        Assertions.assertEquals(1, index[999]); // the first layer with cell size = 1
        Assertions.assertEquals(1, index[1099]); // the next layer with cell size = 10
        Assertions.assertEquals(1, index[1109]); // the next layer with cell size = 100
        Assertions.assertEquals(2, countRange(0, 999));
        Assertions.assertEquals(3, countRange(0, 1000));

        increaseCounter(500);
        Assertions.assertEquals(1, index[500]);
        Assertions.assertEquals(1, index[1050]);
        Assertions.assertEquals(1, index[1105]);
        Assertions.assertEquals(2, countRange(0, 500));
        Assertions.assertEquals(4, countRange(0, 1000));
    }

    @Test
    void testIndex_3() {
        // init a small index
        initIndex(100_000, 10, 100);
        Assertions.assertEquals(101110, index.length);
        increaseCounter(0);
    }
}