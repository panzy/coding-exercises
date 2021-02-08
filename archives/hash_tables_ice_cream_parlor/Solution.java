package hash_tables_ice_cream_parlor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Hash Tables: Ice Cream Parlor
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
 * Created by Zhiyong Pan on 2021-02-08.
 */
public class Solution {
    static void whatFlavors(int[] cost, int money) {
        int[] pair = helper(cost, money);
        System.out.printf("%d %d%n", pair[0], pair[1]);
    }

    static int[] helper(int[] cost, int money) {
        // cost -> smallest index
        HashMap<Integer, Integer> cost2index = new HashMap<>();

        int[] potentialAns = {-1, -1};

        for (int i = cost.length - 1; i >= 0; --i) {
            if (cost[i] * 2 == money && cost2index.containsKey(cost[i])) {
                potentialAns[0] = i + 1;
                potentialAns[1] = cost2index.get(cost[i]) + 1;
            }
            cost2index.put(cost[i], i);
        }

        for (int i = 0; i < cost.length; ++i) {
            int j = cost2index.getOrDefault(money - cost[i], -1);
            if (i < j) {
                return new int[]{i + 1, j + 1};
            }
        }

        return potentialAns;
    }

    @Test
    void examples() {
        Assertions.assertArrayEquals(new int[]{1, 4}, helper(new int[]{1, 4, 5, 3, 2}, 4));
        Assertions.assertArrayEquals(new int[]{1, 2}, helper(new int[]{2, 2, 4, 3}, 4));
        Assertions.assertArrayEquals(new int[]{1, 5}, helper(new int[]{1, 2, 2, 4, 3}, 4));
        Assertions.assertArrayEquals(new int[]{2, 4}, helper(new int[]{5, 2, 4, 2, 2, 4, 3}, 4));
    }

}
