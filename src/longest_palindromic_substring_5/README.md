# 5. Longest Palindromic Substring
longest_palindromic_substring
Medium

https://leetcode.com/problems/longest-palindromic-substring/

## Description

Given a string s, return the longest palindromic substring in s.

## Notes

Every palindrome has a center (either one char or two same chars),
and for each center the longest palindrome is determined. So we can
enumerate palindromes in one-pass.