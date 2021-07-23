//
// 838. Push Dominoes
// https://leetcode.com/problems/push-dominoes/
// 
// 43 / 43 test cases passed.	Status: Accepted
// Runtime: 32 ms
// Memory Usage: 12.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      22/07/2021, 20:44:41
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/526804303/?from=explore&item_id=3821/
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
public:
    string pushDominoes(string d) {
        int n = d.length();
        int prevR = -1; // position of previous 'R' that is still in scope
        
        for (int i = 0; i < n; ++i) {
            if (d[i] == 'R') {
                prevR = i;
            } else if (d[i] == 'L') {
                if (prevR == -1) {
                    // push left until an already fallen dominoe is reached
                    for (int j = i - 1; j >= 0 && d[j] != 'R' && d[j] != 'L'; --j) {
                        d[j] = 'L';
                    }
                } else {
                    // push left until the middle dominoe is reached
                    for (int m = 0; m < (i - prevR - 1) / 2; ++m) {
                        d[i - 1 - m] = 'L';
                    }
                    // if there're odd number of dominoe between the L and R
                    // then the middle one is restored.
                    if ((i - prevR - 1) % 2 == 1) {
                        d[i - (i - prevR) / 2] = '.';
                    }
                    prevR = -1; // the previous R will be out of scope
                }
            } else {
                if (prevR != -1)
                    d[i] = 'R';
            }
        }
        
        return d;
    }
};