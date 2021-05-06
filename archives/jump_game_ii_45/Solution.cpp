/* 45. Jump Game II
 * https://leetcode.com/problems/jump-game-ii/
 * --
 * Zhiyong 2021-05-05
 */


/* Approach #0 BFS
 * 92 / 92 test cases passed, but took too long.
 * Status: Time Limit Exceeded
 */
class Solution0 {
    public:
        int jump(vector<int>& nums) {
            size_t n = nums.size();

            // Q[i] = {position, jumps}
            queue<pair<int, int>> Q;
            bitset<1001> seen;

            Q.push({0, 0});

            while (!Q.empty()) {
                auto [p, j] = Q.front();
                Q.pop();

                if (p == n - 1)
                    return j;

                if (p + nums[p] >= n - 1)
                    return j + 1;

                seen[p] = true;

                for (int d = 1; d <= nums[p]; ++d) {
                    if (!seen[p + d] && nums[p + d] > 0) {
                        Q.push({p + d, j + 1});
                    }
                }
            }

            return -1;
        }
};

/* Approach #1 Trivial DP, O(n^2) where n is nums.length.
 * Stuatus: (did not submit)
 */
class Solution1 {
public:
    int jump(vector<int>& nums) {
        size_t n = nums.size();
        // dist[i] = how many jumps does it take from position 0 to i?
        vector<int> dist(n, 1000);
        dist[0] = 0;
        
        for (size_t i = 0; i < n; ++i) {
            for (int j = 1; j <= nums[i] && i + j < n; ++j) {
                dist[i + j] = min(dist[i + j], dist[i] + 1);
            }
        }
        
        return dist[n - 1];
    }
};

/* Approach #2 DP, O(n) where n is nums.length.
 * Status: Accepted
 */
class Solution2 {
    public:
        int jump(vector<int>& nums) {
            size_t n = nums.size();
            // dist[i] = how many jumps does it take from position 0 to i?
            vector<int> dist(n, 1000);
            dist[0] = 0;

            int prevScope = 0;
            for (int i = 0; i < n; ++i) {
                int currScope = i;
                // start from j where i + j == prevScope + 1
                for (int j = prevScope + 1 - i; j <= nums[i] && i + j < n; ++j) {
                    dist[i + j] = min(dist[i + j], dist[i] + 1);
                    currScope = max(currScope, i + j);
                }
                prevScope = currScope;
            }

            return dist[n - 1];
        }
};
