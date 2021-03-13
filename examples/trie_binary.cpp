/*
421. Maximum XOR of Two Numbers in an Array
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 ≤ i ≤ j < n.

Follow up: Could you do this in O(n) runtime?

Example 1:

Input: nums = [3,10,5,25,2,8]
Output: 28
Explanation: The maximum result is 5 XOR 25 = 28.
Example 2:

Input: nums = [0]
Output: 0
Example 3:

Input: nums = [2,4]
Output: 6
Example 4:

Input: nums = [8,10,2]
Output: 10
Example 5:

Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
Output: 127

Constraints:

1 <= nums.length <= 2 * 10^4
0 <= nums[i] <= 2^31 - 1

--
Zhiyong
2021-03-13
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

/*
A slow implementation of trie tree.
Runtime: 176 ms, faster than 40.66% of C++ online submissions for Maximum XOR of Two Numbers in an Array.
Memory Usage: 111.2 MB, less than 5.09% of C++ online submissions for Maximum XOR of Two Numbers in an Array.

If delete the ~Node(), performance is improved significntly:
Runtime: 72 ms, faster than 87.51% of C++ online submissions for Maximum XOR of Two Numbers in an Array.
Memory Usage: 35.9 MB, less than 47.95% of C++ online submissions for Maximum XOR of Two Numbers in an Array.
*/
class BinaryTrieTree {
    struct Node {
        bool flag; // indicates whether this node has a value.
        Node* children[2];

        Node() : flag(false), children{ nullptr, nullptr }
        {}

        ~Node() {
            if (children[0])
                delete children[0];
            if (children[1])
                delete children[1];
        }
    };

    Node root;
    int height;

public: 
    BinaryTrieTree(int height_) : height(height_) {
    }

    void add(unsigned int val) {
        // Navigate to the corresponding node. Create it if not exist.
        Node* p = &root;
        for (unsigned int mask = 1 << (height - 1); mask > 0; mask >>= 1) {
            int childIdx = val & mask ? 1 : 0;
            if (!p->children[childIdx])
                p->children[childIdx] = new Node();
            p = p->children[childIdx];
        }

        p->flag = true;
    }

    // Find the closest value.
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
            if (childIdx == 1)
                sum += mask;
        }

        return sum;
    }
    
};

class Solution {
public:
    int findMaximumXOR(const vector<int>& nums) {
        int maxVal = *max_element(nums.begin(), nums.end());
        if (maxVal == 0) return 0; // because log2(0) will fail
        BinaryTrieTree tree(log2(maxVal) + 1);

        for (int i : nums)
            tree.add(i);

        int ans = 0;
        for (int i : nums) {
            int j = tree.find(~i);
            ans = max(ans, i ^ j);
        }

        return ans;
    }
};

int main() {
    int ans;

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

    assert(0 == (ans = Solution().findMaximumXOR({0})));
    assert(6 == (ans = Solution().findMaximumXOR({2,4})));
    assert(10 == (ans = Solution().findMaximumXOR({8,10,2})));
    assert(28 == (ans = Solution().findMaximumXOR({3,10,5,25,2,8})));
    assert(127 == (ans = Solution().findMaximumXOR({14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70})));
}
