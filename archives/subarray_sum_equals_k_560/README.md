# 560. Subarray Sum Equals K

Medium

https://leetcode.com/problems/subarray-sum-equals-k/

## Notes

I think the sliding window technique doesn't apply to this problem
because unlike other problems concerning occurrences of symbols,
such as [992. Subarrays with K Different Integers](../subarrays_with_k_different_integers),
[395. Longest Substring with At Least K Repeating Characters](../longest_substring_with_at_least_k_repeating_characters),
and [3. Longest Substring Without Repeating Characters](../longest_substring_without_repeating_characters),
this problem deals with sum of number values, and because a number
can be negative, the sum becomes irrelevant with the window size.

So the comparison of the current "value" of the window with
the desired value can't tell you how to adjust the window.
