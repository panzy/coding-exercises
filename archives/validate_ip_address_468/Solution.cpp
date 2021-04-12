/* 468. Validate IP Address
 * https://leetcode.com/problems/validate-ip-address/
 *
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP
 * is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * --
 * Zhiyong 2021-04-08
 */

class Solution {
public:
    string validIPAddress(string IP) {
        string none = "Neither";
        int n = IP.length();

        if (IP.find('.') != -1) {
            // IPv4?
            int numIdx = 0;
            int num = -1; // -1 means empty

            for (int i = 0; i <= n; ++i) {
                // imagine there is a tailing '.'
                char c = i < n ? IP[i] : '.';

                if (numIdx == 4) // too many numbers
                    return none;
                if (num > 255)
                    return none;

                if (c == '.') {
                    if (num == -1) // empty number
                        return none;
                    ++numIdx ;
                    num = -1;
                } else if (c == '0') {
                    if (num == -1)
                        num = 0;
                    else if (num == 0) // leading zero
                        return none;
                    else
                        num *= 10;
                } else if ('1' <= c && c <= '9') {
                    if (num == -1)
                        num = 0;
                    else if (num == 0) // leading zero
                        return none;
                    num = num * 10 + (c - '0');
                } else { // invalid chars
                    return none;
                }
            }

            return numIdx == 4 ? "IPv4" : none;
        } else {
            // IPv6?
            int numIdx = 0;
            int numDigits= 0;

            for (int i = 0; i <= n; ++i) {
                // imagine there is a tailing ':'
                char c = i < n ? IP[i] : ':';

                if (numIdx == 8) // too many numbers
                    return none;
                if (numDigits > 4) // too many digits
                    return none;
                if (c == ':') {
                    ++numIdx;
                    if (numDigits == 0) // empty number
                        return none;
                    numDigits = 0;
                } else if ('0' <= c && c <= '9' || 'a' <= c && c <= 'f' || 'A' <= c && c <= 'F') {
                    ++numDigits;
                } else {
                    return none;
                }
            }

            return numIdx == 8 ? "IPv6" : none;
        }

        return none;
    }
};
