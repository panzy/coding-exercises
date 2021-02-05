# New Year Chaos

HackerRank Medium

https://www.hackerrank.com/challenges/new-year-chaos/problem

## Notes

Since each person can only swap with the one immediately before
them, this is a bubble sort.

Since `n` can be as large as `10^5`, vanilla bubble sort,
which is `O(N^2)` won't be acceptable.

I managed to use hash map to accelerate bubble sort and get
the solution accepted. However, that approach isn't clever.

Since each person can swap at most twice, this is a
scope-restricted bubble sort -- it aborts when a number
is too far from its desired position. Exploiting the fact
that the scope is at most two, we can eliminate the inner
loop in a vanilla bubble sort and finish the job in one pass.