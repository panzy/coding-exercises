# 1673. Find the Most Competitive Subsequence

Medium

https://leetcode.com/problems/find-the-most-competitive-subsequence/

## Notes

Both solution #1 and #2 are building the target subsequence from left
to right, but their efficiency differs dramatically.

Solution #1 makes sure each step is the final decision.

Solution #2 doesn't try to get the global optimized element at each step.
Instead, it accepts whatever is coming, and drops previous elements
if that's feasible (the newer element is smaller) and affordable
(there are enough remaining elements from the original sequence).

Solution #1 suffers the most when the input array is an increasing one.