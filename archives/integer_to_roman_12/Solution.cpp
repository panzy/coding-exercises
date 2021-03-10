/* 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 * --
 * Zhiyong
 * 2021-03-10
 */
class Solution {
public:
    string intToRoman(int num) {
        const int n = 13;
        int vals[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        string syms[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        string ans;
        ans.reserve(20);
        
        for (int i = 0; i < n; ++i) {
            while (num >= vals[i]) {
                ans += syms[i];
                num -= vals[i];
            }
        }        
        
        return ans;
    }
};
