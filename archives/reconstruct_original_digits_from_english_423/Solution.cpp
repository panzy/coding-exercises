/* 423. Reconstruct Original Digits from English
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * --
 * Zhiyong
 * 2021-03-28
 */

/*
Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
*/

class Solution {
    void countFreq(string s, int freq[26]) {
        for (char c : s)
            ++freq[c - 'a'];
    }
    
    bool subtractFreq(int a[26], int b[26]) {
        for (int i = 0; i < 26; ++i) {
            if (a[i] < b[i])
                return false;
        }
        
        for (int i = 0; i < 26; ++i)
            a[i] -= b[i];
        
        return true;
    }
public:
    string originalDigits(string s) {
        int freq[26]{};
        countFreq(s, freq);

        int dfreq[10][26]{};
        int idx = 0;
        // order matters!
        countFreq("zero", dfreq[idx++]);
        countFreq("six", dfreq[idx++]);
        countFreq("seven", dfreq[idx++]);
        countFreq("eight", dfreq[idx++]);
        countFreq("three", dfreq[idx++]);
        countFreq("two", dfreq[idx++]);
        countFreq("four", dfreq[idx++]);
        countFreq("five", dfreq[idx++]);
        countFreq("nine", dfreq[idx++]);
        countFreq("one", dfreq[idx++]);
        
        // the digit order matches dfreq
        int dtable[]{0, 6, 7, 8, 3, 2, 4, 5, 9, 1};
        
        int digits[10]{};
        int cnt = 0;
        for (int i = 0; i < 10; ++i) {
            // XXX subtractFreq() performs a dry run to check whether
            // freq contains dfreq[i] before actually do the subtracting.
            // There is optimization space for this operation because,
            // in fact, it's enough to check only one char for each word.
            while (subtractFreq(freq, dfreq[i])) {
                ++digits[dtable[i]];
                ++cnt;
            }
        }

        // XXX appending char to a char array is slightly faster than appending
        // to a string.
        string res;
        res.reserve(cnt);
        for (int i = 0; i < 10; ++i) {
            for (int r = 0; r < digits[i]; ++r) { // repeat times
                res += '0' + i;
            }
        }
        return res;
    }
};
