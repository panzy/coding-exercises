//
// 307. Range Sum Query - Mutable
// https://leetcode.com/problems/range-sum-query-mutable/
// 
// 15 / 15 test cases passed.	Status: Accepted
// Runtime: 384 ms
// Memory Usage: 150.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      20/06/2021, 18:31:07
// LeetCode submit time: 0 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/510826375//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
// Update 1: By moving the tree root from [0] to [1], the arithmetic becomes simpler.
//
// Update 2: By allowing n to be not a power of 2, the tree is not always full,
// and maybe surprisingly, the original numbers are not guaranteed to sit at
// the bottom layer. See the README file for a demonstation.
//
// Fortunately, the following properties still hold:
// (1) the first input number is tree[n]
// (2) tree[i] = tree[2 * i] + tree[2 * i + 1]
// Thus, the reset of the algorithm does not need to update.
class NumArray {
    // number of leaf nodes.
    // it worths mentioning that when the bottom layer is not full, some leaves
    // move to the last 2nd layer.
    size_t n;
    // an implicit BST, each node value is the sum of its two children.
    // tree[1] = root, tree[0] is not used.
    vector<int> tree;

public:
    NumArray(const vector<int>& nums)
        : n(nums.size()),
        tree(n * 2, 0)
    {
        copy(begin(nums), end(nums), end(tree) - n);

        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[2 * i] + tree[2 * i + 1];
    }
    
    void update(int index, int val) {
        // locate the leaf node: tree[nodePos] <=> nums[index]
        int nodePos = n + index;
        int delta = val - tree[nodePos];

        // update the leaf node and the parents on the path to the root
        while (nodePos > 0) {
            tree[nodePos] += delta;
            nodePos = nodePos / 2;
        }
    }
    
    int sumRange(int left, int right) {
        // Approach: Assume the tree is full. Becase each layer has the same
        // sum, and higher layer has shorter length, our motivation is move
        // upward.
        // When the tree is not full, amazingly, the algorithm is still
        // correct.

        // map array indices to tree node indices
        left += n;
        right += n;

        int sum = 0;
        
        while (left <= right) {
            // trim the current layer to align with two's power
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            if (right % 2 == 0) {
                sum += tree[right--];
            }

            // move up
            left /= 2;
            right /= 2;
        }

        return sum;
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
