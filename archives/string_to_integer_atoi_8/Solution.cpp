/* 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 * --
 * Zhiyong 2021-04-08
 */

class Solution {
public:
    int myAtoi(string s) {
        int sign = 0; // 0 means not determined
        long long num = 0;

        for (char c : s) {
            if (c == ' ') {
                // ignore leading whitespace
                if (sign == 0) continue;
                // ignore the rest
                else break;
            } else if (c == '-') {
                if (sign != 0) break;
                sign = -1;
            } else if (c == '+') {
                if (sign != 0) break;
                sign = 1;
            } else if ('0' <= c && c <= '9') {
                // if no sign, make it positive
                if (sign == 0) sign = 1;

                num = num * 10 + (c - '0');

                // clamp to -2^31
                if (sign == -1 && num > (1L << 31))
                    return -(1L << 31);

                // clamp to 2^31 - 1
                if (sign == 1 && num > (1L << 31) - 1)
                    return (1L << 31) - 1;
            } else {
                break;
            }
        }

        return sign * num;
    }
};
