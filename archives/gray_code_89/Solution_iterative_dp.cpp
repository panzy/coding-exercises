//
// 89. Gray Code
// https://leetcode.com/problems/gray-code/
// 
// 16 / 16 test cases passed.	Status: Accepted
// Runtime: 8 ms
// Memory Usage: 11 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      01/07/2021, 23:12:14
// LeetCode submit time: 4 hours, 8 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/516055307//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/*
n=1:   0->1           

n=2:   00->01           
             \
         10<-11
          
n=3:   000->001-011-010   // this line is the result for n=2
                      \  
        100<-101-111-110  // this line is the reversed previous line and add 4 to each item. 
   
n=4:   0000->0001-0011-0010-0110-0111-0101-0100  // this line is the result for n=3
                                              \
        1000<-1001-1011-1010-1110-1111-1101-1100   // this line is the reversed previous line and add 8 to each item.
*/
class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> res(1 << n, 0);
        
        // when n = 1
        res[0] = 0;
        res[1] = 1;

        // build each layer of results based up on the previous one
        for (int i = 2; i <= n; ++i) {
            int half = 1 << (i - 1);
            for (int j = 0; j < half; ++j) {
                res[half + j] = half + res[half - 1 - j];
            }
        }
        
        return res;
    }
};