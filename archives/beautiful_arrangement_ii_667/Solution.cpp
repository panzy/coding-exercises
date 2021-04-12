/* 667. Beautiful Arrangement II
 * https://leetcode.com/problems/beautiful-arrangement-ii/
 * --
 * Zhiyong 2021-04-12
 */

/* Approach: generate deltas like this:
 *
 * n-1, n-1, n-2, ..., (n-k+1), 1, 1, ..., 1
 *
 * to get such deltas, arrange the numbers like this:
 *
 * 1, n, 2, n-1, 3, n-2, ..., m, m+1, m+2, ...
 *
 * or
 *
 * 1, n, 2, n-1, 3, n-2, ..., m, m-1, m-2, ...
 */
class Solution {
public:
    vector<int> constructArray(int n, int k) {
        // number of 1s = n - k
        // number of others = (n - 1) - (n - k) = k - 1
        int maxOthers = n - 1;
        int minOthers = n - k + 1;
        
        vector<int> res;
        res.resize(n);

        res[0] = 1;
        int idx = 1;
        bool asc = true;
        
        // generate non-one deltas
        // examples when n=8:
        // 1 8 2 7 3 6 ...    
        for (int d = maxOthers; d >= minOthers; --d) {
            if (asc)
                res[idx] = d + res[idx - 1];
            else
                res[idx] = res[idx - 1] - d;
            asc = !asc;
            ++idx;
        }
        
        // generate all-one deltas
        int d = asc ? 1 : -1;
        for (; idx < n; ++idx)
            res[idx] = res[idx - 1] + d;
        
        return res;
    }
};
