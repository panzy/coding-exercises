/*
166. Fraction to Recurring Decimal
https://leetcode.com/problems/fraction-to-recurring-decimal/

--
Zhiyong
2021-04-08
*/
#include <string>
#include <sstream>
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

// Approach: simulate.
class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        stringstream ss; // the fraction part, not including the dot.
        int len = 0; // length of the string stream
        unordered_map<int, int> remain; // remember each remainder and the corresponding length of the stream.

        // Calculate the whole part of the answer and then start the division from the fraction part.
        // This is a key step to control the complexity.
        // Note: convert int to long long to handle stupid overflow, e.g., -1 * -2147483648 
        string whole = ((long long)numerator * denominator < 0 ? "-" : "") +
            to_string(abs((long long)numerator / denominator));
        const long long D = abs(denominator);
        long long R = abs(numerator) % D * 10; // the next remainder
        remain.insert({ R, 0 });

        while (R > 0) {
            remain[R] = len;

            if (R >= D) {
                ss << R / D;
                len += 1;
                R %= D;
                R *= 10;
            }
            else {
                ss << '0';
                len += 1;
                R *= 10;
            }

            if (remain.count(R)) {
                // repeating detected

                // the repeating part
                string t = ss.str();
                t = t.substr(remain[R]);

                string s = ss.str();
                if (s.length() >= t.length()) {
                    return whole + '.' + s.substr(0, s.length() - t.length()) +
                        '(' + s.substr(s.length() - t.length()) + ')';
                }
                else {
                    return whole + '.' + s + '(' + t + ')';
                }
            }
        }

        if (len > 0)
            return whole + '.' + ss.str();
        else
            return whole;
    }
};

// Approach #0: find the smallest integer n so that answer = n / (10^m). E.g.,
//      for 400/333 = 1.(201),
//      we need n = 1201
// XXX This approach works only when all the digits in the answer can be covered by an int64.
// So, for example, it can't handle 1/2324.
class Solution0 {

    // Make the decimal from an integer and the number of digits that should be in the fraction part.
    // val - the integer part before apply |f|.
    // f - need to divide by 10^f
    // repeatLen - the number of the right most digits from the fration part that are repeating.
    string makeDecimal(long long val, int f, int repeatLen) {
        string s = to_string(val);

        if (f == 0) {
            return s;
        }
        else if (s.length() <= f) {
            s = "0." + string(f - s.length(), '0') + s;
            return s.substr(0, s.length() - repeatLen) + '(' + s.substr(s.length() - repeatLen) + ')';
        } 
        else {
            return s.substr(0, s.length() - f) + ".(" + s.substr(s.length() - f, repeatLen) + ")";
        }
    }
public:
    string fractionToDecimal(int numerator, int denominator) {
        
        // put aside negative sign
        bool neg = numerator * denominator < 0;
        numerator = abs(numerator);
        denominator = abs(denominator);
        
        // define bare = numerator with tailing zeros being removed
        int bare = numerator / pow(10, (int)log10(numerator));
        
        // keep a record of the mod results.
        // if a result occurs again, then the fraction part repeats.
        unordered_map<int, int> seen;
        seen.insert({ bare, 0 });
        
        // length of the repeating part, if any
        int repeatLen = 0;

        // numX = bare * 10^i
        long long numX = bare;

        // find the smallest numX that either:
        // is a multiplication of denominator, or
        // mod denominator and results into a seen remaining.
        while (numX < denominator) {
            numX *= 10;
            ++repeatLen;
        }
        while (true) {
            int r = numX % denominator;
            if (r == 0) {
                // no repeat.
                return (neg ? "-" : "") + makeDecimal(numX / denominator, (int)log10(numX / numerator), 0);
            } else if (seen.count(r)) {
                // repeat detected
                int rp = repeatLen - seen[r];
                return (neg ? "-" : "") + makeDecimal(
                    (long long)(numX / bare) * numerator / denominator,
                    repeatLen,
                    rp);
            }
            
            seen.insert({ r, repeatLen });
            numX *= 10;
            ++repeatLen;
        }
    }
};

int main() {
    int n = -4, d = 333;

    n = 1;
    d = 2324;
    cout << to_string((float)n / d) << "\t" << Solution().fractionToDecimal(n, d) << endl;

    n = 1;
    d = 3;
    cout << to_string((float)n / d) << "\t" << Solution().fractionToDecimal(n, d) << endl;

    n = 2;
    d = 3;
    cout << to_string((float)n / d) << "\t" << Solution().fractionToDecimal(n, d) << endl;

    n = 1;
    d = 6;
    cout << to_string((float)n / d) << "\t" << Solution().fractionToDecimal(n, d) << endl;

    n = -4;
    d = 333;
    for (int i = 0; i < 9; ++i) {
        cout << n << '/' << d << ' ' << to_string((float)n / d) << "\t" << Solution().fractionToDecimal(n, d) << endl;
        n *= 10;
    }
}
