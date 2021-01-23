package minimum_number_of_people_to_teach_1733;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Did it in a contest. Not optimized.
 *
 * Created by Zhiyong Pan on 2021-01-23.
 */
public class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;

        // key = language index (0-based)
        // value = number of users want to learn this lang
        int[] wanted = new int[n];

        // key = user index (0-based)
        // value = whether this user's wanted list has been registered
        boolean[] enrolled = new boolean[m];

        for (int[] p : friendships) {
            int u = p[0] - 1;
            int v = p[1] - 1;

            boolean shared = false;
            for (int i = 0; i < languages[u].length; ++i) {
                for (int j = 0; j < languages[v].length; ++j) {
                    if (languages[u][i] == languages[v][j]) {
                        shared = true;
                        break;
                    }
                }
                if (shared)
                    break;
            }

            if (!shared) {
                for (int l = 0; l < n; ++l) {
                    if (!enrolled[u] && !contains(languages[u], l + 1)) {
                        ++wanted[l];
                    }
                    if (!enrolled[v] && !contains(languages[v], l + 1)) {
                        ++wanted[l];
                    }
                }
                enrolled[u] = true;
                enrolled[v] = true;
            }
        }

        int ans = wanted[0];
        for (int i = 1; i < n; ++i)
            ans = Math.min(ans, wanted[i]);

        return ans;
    }

    boolean contains(int[] arr, int val) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == val)
                return true;
        }
        return false;
    }

    @Test
    void example1() {
        Assertions.assertEquals(1, minimumTeachings(
                2,
                new int[][]{{1}, {2}, {1, 2}},
                new int[][]{{1, 2}, {1, 3}, {2, 3}})
        );
    }

    @Test
    void example2() {
        Assertions.assertEquals(2, minimumTeachings(
                3,
                new int[][]{{2}, {1, 3}, {1, 2}, {3}},
                new int[][]{{1, 4}, {1, 2}, {3, 4}, {2, 3}})
        );
    }

    @Test
    void example3() {
        Assertions.assertEquals(0, minimumTeachings(
                3,
                new int[][]{{2}, {1, 3}, {1, 2}, {3}},
                new int[][]{{2, 3}})
        );
        Assertions.assertEquals(0, minimumTeachings(
                3,
                new int[][]{{2}, {1, 3}, {1, 2}, {3}},
                new int[][]{{2, 4}})
        );
        Assertions.assertEquals(1, minimumTeachings(
                3,
                new int[][]{{2}, {1, 3}, {1, 2}, {3}},
                new int[][]{{3, 4}})
        );
    }
}
