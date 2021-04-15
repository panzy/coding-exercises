/*
493. Reverse Pairs
https://leetcode.com/problems/reverse-pairs/

Tip: how do you count numeric values within a given range?
--
Zhiyong
2021-04-15
*/
#include <string>
#include <sstream>
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>
#include <functional>
#include <cassert>
using namespace std;

class BinaryTrieTree {
    struct Node {
        bool flag; // indicates whether this node has a value.
        int count; // count of values that can be reached from this node
        Node* children[2];

        Node() :
        flag(false),
        count(0),
        children{ nullptr, nullptr }
        {}

        // Calling children's destructor recursively, could lead to TLE.
        //~Node() {
        //    if (children[0])
        //        delete children[0];
        //    if (children[1])
        //        delete children[1];
        //}
    };

    Node root;
    int height;
    bool hasZero;

public: 
    BinaryTrieTree(int height_) : height(height_), hasZero(false) {
    }

    void add(unsigned int val) {
        // Navigate to the corresponding node. Create it if not exist.
        Node* p = &root;
        for (unsigned int mask = 1 << (height - 1); mask > 0; mask >>= 1) {
            int childIdx = val & mask ? 1 : 0;
            if (!p->children[childIdx])
                p->children[childIdx] = new Node();
            p->children[childIdx]->count++;
            p = p->children[childIdx];
        }

        p->flag = true;
        
        if (val == 0)
            hasZero = true;
    }

    // Count numbers that are <= or >= the given value.
    // @arg le - true = less than or equal to, false = greater than or equal to
    int count(unsigned long long val, bool le) {
        // Navigate the corresponding node.
        Node* p = &root;
        unsigned long long sum = 0; // value of the path
        int total = 0;
        for (unsigned long long mask = 1LL << (height - 1); mask > 0 && p != nullptr; mask >>= 1) {

            int childIdx = val & mask ? 1 : 0;

            // All numbers on the other branch are less
            if (le && childIdx == 1 && p->children[0])
                total += p->children[0]->count;
            else if (!le && childIdx == 0 && p->children[1])
                total += p->children[1]->count;
            
            p = p->children[childIdx];

            // If we're going to the non-zero branch, add the value of that bit to the sum.
            if (p != nullptr && childIdx == 1) {
                sum += mask;
            }

            // If val exists...
            if (p != nullptr && sum == val && p->flag) {
                total += p->count;
            }
        }

        return total;
    }

    int size() {
        int ans = 0;
        if (root.children[0]) ans += root.children[0]->count;
        if (root.children[1]) ans += root.children[1]->count;
        return ans;
    }
};

class Solution {
public:
    int reversePairs(const vector<int>& nums) {
        // This Trie implementation doens't support negative values,
        // so store the absolute values in a separate tree.
        BinaryTrieTree tree(32);
        BinaryTrieTree treeNeg(32);
        int ans = 0;

        for (int i = nums.size() - 1; i >= 0; --i) {
            if (nums[i] > 0) {
                ans += tree.count(nums[i] % 2 == 1 ? nums[i] / 2 : max(0, nums[i] / 2 - 1), true);
                ans += treeNeg.size();
                tree.add(nums[i]);
            }
            else if (nums[i] == 0) {
                ans += treeNeg.size();
                tree.add(nums[i]);
            }
            else {
                ans += treeNeg.count(-((long long)nums[i] / 2) + 1, false);
                treeNeg.add(-nums[i]);
            }
        }

        return ans;
    }
};

int main() {
    int ans;

    {
        BinaryTrieTree tree(4);
        tree.add(0);
        tree.add(3);
        tree.add(7);
        tree.add(10);
        assert(2 == (ans = tree.count(6, true)));
        assert(3 == (ans = tree.count(7, true)));
        assert(3 == (ans = tree.count(8, true)));
        assert(3 == (ans = tree.count(9, true)));
        assert(4 == (ans = tree.count(10, true)));
        assert(4 == (ans = tree.count(11, true)));
    }

    {
        BinaryTrieTree tree(4);
        tree.add(0);
        tree.add(3);
        tree.add(3);
        tree.add(7);
        tree.add(7);
        tree.add(10);
        assert(3 == (ans = tree.count(6, true)));
        assert(5 == (ans = tree.count(7, true)));
    }

    {
        assert(2 == (ans = Solution().reversePairs({ 1, 3, 2, 3, 1 })));
    }

    {
        assert(1 == (ans = Solution().reversePairs({ 5, 0, 3 })));
    }

    {
        assert(9 == (ans = Solution().reversePairs({ 2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647 })));
    }
}
