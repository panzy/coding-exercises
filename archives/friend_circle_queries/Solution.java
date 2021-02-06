package friend_circle_queries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by Zhiyong Pan on 2021-02-06.
 */
public class Solution {
    static class Circle {
        private int size;
        Circle parent = null;

        int size() {
            return parent == null ? size : parent.size();
        }

        void increaseSize(int d) {
            root().size += d;
        }

        Circle root() {
            Circle p = this;
            while (p.parent != null)
                p = p.parent;
            return p;
        }
    }

    // Complete the maxCircle function below.
    static int[] maxCircle(int[][] queries) {
        // person id => circle
        HashMap<Integer, Circle> people = new HashMap<>();

        int maxSize = 0;
        int[] ans = new int[queries.length];

        for (int queryIdx = 0; queryIdx < queries.length; ++queryIdx) {
            int[] pair = queries[queryIdx];
            Circle c1 = people.get(pair[0]);
            Circle c2 = people.get(pair[1]);

            if (c1 == null && c2 == null) {
                Circle c = new Circle();
                c.increaseSize(2);
                people.put(pair[0], c);
                people.put(pair[1], c);
                maxSize = Math.max(maxSize, 2);
            } else if (c1 == null) {
                // person 1 joins person 2's circle.
                people.put(pair[0], c2);
                c2.increaseSize(1);
                maxSize = Math.max(maxSize, c2.size());
            } else if (c2 == null) {
                // person 2 joins person 1's circle.
                people.put(pair[1], c1);
                c1.increaseSize(1);
                maxSize = Math.max(maxSize, c1.size());
            } else {
                // Merge the two circles (they may have already merged, but that doesn't matter).
                merge(c1, c2);
                maxSize = Math.max(maxSize, c1.size());
            }
            ans[queryIdx] = maxSize;
        }

        return ans;
    }

    private static void merge(Circle c1, Circle c2) {
        Circle r1 = c1.root();
        Circle r2 = c2.root();
        if (r1 != r2) {
            c1.increaseSize(r2.size());
            r2.parent = r1;
        }
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(new int[]{2, 2, 4, 4, 4, 7},
                maxCircle(new int[][]{
                        {1, 2},
                        {3, 4},
                        {1, 3},
                        {5, 7},
                        {6, 7},
                        {7, 4}
                }));
    }

    @Test
    void test2() {
        Assertions.assertArrayEquals(new int[]{2, 2, 4, 4, 4, 7, 8},
                maxCircle(new int[][]{
                        {1, 2},
                        {3, 4},
                        {1, 3},
                        {5, 7},
                        {6, 7},
                        {7, 4},
                        {8, 2}
                }));
    }
}
