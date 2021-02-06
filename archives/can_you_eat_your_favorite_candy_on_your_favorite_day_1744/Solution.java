package can_you_eat_your_favorite_candy_on_your_favorite_day_1744;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * 1744. Can You Eat Your Favorite Candy on Your Favorite Day?
 *
 * Be aware of the constraints, int32 will overflow!
 *
 * Created by Zhiyong Pan on 2021-01-30.
 */
public class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] candiesCountAccu = new long[candiesCount.length];

        candiesCountAccu[0] = candiesCount[0];
        for (int i = 1; i < candiesCount.length; ++i) {
            candiesCountAccu[i] = candiesCountAccu[i - 1] + candiesCount[i];
        }

        boolean[] ans = new boolean[queries.length];

        for (int i = 0; i < ans.length; ++i) {
            int type = queries[i][0];
            int day = queries[i][1];
            int cap = queries[i][2];
            ans[i] = candiesCountAccu[type] > day // enough
                    && (type == 0 || ((long)day * cap + cap - 1) >= candiesCountAccu[type - 1]);
        }

        return ans;
    }

    @Test
    void testExamples() throws IOException, ParseException {
        Assertions.assertArrayEquals(new boolean[]{false}, canEat(
                new int[]{1},
                new int[][]{{0,100,5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{false}, canEat(
                new int[]{99},
                new int[][]{{0,100,5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{false}, canEat(
                new int[]{100},
                new int[][]{{0,100,5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{true}, canEat(
                new int[]{101},
                new int[][]{{0,100,5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{false}, canEat(
                new int[]{100, 100},
                new int[][]{{1, 19, 5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{true}, canEat(
                new int[]{100, 100},
                new int[][]{{1, 20, 5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{false}, canEat(
                new int[]{105, 100},
                new int[][]{{1, 20, 5}}
        ));
        Assertions.assertArrayEquals(new boolean[]{true, false, true}, canEat(
                new int[]{7,4,5,3,8},
                new int[][]{{0,2,2}, {4,2,4},{2,13,1000000000}}
        ));
        Assertions.assertArrayEquals(new boolean[]{false,true,true,false,false}, canEat(
                new int[]{5,2,6,4,1},
                new int[][]{{3,1,2},{4,10,3},{3,10,100},{4,100,30},{1,3,1}}
        ));
    }
}
