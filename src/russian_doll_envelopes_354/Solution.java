package russian_doll_envelopes_354;

import java.util.Arrays;

/**
 * Based on {@link maximum_length_of_pair_chain_646.Solution}.
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length <= 1)
            return envelopes.length;

        // Since we are given a SET of envelopes, to maximize the longest increasing subsequence,
        // we sort the envelopes first.
        // For envelopes that share a left edge, sort them by right edge in descent order to make
        // sure at most one of them can be selected by the LIS.
        Arrays.sort(envelopes, (p1, p2) -> {
            if (p1[0] != p2[0])
                return p1[0] - p2[0];
            else
                return p2[1] - p1[1];
        });

        int n = envelopes.length;

        // [i] = how many envelopes before envelopes[i] are less than envelopes[i]?
        int[] cnts = new int[n];
        Arrays.fill(cnts, 0);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
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
