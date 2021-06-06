//
// 128. Longest Consecutive Sequence
// https://leetcode.com/problems/longest-consecutive-sequence/
// 
// 67 /  test cases passed.	Status: Accepted
// Runtime: 115 ms
// Memory Usage: NaN MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      06/06/2021, 11:49:40
// LeetCode submit time: 4 years, 9 months ago
// Submission detail page: https://leetcode.com/submissions/detail/72917954//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function(nums) {

    const allNums = new Set();
    nums.forEach(i => allNums.add(i));
    
    const visited = new Set();

    var L = 0;
    nums.forEach(curr => {
        if (!visited.has(curr)) {

            var rightOffset = 1;
            while (true) {
                var nextNum = curr + rightOffset;
                if (allNums.has(nextNum)) {
                    visited.add(nextNum);
                    ++rightOffset;
                } else {
                    break;
                }
            }

            var leftOffset = 1;
            while (true) {
                var prev = curr - leftOffset;
                if (allNums.has(prev)) {
                    visited.add(prev);
                    ++leftOffset;
                } else {
                    break;
                }
            }

            var l = leftOffset + rightOffset - 1;
            //console.log(curr, rightOffset, leftOffset, l);
            if (l > L)
                L = l;
        }
    });
    return L;
}