# 1646. Get Maximum in Generated Array

https://leetcode.com/problems/get-maximum-in-generated-array/

## Notes

An easy problem, given the constraint that `0 <= n <= 100`.

Tried to find a solution cleverer than simply simulating. Here are some
observations.

First, an example:

    // n = 15
    0,1,1,2,1,3,2,3,1,4,3,5,2,5,3,4

1. The array is not monotonically increasing.
2. `nums[2i+1] > nums[2i]`
3. For two number `i > j`, `nums[2i+1]` may or may not be greater than `nums[2j+1]`
4. If you view the array as a binary tree:
   - Each left child equals to its parent. In particular, all left children
     being the first of its layer are `1`.
   - Each right child equals to the sum of its parent and uncle. In particular,
    all right children being the last of its layer form a sequence `1, 2, 3, ...`
   - For each layer, the greatest nodes are one of the right children.
   - The maximum element of the whole array is hidden in the bottom layer of the tree.
   - The greatest nodes from each layer, form a sequence like this:
    `1+1, 2+1, 3+2, 5+3, 8+5, ...`
   - So, if the tree is full, the maximum value can be found at the cost of
     O(logN) time and O(1) space.
   - But if it's not full, I have no idea how to determine the maximum.
