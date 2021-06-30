# 307. Range Sum Query - Mutable

[https://leetcode.com/problems/range-sum-query-mutable/submissions/](https://leetcode.com/problems/range-sum-query-mutable/submissions/)

## Evolvement of Segment Tree

- [Verbose, Top-Bottom, Recursion](./Solution_segment_tree_verbose.cpp)
- [Non-verbose, Bottom-Top, Non-recursion](./Solution_segment_tree_non_recursion.cpp)
- [Moved tree root from #0 to #1](./Solution_segment_tree_concise.cpp)
- [Do not pad zeros to make the tree full](./Solution_segment_tree_elegant.cpp)

## How does non-padding tree work if n is not a power of two?

### Conclusion

- For a full tree, number of nodes = `2^ceil(log2(n)) - 1` < `4\*n - 1`
- For a non-padding tree, number of nodes = `2\*n - 1` no matter what.

### Demonstration

Suppose input array: 1 2 3 4 5, n = 5

A. padding zeros to make the length of input array a power of two

    tree size = 2^ceil(log2(n)) - 1 = 15
    implicit tree: 15 10 5 3 7 5 0 1 2 3 5 0 0 0


tree:

          15
        /    \
      10       5
      / \     / \
     3   7   5   0
    /\  /\  /\  /\
    1 2 3 4 5 0 0 0

B. no padding

    tree size = 2 * n - 1 = 9
    implicit tree: -  15 10 5  9  1  2  3  4  5  (tree[0] is unused)
    idx:           0  1  2  3  4  5  6  7  8  9


tree:

            15
          /    \
        10      5
       /  \    / \
      9    1  2   3
     / \
    4   5

