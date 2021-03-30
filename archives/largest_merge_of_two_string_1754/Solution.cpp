/* 1754. Largest Merge Of Two Strings
 * https://leetcode.com/problems/largest-merge-of-two-strings/
 *
 * Note that the chars of the two strings are not sorted.
 * So it's not the same problem as the merge stage of a merge sort.
 * --
 * Zhiyong 2021-03-29
 */

/*
You are given two strings word1 and word2. You want to construct a string merge
in the following way: while either word1 or word2 are non-empty, choose one of
the following options:

If word1 is non-empty, append the first character in word1 to merge and delete
it from word1.

For example, if word1 = "abc" and merge = "dv", then after choosing this
operation, word1 = "bc" and merge = "dva".

If word2 is non-empty, append the first character in word2 to merge and delete
it from word2.

For example, if word2 = "abc" and merge = "", then after choosing this
operation, word2 = "bc" and merge = "a".

Return the lexicographically largest merge you can construct.

Constraints:

1 <= word1.length, word2.length <= 3000
word1 and word2 consist only of lowercase English letters.
*/

class Solution {
public:
    string largestMerge(string word1, string word2) {
        stringstream ss;
        int i = 0, j = 0;
        int m = word1.length(), n = word2.length();
        while (i < m || j < n) {
            // samples:
            // abc, abd -> abdabc
            // bac, bad -> bbadac
            // ba, bad -> badba
            // yxa, yx -> yyxxa
            
            if (i < m && j < n && word1[i] == word2[j]) {
                // the two heads are equal? find the first mismatch.
                int i2 = i + 1, j2 = j + 1;
                while (i2 < m && j2 < n && word1[i2] == word2[j2]) {
                    ++i2;
                    ++j2;
                }
                
                // consume the head followed by the larger mismatched letter
                if (i2 < m && (j2 == n || word1[i2] > word2[j2])) {
                    ss << word1[i++];
                } else {
                    ss << word2[j++];
                }
            } else if (i < m && (j == n || word1[i] > word2[j])) {
                ss << word1[i++];
            } else {
                ss << word2[j++];
            }
        }
        return ss.str();
    }
};
