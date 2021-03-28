/* 423. Reconstruct Original Digits from English
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * --
 * Zhiyong
 * 2021-03-28
 */

/* Optimization on the previous solution. */

class Solution {
    void countFreq(string s, int freq[26]) {
        for (char c : s)
            ++freq[c - 'a'];
    }
    
    void subtractFreq(int a[26], int b[26]) {
        for (int i = 0; i < 26; ++i)
            a[i] -= b[i];
    }
public:
    string originalDigits(string s) {
        int freq[26]{};
        countFreq(s, freq);

        // digit frequency for each En word
        int dfreq[10][26]{};
        int idx = 0;
        // order matters!
        countFreq("zero", dfreq[idx++]);
        countFreq("six", dfreq[idx++]);
        countFreq("four", dfreq[idx++]);
        countFreq("five", dfreq[idx++]);
        countFreq("two", dfreq[idx++]);
        countFreq("seven", dfreq[idx++]);
        countFreq("three", dfreq[idx++]);
        countFreq("eight", dfreq[idx++]);
        countFreq("nine", dfreq[idx++]);
        countFreq("one", dfreq[idx++]);
        
        // the digit order matches dfreq
        int dtable[10]{0, 6, 4, 5, 2, 7, 3, 8, 9, 1};
        
        // the single char that can identify a word if we identify them in this order.
        char duniq[10]{'z', 'x', 'u', 'f', 'w', 'v', 'r', 't', 'i', 'o'};
        
        // count digits
        int digits[10]{};
        int cnt = 0;
        for (int i = 0; i < 10; ++i) {
            while (freq[duniq[i] - 'a'] > 0) {
                subtractFreq(freq, dfreq[i]);
                ++digits[dtable[i]];
                ++cnt;
            }
        }
        
        // convert digit counts to string
        char res[cnt + 1];
        idx = 0;
        for (int i = 0; i < 10; ++i) { // digit i
            for (int r = 0; r < digits[i]; ++r) { // repeat times
                res[idx++] = '0' + i;
            }
        }
        res[idx] = '\0';
        return res;
    }
};
