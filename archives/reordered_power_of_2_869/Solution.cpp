/* 869. Reordered Power of 2
 * https://leetcode.com/problems/reordered-power-of-2/
 * --
 * Zhiyong
 * 2021-03-21
 */

/*
Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
 

Note:

1 <= N <= 10^9
*/
class Solution {
public:
    void getDigitFreq(int N, int digits[10]) {
        while (N > 0) {
            int d = N % 10;
            ++digits[d];
            N /= 10;
        }
    }
    
    bool sameDigits(int p, int digits[10]) {
        int pd[10]{};
        getDigitFreq(p, pd);
        return mismatch(pd, pd + 10, digits).first == pd + 10;
    }
    
    bool reorderedPowerOf2(int N) {
        // N <= 10^9 < 2^30
        
        int digits[10]{}; // digit frequency of N
        getDigitFreq(N, digits);

        for (int i = 0; i < 31; ++i) {
            int p = (int)pow(2, i);
            if (sameDigits(p, digits)) {
                return true;
            }   
        }
        
        return false;
    }
};
