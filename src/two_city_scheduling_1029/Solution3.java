package two_city_scheduling_1029;

import java.util.PriorityQueue;

/**
 * Greedy. Accepted.
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution3 {
    public int twoCitySchedCost(int[][] costs) {
        // Use this heap to pick the person with the largest cost difference between the two cities.
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) ->
                Math.abs(costs[b][0] - costs[b][1])
                - Math.abs(costs[a][0] - costs[a][1])
        );

        for (int i = 0; i < costs.length; ++i) {
            heap.add(i);
        }

        int n = costs.length / 2;
        int aCnt = 0, bCnt = 0; // How many people have flight to city a and b, respectively?
        int totalCost = 0;

        while (aCnt < n || bCnt < n) {

            // The i-th person.
            int i = heap.poll();

            if (aCnt < n && (bCnt == n || costs[i][0] <= costs[i][1])) {
                // Fly to city a.
                ++aCnt;
                totalCost += costs[i][0];
            } else {
                // Fly to city b.
                ++bCnt;
                totalCost += costs[i][1];
            }
        }

        return totalCost;
    }
}
