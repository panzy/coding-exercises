//
// 307. Range Sum Query - Mutable
// https://leetcode.com/problems/range-sum-query-mutable/
// 
// 15 / 15 test cases passed.	Status: Accepted
// Runtime: 364 ms
// Memory Usage: 151.2 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      20/06/2021, 17:25:28
// LeetCode submit time: 36 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/510794380//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class NumArray {
    size_t n; // number of leaf nodes, obtained by aligning nums.size() to a power of two
    vector<int> tree; // an implicit BST, each node value is the sum of its two children.

    int getParentNodePos(int nodePos) {
        return (nodePos + 1) / 2 - 1;
    }

public:
    NumArray(const vector<int>& nums)
        : n(pow(2, ceil(log2(nums.size())))),
        tree(n * 2 - 1, 0)
    {
        copy(begin(nums), end(nums), end(tree) - n);

        for (int i = n - 2; i >= 0; --i)
            tree[i] = tree[2 * i + 1] + tree[2 * i + 2];
    }
    
    void update(int index, int val) {
        // locate the leaf node: tree[nodePos] <=> nums[index]
        int nodePos = (n - 1) + index;
        int delta = val - tree[nodePos];

        // update the leaf node and the parents on the path to the root
        while (true) {
            tree[nodePos] += delta;
            if (nodePos == 0)
                break;
            nodePos = getParentNodePos(nodePos);
        }
    }
    
    int sumRange(int left, int right) {
        // approach: becase each layer has the same sum, and higher layer has shorter length,
        // our motivation is move upwards.

        // map array indices to tree node indices
        left += n - 1;
        right += n - 1;

        int sum = 0;
        
        while (left <= right) {
            // trim the current layer to align with two's power
            if (left % 2 == 0) {
                sum += tree[left++];
            }
            if (right % 2 == 1) {
                sum += tree[right--];
            }

            // move up
            left = (left - 1) / 2;
            right = right / 2 - 1;
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