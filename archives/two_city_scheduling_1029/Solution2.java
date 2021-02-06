package two_city_scheduling_1029;

/**
 * Binary search + Brute force. Time limit exceeded.
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution2 {
    int n;
    int[][] costs;

    public int twoCitySchedCost(int[][] costs) {
        n = costs.length / 2;
        this.costs = costs;

        int lo = 1;
        int hi = easyAnswer();

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (fly(0, 0, 0, 0, mi)) {
                hi = mi;
            } else {
                lo = mi + 1;
            }
        }
        return lo;
    }

    private int easyAnswer() {
        int total = 0;
        for (int i = 0; i < n; ++i) {
            total += costs[i][0] + costs[i * 2][1];
        }
        return total;
    }

    boolean fly(int i, int aCnt, int bCnt, int totalCost, int limit) {
        if (totalCost >= limit)
            return false;

        // All the rest people have to fly to the other city.
        if (aCnt == n) {
            for (int j = i; j < costs.length; ++j) {
                totalCost += costs[j][1];
            }
            return totalCost <= limit;
        } else if (bCnt == n) {
            for (int j = i; j < costs.length; ++j) {
                totalCost += costs[j][0];
            }
            return totalCost <= limit;
        } else {
            if (fly(i + 1, aCnt + 1, bCnt, totalCost + costs[i][0], limit))
                return true;
            else
                return fly(i + 1, aCnt, bCnt + 1, totalCost + costs[i][1], limit);
        }
    }
}
