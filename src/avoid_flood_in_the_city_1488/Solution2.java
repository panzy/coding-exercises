package avoid_flood_in_the_city_1488;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Use the same drying strategies as the previous solution, but slightly differ
 * in implementation.
 *
 * This time, TreeSet is not needed. A PriorityQueue is enough.
 *
 * This is where different with the previous solution:
 * for a given non-raining day, find the most dangerous lake.
 *
 * Created by Zhiyong Pan on 2021-02-04.
 */
public class Solution2 {
    static class Lake {
        int idx;

        // top = the nearest raining day for this lake.
        Stack<Integer> rainingDays = new Stack<>();

        // Has this lake been filled?
        boolean filled = false;

        public Lake(int idx) {
            this.idx = idx;
        }
    }

    public int[] avoidFlood(int[] rains) {
        // key = lake idx
        HashMap<Integer, Lake> lakes = new HashMap<>();

        // element = the nearest day that flood would happen if we
        // don't dry the corresponding lake before it.
        PriorityQueue<Integer> dangerousDays = new PriorityQueue<>();

        int[] ans = new int[rains.length];

        // collect raining days for each lake.
        for (int i = rains.length - 1; i >= 0; --i) {
            if (rains[i] != 0) {
                int li = rains[i];
                Lake lake = lakes.get(li);
                if (lake == null) {
                    lake = new Lake(li);
                    lakes.put(li, lake);
                }
                lake.rainingDays.push(i);
            }
        }

        boolean failed = false;

        for (int i = 0; i < rains.length; ++i) {
            if (rains[i] == 0) {
                if (dangerousDays.isEmpty()) {
                    ans[i] = 1; // dry any lake.
                } else {
                    int li = rains[dangerousDays.poll()]; // lake idx
                    ans[i] = li;
                    lakes.get(li).filled = false;
                }
            } else {
                ans[i] = -1;
                Lake lake = lakes.get(rains[i]);
                assert lake.rainingDays.peek() == i;
                lake.rainingDays.pop();

                if (lake.filled) {
                    failed = true;
                    break;
                } else {
                    lake.filled = true;
                    if (!lake.rainingDays.isEmpty()) {
                        dangerousDays.add(lake.rainingDays.peek());
                    }
                }
            }
        }

        return failed ? new int[0] : ans;
    }

    @Test
    void example1() {
        Assertions.assertArrayEquals(new int[]{-1, -1, -1, -1}, avoidFlood(new int[]{1, 2, 3, 4}));
    }

    @Test
    void example2() {
        // Expect [-1, -1, 2, 1, -1, -1] or [-1, -1, 1, 2, -1, -1].
        Assertions.assertArrayEquals(new int[]{-1, -1, 2, 1, -1, -1}, avoidFlood(new int[]{1, 2, 0, 0, 2, 1}));
    }

    @Test
    void example3() {
        Assertions.assertArrayEquals(new int[0], avoidFlood(new int[]{1,2,0,1,2}));
    }

    @Test
    void example4() {
        // Expect Any solution on one of the forms [-1,69,x,y,-1], [-1,x,69,y,-1] or [-1,x,y,69,-1] is acceptable where 1 <= x,y <= 10^9
        Assertions.assertArrayEquals(new int[]{-1, 69, 1, 1, -1}, avoidFlood(new int[]{69, 0, 0, 0, 69}));
    }

    @Test
    void example5() {
        Assertions.assertArrayEquals(new int[]{}, avoidFlood(new int[]{0, 1, 1}));
        Assertions.assertArrayEquals(new int[]{-1, 1, -1}, avoidFlood(new int[]{1, 0, 1}));
    }

    @Test
    void example6() {
        Assertions.assertArrayEquals(new int[]{-1, 1, -1, 2, -1, -1}, avoidFlood(new int[]{1, 0, 2, 0, 1, 2}));
        Assertions.assertArrayEquals(new int[]{-1, 1, -1, 2, -1, -1}, avoidFlood(new int[]{1, 0, 2, 0, 2, 1}));
    }

    @Test
    void example7() {
        Assertions.assertArrayEquals(
                          new int[]{-1, 1, -1, 2, -1, 3, -1, 2, 1, 1, -1, -1, -1},
                avoidFlood(new int[]{1, 0,  2, 0,  3, 0,  2, 0, 0, 0, 1, 2, 3}));
    }
}
