package recursion_stair_permutation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Recursion: Davis' Staircase
 * https://www.hackerrank.com/challenges/ctci-recursive-staircase/problem
 *
 * Created by Zhiyong Pan on 2021-02-11.
 */
public class Stairs {
    static final long mod = 10_000_000_007L;
    static int[] memo = null;

    static int stepPerms(int n) {
        memo = new int[n + 1];
        return helper(n);
    }

    static int helper(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;

        if (memo[n] == 0)
            memo[n] = (int) ((helper(n - 1) + helper(n - 2) + helper(n - 3)) % mod);
        return memo[n];
    }

    @Test
    void test() {
        Assertions.assertEquals(13, stepPerms(5));
        Assertions.assertEquals(44, stepPerms(7));
        Assertions.assertEquals(81, stepPerms(8));
        Assertions.assertEquals(5768, stepPerms(15));
        Assertions.assertEquals(121415, stepPerms(20));
        Assertions.assertEquals(8646064, stepPerms(27));
    }
}
