package rectangle_area_223;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Zhiyong Pan on 2021-01-17.
 */
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E) return computeArea(E, F, G, H, A, B, C, D);

        int overlapWidth = overlapLen(A, C, E, G);
        int overlapHeight = overlapLen(B, D, F, H);

        assert overlapWidth >= 0;
        assert overlapHeight >= 0;

        return (C - A) * (D - B) + (G - E) * (H - F) - overlapWidth * overlapHeight;
    }

    /**
     * Given two segments along a same axis, find the overlapped length.
     */
    private int overlapLen(int A, int B, int C, int D) {
        if (A == B || C == D) return 0;
        if (A > C) return overlapLen(C, D, A, B);

        assert A <= B;
        assert C <= D;
        assert A <= C;

        if (B < C) return 0;
        assert A <= C && C <= B;
        return Math.min(B, D) - C;
    }

    @Test
    void example1() {
        Assertions.assertEquals(45, computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    @Test
    void example2() {
        // totally contains
        Assertions.assertEquals(24, computeArea(-3, 0, 3, 4, 1, 1, 2, 2));
    }

    @Test
    void example3() {
        // separated
        Assertions.assertEquals(26, computeArea(-3, 0, 3, 4, 3, 1, 5, 2));
    }

    @Test
    void example4() {
        // covers right-side
        Assertions.assertEquals(42, computeArea(-3, 0, 3, 4, -5, -1, 0, 5));
    }
}
