/*
Implemented a binary Trie tree for storing integers.

With the help of this data structure, these problems are easy to solve:

    421. Maximum XOR of Two Numbers in an Array
    https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

    1707. Maximum XOR With an Element From Array
    https://leetcode.com/problems/maximum-xor-with-an-element-from-array/

--
Zhiyong
2021-03-13
2021-03-18 added find(int val, int upperBound) overload
*/
#include <iostream>
#include <numeric>
#include <vector>
#include <stack>
#include <queue>
#include <map>
#include <set>
#include <unordered_map>
#include <unordered_set>
#include <tuple>
#include <algorithm>
#include <functional>
#include <regex>
#include <mutex>
#include <barrier>
#include <cassert>
#include <bitset>
using namespace std;

class BinaryTrieTree {
    struct Node {
        bool flag; // indicates whether this node has a value.
        unsigned int minVal; // the minimal value this node leads to.
        Node* children[2];

        Node() :
        flag(false),
        minVal(numeric_limits<unsigned int>::max()),
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
            p->children[childIdx]->minVal = min(p->children[childIdx]->minVal, val);
            p = p->children[childIdx];
        }

        p->flag = true;
        
        if (val == 0)
            hasZero = true;
    }

    // Find the closest value.
    // Returns -1 if the tree is empty.
    int find(unsigned int val) {
        // Navigate the corresponding node.
        Node* p = &root;
        int sum = 0; // value of the path
        for (unsigned int mask = 1 << (height - 1); mask > 0 && p != nullptr; mask >>= 1) {

            int childIdx = val & mask ? 1 : 0;
            
            // If the desired branch doesn't exist, go to the other.
            if (!p->children[childIdx]) {
                childIdx = 1 - childIdx;
            }

            p = p->children[childIdx];

            // If we're going to the non-zero branch, add the value of that bit to the sum.
            if (p != nullptr && childIdx == 1) {
                sum += mask;
            }
        }

        return sum == 0 ? (hasZero ? 0 : -1) : sum;
    }

    // Find the closest value.
    // Returns -1 if no value is <= upperBound.
    int find(unsigned int val, unsigned int upperBound) {
        // Navigate the corresponding node.
        Node* p = &root;
        int sum = 0; // value of the path
        for (unsigned int mask = 1 << (height - 1); mask > 0 && p != nullptr; mask >>= 1) {

            int childIdx = val & mask ? 1 : 0;
            
            // If the desired branch doesn't exist, go to the other.
            if (!p->children[childIdx] || p->children[childIdx]->minVal > upperBound) {
                childIdx = 1 - childIdx;
            }

            p = p->children[childIdx];

            // If we're going to the non-zero branch, add the value of that bit to the sum.
            if (p != nullptr && childIdx == 1) {
                sum += mask;
                if (sum > upperBound)
                    return -1;
            }
        }

        return sum == 0 ? (hasZero ? 0 : -1) : sum;
    }
    
};

int main() {
    int ans;

    {
        BinaryTrieTree tree(4);
        tree.add(3);
        tree.add(7);
        tree.add(10);
        assert(10 == (ans = tree.find(~3)));

        tree.add(15);
        assert(15 == (ans = tree.find(~3)));

        tree.add(12);
        assert(12 == (ans = tree.find(~3)));

        assert(10 == (ans = tree.find(11)));
        assert(12 == (ans = tree.find(13)));
        assert(15 == (ans = tree.find(14)));
        assert(12 == (ans = tree.find(14, 14)));
        assert(12 == (ans = tree.find(14, 12)));
        assert(10 == (ans = tree.find(14, 11)));
    }

    { // finding in empty tree should returns -1
        BinaryTrieTree tree(4);
        assert(-1 == (ans = tree.find(1)));
    }

    {
        BinaryTrieTree tree(4);
        tree.add(0);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        assert(2 == (ans = tree.find(~1, 3)));
        assert(2 == (ans = tree.find(~5, 6)));
    }

    {
        BinaryTrieTree tree(4);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        assert(-1 == (ans = tree.find(~8, 1)));
    }
}
