package create_sorted_array_through_instructions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Based on the previous solution, I turned the sparse array into an implicit full tree:
 *
 * - The range [0, 100_000) is the original array, each element represents a number's occurrence times.
 *   It's also the bottom layer of the tree.
 * - The range [100_000, 100_000 + 1000) is the 2nd layer from the bottom. Each element is the sum of 100
 *   numbers' occurrence times.
 * - The range [101_000, 101_000 + 100) is the 3rd layer from the bottom, Each element is the sum of 1000
 *   numbers' occurrence times.
 * - And so on.
 *
 * Created by Zhiyong Pan on 2021-01-10.
 */
public class Solution2B {
    int count;
    int splits;
    int minNodeSize;
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
     * @param count total number of numbers. It has to be a power of the |splits|.
     * @param splits how many children does a tree node have?
     * @param minNodeSize
     */
    private void initIndex(int count, int splits, int minNodeSize) {
        this.count = count;
        this.splits = splits;
        int totalNodes = count;
        for (int layerNodes = splits; count / layerNodes >= minNodeSize; layerNodes *= splits) {
            totalNodes += layerNodes;
            this.minNodeSize = count / layerNodes;
        }

        // a implicit tree
        index = new int[totalNodes];
    }

    void increaseCounter(int num) {
        ++index[num];

        int offset = count;
        int nodeSize = minNodeSize;
        while (nodeSize < count) {
            ++index[offset + num / nodeSize];
            offset += count / nodeSize;
            nodeSize *= splits;
        }
    }

    int countRange(int beginNum, int endNum) {
        int cost = 0;

        // find the appropriate node size
        int nodeSize = count / splits;
        while (beginNum + nodeSize > endNum) {
            nodeSize /= splits;
        }

        if (nodeSize >= minNodeSize) {
            int nodePos = count;
            for (int nodeSize2 = minNodeSize; nodeSize2 < nodeSize; nodeSize2 *= splits) {
                nodePos += count / nodeSize2;
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