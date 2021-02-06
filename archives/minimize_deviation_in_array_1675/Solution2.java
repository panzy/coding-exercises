package minimize_deviation_in_array_1675;

import java.util.PriorityQueue;

/**
 * This is just a variant of the previous solution.
 *
 * Make all numbers be their maximum possible values at the beginning, so that we
 * only have to search in one direction -- decreasing a number.
 *
 * It's a bit less efficient than the previous solution, which starts the search from minimal possible values
 * rather than maximal values. I think that's obvious because when you x2 some numbers at the preprocess stage,
 * the deviation is also enlarged. For example, in previous solution:
 *      [1, 4] --minimize-numbers--> [1, 2] --minimum-deviation-> 2-1 = 1
 * While in this solution:
 *      [1, 4] --maximize-numbers--> [2, 4] --iterations-> [1, 2] --minimum-deviation-> 2-1 = 1
 *
 * Created by Zhiyong Pan on 2021-01-30.
 */
public class Solution2 {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int L = 0;

        for (int i : nums) {
            if (i % 2 == 1)
                i *= 2;
            maxHeap.add(i);

            if (L == 0 || L > i)
                L = i;
        }

        int ans = maxHeap.peek() - L;

        while (maxHeap.peek() % 2 == 0) {
            int t = maxHeap.poll() / 2;

            // This is just an optimization. It has no impact on the correctness.
            while (t % 2 == 0 && t / 2 >= L)
                t /= 2;

            maxHeap.add(t);
            L = Math.min(L, t);
            int U = Math.max(maxHeap.peek(), t);
            ans = Math.min(ans, U - L);
        }

        return ans;
    }
}
