/* 1191. K-Concatenation Maximum Sum
 * https://leetcode.com/problems/k-concatenation-maximum-sum/
 * --
 * Zhiyong 2021-04-10
 */

class Solution {
public:
    int kConcatenationMaxSum(vector<int>& arr, int k) {
        // F: full sum of the arr
        // L: maximal sum of subarray [0:i]
        // R: maximal sum of subarray [i:n-1]
        // M: maximal sum of subarray [i:j]
        // L0: minimal sum of subarray [0:i]
        long long F = 0, L = 0, L0 = 0, M = 0, R = 0;
        
        for (int e : arr) {
            F += e;
            if (L0 > F) L0 = F;
            if (L < F) L = F;
            if (M < F - L0) M = F - L0;
        }
        
        F = 0;
        for (int i = arr.size() - 1; i >= 0; --i) {
            F += arr[i];
            if (R < F) R = F;
        }
        
        long long ans = M;
        if (F > 0)
            ans = max(ans, F * k);
        if (k < 2)
            ans = max(ans, max(R, L));
        else
            ans = max(ans, R + (F > 0 ? F * (k - 2) : 0) + L);
        return ans % (long long)(1e9 + 7);
    }
};
