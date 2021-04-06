/* 1551. Minimum Operations to Make Array Equal
 * https://leetcode.com/problems/minimum-operations-to-make-array-equal/
 * --
 * Zhiyong 2021-04-06
 */

class Solution {
public:
    int minOperations(int n) {
        if (n % 2 == 0) // sum = 1 + 3 + ... + (n-1)
            return n * n / 4;
        else // sum = 0 + 2 + ... + (n-1)
            return (n - 1) * (n + 1) / 4;
    }
};
