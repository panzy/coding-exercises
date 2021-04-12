/* 468. Validate IP Address
 * https://leetcode.com/problems/validate-ip-address/
 *
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP
 * is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * --
 * Zhiyong 2021-04-08
 */

class Solution {
    bool validV4Num(const string& s, int begin, int end) {
        if (begin >= end) return false; // empty
        if (end - begin > 3) return false; // too many digits
        if (s[begin] == '0' && begin + 1 < end) return false; // leading zero

        int num = 0;
        for (int i = begin; i < end; ++i) {
            char c = s[i];
            if ('0' <= c && c <= '9') {
                num = num * 10 + (c - '0');
            } else {
                return false;
            }
        }

        return 0 <= num && num < 256;
    }

    bool validV6Num(const string& s, int begin, int end) {
        if (begin >= end) return false; // empty
        if (end - begin > 4) return false; // too many digits

        for (int i = begin; i < end; ++i) {
            char c = s[i];
            if (!('0' <= c && c <= '9' || 'a' <= c && c <= 'f' || 'A' <= c && c <= 'F')) {
                return false;
            }
        }

        return true;
    }
public:
    string validIPAddress(string IP) {
        string none = "Neither";
        int n = IP.length();

        if (IP.find('.') != -1) {
            // IPv4?
            int nums = 0;

            for (int i = 0, begin = 0; i <= n; ++i) {
                // imagine there is a tailing '.'
                char c = i < n ? IP[i] : '.';

                if (c == '.') {
                    if (!validV4Num(IP, begin, i))
                        return none;
                    ++nums ;
                    begin = i + 1;
                }
            }

            return nums == 4 ? "IPv4" : none;
        } else {
            // IPv6?
            int nums = 0;

            for (int i = 0, begin = 0; i <= n; ++i) {
                // imagine there is a tailing ':'
                char c = i < n ? IP[i] : ':';

                if (c == ':') {
                    if (!validV6Num(IP, begin, i))
                        return none;
                    ++nums ;
                    begin = i + 1;
                }
            }

            return nums == 8 ? "IPv6" : none;
        }

        return none;
    }
};
