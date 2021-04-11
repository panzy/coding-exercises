/* 65. Valid Number
 * https://leetcode.com/problems/valid-number/
 * --
 * Zhiyong 2021-04-11
 */

class Solution {
    bool allDigits(const string& s, int begin, int end) {
        for (int i = begin; i < end; ++i) {
            if (!('0' <= s[i] && s[i] <= '9')) {
                return false;
            }
        }
        return true;
    }
    
    bool isInteger(const string& s, int begin, int end) {
        if (begin >= end) return false;
        if (s[begin] == '-' || s[begin] == '+')
            return begin + 1 < end && allDigits(s, begin + 1, end);
        else
            return allDigits(s, begin, end);
    }
    
    bool isDecimalWithoutSign(const string& s, int begin, int end) {
        if (begin >= end) return false;
        int dotPos = s.find('.', begin);
        if (dotPos == -1 || dotPos >= end) return false;
        if (dotPos == begin)
            return dotPos + 1 < end && allDigits(s, dotPos + 1, end);
        else
            return allDigits(s, begin, dotPos) && (dotPos + 1 == end || allDigits(s, dotPos + 1, end));
    }

    bool isDecimal(const string& s, int begin, int end) {
        if (begin >= end) return false;
        if (s[begin] == '-' || s[begin] == '+')
            return isDecimalWithoutSign(s, begin + 1, end);
        else
            return isDecimalWithoutSign(s, begin, end);
    }
public:
    bool isNumber(string s) {
        int p = s.find('e');
        if (p == -1) p = s.find('E');
        
        if (p == -1) {
            return isDecimal(s, 0, s.length()) || isInteger(s, 0, s.length());
        } else {
            return (isDecimal(s, 0, p) || isInteger(s, 0, p)) && isInteger(s, p + 1, s.length());
        }
    }
};
