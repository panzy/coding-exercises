package average_waiting_time;

/**
 * Zhiyong Pan, 2020-12-26
 */
class Solution {
    public double averageWaitingTime(int[][] customers) {
        double sum = 0;
        int now = customers[0][0]; // time starts the moment the first customer arrives
        for (int[] c : customers) {
            sum += Math.max(0, now - c[0]) + c[1];
            now = Math.max(now, c[0]) + c[1];
        }
        return sum / customers.length;
    }
}
