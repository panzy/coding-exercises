package daily_temperatures;

import _lib.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Another application of monotonic stack.
 *
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
class Solution {
    public int[] dailyTemperatures(int[] T) {

        int[] ans = new int[T.length];

        // A monotonic stack.
        // pair = array index -> element value
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.ensureCapacity(T.length);

        for (int i = T.length - 1; i >= 0; --i) {
            // pop all numbers that are not greater than [i]
            while (!stack.isEmpty() && stack.peek().getValue() <= T[i])
                stack.pop();

            ans[i] = stack.isEmpty() ? 0: stack.peek().getKey() - i;

            stack.push(new Pair(i, T[i]));
        }

        return ans;
    }
}

class SolutionTest {
    Solution solution = new Solution();

    @Test
    void example1() {
        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
        Assertions.assertArrayEquals(expected, solution.dailyTemperatures(T));
    }
}
