package average_waiting_time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    void averageWaitingTime_case1() {
        int[][] customers = new int[][] { {1, 2}, {2, 5}, {4, 3} };
        double expected = 5.0;
        Assertions.assertEquals(expected, solution.averageWaitingTime(customers), 0.0001);
    }

    @Test
    void averageWaitingTime_case2() {
        int[][] customers = new int[][] { {5, 2}, {5, 4}, {10, 3}, {20, 1} };
        double expected = 3.25;
        Assertions.assertEquals(expected, solution.averageWaitingTime(customers), 0.0001);
    }
}
