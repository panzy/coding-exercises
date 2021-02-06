package two_city_scheduling_1029;

/**
 * Brute force. Time limit exceeded.
 *
 * Created by Zhiyong Pan on 2021-01-28.
 */
public class Solution {
    int n;
    int[][] costs;
    int bestAnswer;

    public int twoCitySchedCost(int[][] costs) {
        n = costs.length / 2;
        this.costs = costs;
        bestAnswer = Integer.MAX_VALUE;
        return fly(0, 0, 0, 0);
    }

    int fly(int i, int aCnt, int bCnt, int totalCost) {
        // Shortcut.
        if (totalCost >= bestAnswer)
            return bestAnswer;

        // All the rest people have to fly to the other city.
        if (aCnt == n) {
            do {
                totalCost += costs[i][1];
                ++i;
            } while (i < costs.length);
            bestAnswer = Math.min(bestAnswer, totalCost);
            return bestAnswer;
        } else if (bCnt == n) {
            do {
                totalCost += costs[i][0];
                ++i;
            } while (i < costs.length);
            bestAnswer = Math.min(bestAnswer, totalCost);
            return bestAnswer;
        } else {
            totalCost = Math.min(
                    fly(i + 1, aCnt + 1, bCnt, totalCost + costs[i][0]),
                    fly(i + 1, aCnt, bCnt + 1, totalCost + costs[i][1])
            );
            bestAnswer = Math.min(bestAnswer, totalCost);
            return bestAnswer;
        }
    }
}
