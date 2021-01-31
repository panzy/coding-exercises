# 1745. Palindrome Partitioning IV

Hard

https://leetcode.com/problems/palindrome-partitioning-iv/

Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.​​​​​

A string is said to be palindrome if it the same string when reversed.

Constraints:

- 3 <= s.length <= 2000
- s consists only of lowercase English letters.

## Notes

Enumerating both delimiters for the 3 substrings is already an
`O(N^2)` operation, one can only afford a `O(1)` checking for
whether a substring is a palindrome.

A 2D query table can be built in `O(N^2)` time.
`table[i][j]` means whether range `[i,j]` (inclusive) is palindromic.

Although there exists an `O(N)` algorithm to this problem,
[[Python, 80ms] an O(n) algorithm](https://leetcode.com/problems/palindrome-partitioning-iv/discuss/1043402/Python-92ms-an-O(n)-algorithm),
which uses [Manacher's Algorithm](https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm),
for all the LeetCode palindrome problems I've seen so far, one
don't have to master that advanced algorithm.





