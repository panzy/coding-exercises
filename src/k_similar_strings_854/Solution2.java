package k_similar_strings_854;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * BFS. Even slower than the previous one. But still it was accepted.
 *
 * Created by Zhiyong Pan on 2021-02-01.
 */
public class Solution2 {

    public int kSimilarity(String A, String B) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int n = a.length;

        // A solution is defined as this:
        // [0] = progress, an index of the char array.
        // [1] = cost so far.
        // [i] and [i+1] = a swapped indices, where i >= 2.
        ArrayDeque<int[]> solutions = new ArrayDeque<>();

        solutions.add(new int[]{0, 0});

        int ans = -1;

        int maxProgress = 0;

        while (ans == -1) {

            ArrayList<int[]> nextLayer = new ArrayList<>();
            for (int[] solution : solutions) {
                int progress = solution[0];

                replaySwaps(a, solution);

                while (progress < n && a[progress] == b[progress])
                    ++progress;

                if (progress == n) {
                    ans = solution[1];
                    break;
                }

                // A swap is needed.
                solution[0] = progress + 1;
                ++solution[1];
                maxProgress = Math.max(maxProgress, progress + 1);

                for (int j = progress + 1; j < n; ++j) {
                    if (a[j] == b[progress]) {
                        // Swap a[progress] and a[j].
                        nextLayer.add(append(solution, progress, j));
                    }
                }

                undoSwaps(a, solution);
            }

            solutions.clear();
            // Sort solutions by their progress.
            Collections.sort(nextLayer, (x, y) -> y[0] - x[0]);
            solutions.addAll(nextLayer);
        }

        return ans;
    }

    // Append a swap operation to a solution.
    private int[] append(int[] solution, int i, int j) {
        int[] r = new int[solution.length + 2];
        int k = 0;
        for (; k < solution.length; ++k) {
            r[k] = solution[k];
        }
        r[k] = i;
        r[k + 1] = j;
        return r;
    }

    private void replaySwaps(char[] a, int[] solution) {
        for (int i = 2; i < solution.length; i += 2) {
            // swap i and i + 1
            char t = a[solution[i]];
            a[solution[i]] = a[solution[i + 1]];
            a[solution[i + 1]] = t;
        }
    }

    private void undoSwaps(char[] a, int[] solution) {
        for (int i = solution.length - 2; i >= 2; i -= 2) {
            // swap i and i + 1
            char t = a[solution[i]];
            a[solution[i]] = a[solution[i + 1]];
            a[solution[i + 1]] = t;
        }
    }

    @Test
    void testK_example1() {
        Assertions.assertEquals(1, kSimilarity("ab", "ba"));
    }

    @Test
    void testK_example2() {
        Assertions.assertEquals(2, kSimilarity("abc", "bca"));
    }

    @Test
    void testK_example3() {
        Assertions.assertEquals(2, kSimilarity("abac", "baca"));
    }

    @Test
    void testK_example4() {
        Assertions.assertEquals(2, kSimilarity("aabc", "abca"));
    }

    @Test
    void testK_example5() {
        Assertions.assertEquals(0, kSimilarity("abcdefabcdef", "abcdefabcdef"));

        // Swapped the first 'a' with the last 'f'.
        Assertions.assertEquals(1, kSimilarity("abcdefabcdef", "fbcdefabcdea"));
    }

    @Test
    void testK_case10() {
        Assertions.assertEquals(8, kSimilarity("abcdeabcdeabcdeabcde", "aaaabbbbccccddddeeee"));
    }
}
