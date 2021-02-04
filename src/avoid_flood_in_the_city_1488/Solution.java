package avoid_flood_in_the_city_1488;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Strategies:
 *  (1) Don't fill a lake unless have to;
 *  (2) If have to, do it as early as possible.
 *
 * To archive this goal, we
 *  (1) remember each lake's last filled day;
 *  (2) remember all unused non-raining days.
 *
 * For a given raining day, we have to find the first dry day after the corresponding lake was
 * previously filled.
 *
 * Created by Zhiyong Pan on 2021-02-02.
 */
public class Solution {
    public int[] avoidFlood(int[] rains) {
        // key = lake
        // value = filled day
        HashMap<Integer, Integer> filledLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();
        int[] ans = new int[rains.length];

        // If it rains, [i] will be -1;
        // if need to do dry work, [i] will be the lake id;
        // otherwise, it's OK to leave it as 1 (or any other valid lake id, that is, any number between 1 and 10^9).
        Arrays.fill(ans, 1);

        for (int i = 0; i < rains.length; ++i) {
            if (rains[i] == 0) {
                dryDays.add(i);
            } else {
                ans[i] = -1;

                int lake = rains[i];
                Integer filledDay = filledLakes.get(lake);
                if (filledDay == null) {
                    filledLakes.put(lake, i);
                } else if (dryDays.size() > 0) {
                    // Find a earliest dry day that is after the filledDay
                    Integer dryDay = dryDays.ceiling(filledDay);
                    if (dryDay == null) {
                        ans = new int[]{};
                        break;
                    } else {
                        ans[dryDay] = lake;
                        filledLakes.put(lake, i); // update filled day
                        dryDays.remove(dryDay);
                    }
                } else {
                    ans = new int[]{};
                    break;
                }
            }
        }

        return ans;
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
