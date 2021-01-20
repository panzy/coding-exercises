package maximum_length_of_pair_chain_646;

import java.util.Arrays;

/**
 * Based on {@link longest_increasing_subsequence_300.Solution}.
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public int findLongestChain(int[][] pairs) {

        // Since we are given a SET of pairs, to maximize the longest increasing subsequence,
        // we sort the pairs first.
        // For pairs that share a left edge, sort them by right edge in descent order to make
        // sure at most one of them can be selected by the LIS.
        Arrays.sort(pairs, (p1, p2) -> {
            if (p1[0] != p2[0])
                return p1[0] - p2[0];
            else
                return p2[1] - p1[1];
        });

        int n = pairs.length;

        // [i] = how many pairs before pairs[i] are less than pairs[i]?
        int[] cnts = new int[n];
        Arrays.fill(cnts, 0);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (pairs[j][1] < pairs[i][0]) {
                    cnts[i] = Math.max(cnts[i], 1 + cnts[j]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, cnts[i]);
        }

        return ans + 1;
    }
}
