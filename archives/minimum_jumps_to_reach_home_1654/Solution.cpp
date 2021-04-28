/**
 * 1654. Minimum Jumps to Reach Home
 * https://leetcode.com/problems/minimum-jumps-to-reach-home/
 * --
 * Zhiyong 2021-04-28
 */
class Solution {
    public:
        int minimumJumps(const vector<int>& forbidden, int a, int b, int x) {
            // A BFS approach.

            // We have constraints:
            // 1 <= a, b, forbidden[i] <= 2000
            // 0 <= x <= 2000
            int maxPos = 2000 + 2 * max(a, b) + 1; // XXX why?

            // table[i] == 0 initially;
            // table[i] == -1 if i is a forbidden position;
            // table[i] == the number of jumps it takes to get to position i if i > 0 && table[i] > 0;
            vector<int> table(maxPos, 0);

            // backwardUsed[i] = true if we have jumped backward at least once from position i.
            vector<bool> backwardUsed(maxPos, false);

            for (int p : forbidden)
                table[p] = -1;

            // Q[i] = {position, whether jump backward is allowed from here}
            queue<pair<int, bool>> Q;

            Q.push({0, false});

            while (!Q.empty()) {
                auto [pos, allowBack] = Q.front();
                Q.pop();

                int jumps = table[pos];

                if (pos == x)
                    return jumps;

                // jump forward
                if (pos + a < maxPos && (
                            table[pos + a] == 0 ||
                            table[pos + a] > jumps + 1 ||
                            (table[pos + a] > 0 && !backwardUsed[pos + a]))) {
                    table[pos + a] = jumps + 1;
                    Q.push({pos + a, true});
                }

                // jump backward
                if (allowBack && pos - b > 0 && (
                            table[pos - b] == 0 ||
                            table[pos - b] > jumps + 1)) {
                    table[pos - b] = jumps + 1;
                    backwardUsed[pos] = true;
                    Q.push({pos - b, false});
                }
            }

            return -1;
        }
};
