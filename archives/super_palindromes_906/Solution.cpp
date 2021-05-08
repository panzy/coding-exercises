/* 906. Super Palindromes
 * https://leetcode.com/problems/super-palindromes/
 *
 * Requirements to solve this problem:
 * - how to enumerate palindromes
 * - knowning that 
 * - knowning that the double value returned by std::pow() is not precise for determining whether the result would have been a palindrome.
            
 * Notes on int overflow:
 *
 * - 1e18 is within uint64's boundary: 2^64 – 1 = 18446744073709551615 (a 20 digit number)
 * - (kk * kk) would exceed uint64's upper boundary, for example, when k=98765 and kk = 9876556789.
 * - in case of overflow, say kk = 9876556789,
 *      -- `kk * kk` results in a warpped around value, 5312653637794232441
 *      -- `(uint64_t)pow(kk, 2)` results in
 *          --- 9223372036854775808 on MSVC, correct but not accurate        
 *          --- 0 on g++ 7.5.0
 *          --- runtime error: 9.75464e+19 is outside the range of representable values of type 'unsigned long long' on LeetCode's compiler
 * - `pow(kk, 2)` returns a double, which has precision loss, which is not reliable for determining whether the value is a palindrome!
 *           
 * --
 * Zhiyong 2021-05-08
 */

class Solution {
    uint64_t reverse(uint64_t n) {
        uint64_t t = 0;
        while (n > 0) {
            t = t * 10 + n % 10;
            n /= 10;
        }
        return t;
    }
    
    bool isPalindrome(uint64_t q) {
        return q == reverse(q);
    }
    
    uint64_t makeEvenPalindrome(uint64_t leftHalf) {
        uint64_t rightHalf = 0;
        uint64_t shifts = 1;
        for (uint64_t t = leftHalf; t > 0; t /= 10) {
            rightHalf = rightHalf * 10 + t % 10;
            shifts *= 10;
        }
        return leftHalf * shifts + rightHalf;
    }
    
    uint64_t makeOddPalindrome(uint64_t leftHalf) {
        uint64_t rightHalf = 0;
        uint64_t shifts = 1;
        for (uint64_t t = leftHalf / 10; t > 0; t /= 10) {
            rightHalf = rightHalf * 10 + t % 10;
            shifts *= 10;
        }
        return leftHalf * shifts + rightHalf;
    }
public:
    int superpalindromesInRange(string left, string right) {
        uint64_t a = strtoull(left.c_str(), nullptr, 10);
        uint64_t b = strtoull(right.c_str(), nullptr, 10);
        
        // Use an int k to enumerate palindromes:
        // k=123 -> 123321 and 12321
        
        // To determine the (estimated) maximal k:
        //
        // (kk')^2 <= 1e18
        // kk' <= 1e9
        // k < 1e5
        uint64_t MAXK = 1e5;
        
        int ans = 0;

        for (uint64_t k = 1; k < MAXK; ++k) {
            uint64_t kk = makeEvenPalindrome(k);
            double kk2 = pow(kk, 2);
            if (kk2 > b)
                break;
            if (a <= kk2 && isPalindrome(kk * kk)) {
                ++ans;
            }
        }
            
        for (uint64_t k = 1; k < MAXK; ++k) {
            uint64_t kk = makeOddPalindrome(k);
            double kk2 = pow(kk, 2);
            if (kk2 > b)
                break;
            if (a <= kk2 && isPalindrome(kk * kk)) {
                ++ans;
            }
        }
        
        return ans;
    }
};
