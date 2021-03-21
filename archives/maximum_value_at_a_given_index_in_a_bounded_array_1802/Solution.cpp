/* 1802. Maximum Value at a Given Index in a Bounded Array
 * https://leetcode.com/contest/weekly-contest-233/problems/maximum-value-at-a-given-index-in-a-bounded-array/
 * --
 * Zhiyong
 * 2021-03-21
 */

/*
You are given three positive integers n, index and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

nums.length == n
nums[i] is a positive integer where 0 <= i < n.
abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
The sum of all the elements of nums does not exceed maxSum.
nums[index] is maximized.
Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.

 

Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: The arrays [1,1,2,1] and [1,2,2,1] satisfy all the conditions. There are no other valid arrays with a larger value at the given index.
Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3
 

Constraints:

1 <= n <= maxSum <= 109
0 <= index < n
*/

#define ifdbg if(false)
using ULL = unsigned long long;

class Solution {
public:

    // Given that arr[index] = maxVal, calculate the sum of the all elements.
    ULL sum(int n, int index, ULL maxVal) {
        ULL sum = 0;

        // add [0, index]
        if (maxVal > index) {
            // it's a right triangle
            ULL minVal = maxVal - index;
            sum += (minVal + maxVal) * (1 + index) / 2;
            ifdbg cout << "sum[0,index]=" << sum << endl;
        } else {
            // it's a right triangle following some ones
            int hillStart = index - maxVal + 1;
            sum += hillStart + (1 + maxVal) * (1 + index - hillStart) / 2;
            ifdbg cout << "hillStart=" << hillStart << ", sum=" << sum << endl;
        }

        // add [index, n)
        if (maxVal > (n - 1 - index)) {
            // it's a right triangle
            ULL minVal = maxVal - (n - 1 - index);
            sum += (minVal + maxVal) * (n - index) / 2;
            ifdbg cout << "sum[index,n)=" << sum << " (minVal=" << minVal << ")" << endl;
        } else {
            // it's a right triangle followed by some ones
            int hillEnd = index + maxVal;
            sum += (maxVal + 1) * (hillEnd - index) / 2 + (n - hillEnd);
            ifdbg cout << "hillEnd=" << hillEnd << ", sum=" << sum << endl;
        }

        // have add [index] twice
        sum -= maxVal;

        ifdbg cout << "sum " << n << ' ' << index << ' ' << maxVal << " = " << sum << endl;
        return sum;
    }

    int maxValue(int n, int index, int maxSum) {
        // binary search the answer

        int lo = 1, hi = maxSum + 1; // don't forget maxSum itself can be the answer!

        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (sum(n, index, mi) <= maxSum) {
                if (lo < mi)
                    lo = mi;
                else
                    break;
            } else {
                hi = mi;
            }
        }

        return lo;
    }
};
