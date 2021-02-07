package algorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A sorted integer array of a limited collection of values, eg, 0 <= a[i] <= 200,
 * can be presented by a frequency array.
 *
 * By converting a sorted array to its frequency array, insertions and deletions become O(1) rather than O(n),
 * while random accessing is still efficient enough, changing from O(1) to O(m), where m is the length of the
 * frequency array, which should be very small compared to n.
 *
 * This class facilitates the random accessing to the imagined original array via the frequency array.
 *
 * Too see how this can be useful, check out the Fraudulent Activity Notifications problem below.
 *
 * Created by Zhiyong Pan on 2021-02-07.
 */
public class CountSort {
    // Returns the nth-element of the sorted array's position in the frequency array.
    // n starts from 1.
    public static int nthElement(int[] freq, int n) {
        int cnt = 0;
        int fi = 0;
        while (fi < freq.length && cnt + freq[fi] < n) {
            cnt += freq[fi];
            ++fi;
        }
        return fi;
    }

    @Test
    void testCountSort() {
        int[] freq = {0, 0, 2, 2, 1};// for array {2, 2, 3, 3, 4};
        Assertions.assertEquals(2, nthElement(freq, 1));
        Assertions.assertEquals(2, nthElement(freq, 2));
        Assertions.assertEquals(3, nthElement(freq, 3));
        Assertions.assertEquals(3, nthElement(freq, 4));
        Assertions.assertEquals(4, nthElement(freq, 5));
        Assertions.assertEquals(5, nthElement(freq, 6));
    }
}

/**
 * An example problem.
 *
 * Fraudulent Activity Notifications
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications
 */
class FraudulentActivityNotifications {
    static int activityNotifications(int[] expenditure, int d) {
        // Since expenditure[i] is constrained to between [0, 200].
        int[] freq = new int[201];
        int notis = 0;

        for (int i = 0; i < expenditure.length; ++i) {
            if (i >= d) {
                int medianX2 = d % 2 == 1 ?
                        CountSort.nthElement(freq, d / 2 + 1) * 2 :
                        (CountSort.nthElement(freq, d / 2) + CountSort.nthElement(freq, d / 2 + 1));
                if (expenditure[i] >= medianX2) {
                    ++notis;
                }
                --freq[expenditure[i - d]];
            }
            ++freq[expenditure[i]];
        }

        return notis;
    }

    @Test
    void examples() {
        Assertions.assertEquals(2, activityNotifications(new int[]{2, 3, 4, 2, 3, 6, 8, 4, 5}, 5));
        Assertions.assertEquals(0, activityNotifications(new int[]{1, 2, 3, 4, 4}, 4));
    }

    @Test
    void testcase2() {
        Assertions.assertEquals(1, activityNotifications(new int[]{10, 20, 30, 40, 50}, 3));
    }
}