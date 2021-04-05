/* 775. Global and Local Inversions
 * https://leetcode.com/problems/global-and-local-inversions/
 * --
 * Zhiyong 2021-04-05
 */

class Solution {
public:
    bool isIdealPermutation(vector<int>& A) {
        int n = A.size();

        if (n < 2) return true;

        int minVal = A[n - 1];

        for (int i = n - 1; i > 0; --i) {
            if (A[i] < minVal)
                minVal = A[i];

            if (i - 2 >= 0 && A[i - 2] > minVal) // found a global inversion
                return false;
        }

        return true;
    }
};
