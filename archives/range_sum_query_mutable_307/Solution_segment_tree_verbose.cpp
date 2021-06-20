//
// 307. Range Sum Query - Mutable
// https://leetcode.com/problems/range-sum-query-mutable/
// 
// 15 / 15 test cases passed.	Status: Accepted
// Runtime: 636 ms
// Memory Usage: 151.1 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      20/06/2021, 15:38:48
// LeetCode submit time: 18 minutes ago
// Submission detail page: https://leetcode.com/submissions/detail/510763344//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/* This solution is verbose on purpose to demonstrate the implicit BST arithmetic. */
class NumArray {
    size_t n; // number of BST leaf nodes, obtained by aligning nums.size() to a power of two
    vector<int> tree; // an implicit BST, each node value is the sum of its two children.
public:
    NumArray(const vector<int>& nums)
        : n(pow(2, ceil(log2(nums.size())))),
        tree(n * 2 - 1, 0)
    {
        // copy leaves
        copy(begin(nums), end(nums), end(tree) - n);

        // compute parents bottom-up
        int layerHeight = 1;
        int layerSize = n / 2;
        int layerStartNodePos = tree.size() - n - layerSize;
        while (layerSize > 0) {
            for (int i = 0; i < layerSize; ++i) {
                tree[layerStartNodePos + i] =
                    tree[getLeftChildNodePos(layerStartNodePos + i)]
                    + tree[getRightChildNodePos(layerStartNodePos + i)];
            }
            ++layerHeight;
            layerSize /= 2;
            layerStartNodePos -= layerSize;
        }
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
        return sumRange(0, log2(n), left, right);
    }
    
private:
    int getLeftChildNodePos(int nodePos) {
        return 2 * nodePos + 1;
    }

    int getRightChildNodePos(int nodePos) {
        return getLeftChildNodePos(nodePos) + 1;
    }

    int getParentNodePos(int nodePos) {
        return (nodePos + 1) / 2 - 1;
    }

    int getSubtreeLeafCount(int height) {
        return pow(2, height);
    }

    int getPrevSiblingCount(int nodePos, int height) {
        int layerSize = pow(2, log2(n) - height);
        int layerStart = layerSize - 1;
        return nodePos - layerStart;
    }

    int getSubtreeLeft(int nodePos, int height) {
        return getPrevSiblingCount(nodePos, height) * pow(2, height);
    }

    int getSubtreeRight(int nodePos, int height) {
        return getSubtreeLeft(nodePos, height) + pow(2, height) - 1;
    }

    int sumRange(int nodePos, int height, int left, int right) {
        int subtreeLeft = getSubtreeLeft(nodePos, height);
        int subtreeRight = getSubtreeRight(nodePos, height);

        // assume the subtree is high enough
        assert(subtreeLeft <= left && right <= subtreeRight);

        if (subtreeLeft == left && subtreeRight == right) {
            return tree[nodePos];
        }
        else {
            int separator = getSubtreeLeft(getRightChildNodePos(nodePos), height - 1);
            if (right < separator) { // go left
                return sumRange(getLeftChildNodePos(nodePos), height - 1, left, right);
            } else if (left >= separator) { // go right
                return sumRange(getRightChildNodePos(nodePos), height - 1, left, right);
            }
            else { // split
                return sumRange(getLeftChildNodePos(nodePos), height - 1, left, separator - 1)
                    + sumRange(getRightChildNodePos(nodePos), height - 1, separator, right);
            }
        }
    }
};


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */