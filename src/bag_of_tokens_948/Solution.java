package bag_of_tokens_948;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Simulates the game with this strategy:
 *  1. spend the smallest token to earn score,
 *  2. spend the largest token to earn power.
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution {
    public int bagOfTokensScore(int[] tokens, int P) {
        int n = tokens.length;
        int score = 0;
        int i = 0, j = n - 1;

        if (n == 0)
            return 0;

        Arrays.sort(tokens);

        while (i < j) {
            if (P >= tokens[i]) {
                // Spend the smallest token to earn a score.
                P -= tokens[i];
                ++score;
                ++i;
            } else if (score > 0) {
                // Spend the largest token to earn some power.
                P += tokens[j];
                --score;
                --j;
            } else {
                break;
            }
        }

        // Spend the last remaining token.
        if (P >= tokens[i]) {
            ++score;
        }

        return score;
    }

    @Test
    void testBagOfTokens() {
        Assertions.assertEquals(0, bagOfTokensScore(new int[]{100}, 50));
        Assertions.assertEquals(1, bagOfTokensScore(new int[]{100, 200}, 150));
        Assertions.assertEquals(1, bagOfTokensScore(new int[]{200, 100}, 150));
        Assertions.assertEquals(2, bagOfTokensScore(new int[]{100, 200, 300, 400}, 200));
        Assertions.assertEquals(2, bagOfTokensScore(new int[]{100, 100, 100, 200}, 100));
        Assertions.assertEquals(0, bagOfTokensScore(new int[]{}, 85));
        Assertions.assertEquals(1, bagOfTokensScore(new int[]{25, 91}, 99));
        Assertions.assertEquals(0, bagOfTokensScore(new int[]{71, 55, 82}, 54));
    }
}
