/*
1707. Maximum XOR With an Element From Array
https://leetcode.com/problems/maximum-xor-with-an-element-from-array/

You are given an array nums consisting of non-negative integers. You are also given a queries array, where queries[i] = [xi, mi].

The answer to the ith query is the maximum bitwise XOR value of xi and any element of nums that does not exceed mi. In other words, the answer is max(nums[j] XOR xi) for all j such that nums[j] <= mi. If all elements in nums are larger than mi, then the answer is -1.

Return an integer array answer where answer.length == queries.length and answer[i] is the answer to the ith query.

Example 1:

Input: nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
Output: [3,3,7]
Explanation:
1) 0 and 1 are the only two integers not greater than 1. 0 XOR 3 = 3 and 1 XOR 3 = 2. The larger of the two is 3.
2) 1 XOR 2 = 3.
3) 5 XOR 2 = 7.
Example 2:

Input: nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
Output: [15,-1,5]


Constraints:

1 <= nums.length, queries.length <= 10^5
queries[i].length == 2
0 <= nums[j], xi, mi <= 10^9

--------------------------------------------------------------------------------

This problem is harder than
    421. Maximum XOR of Two Numbers in an Array
    https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
in that you need to ensure the value found from the trie tree is not greater
than an upper bound, and upper bound changes for each query.

We can sort the queries by upper bound and incrementally add numbers to the
trie tree.

Or, as I did in this solution, enhance the trie tree class by providing an
overload of find():
    int find(int val, int upperBound)

--
Zhiyong
2021-03-18
*/

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

        // Could lead to TLE
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

class Solution {
public:
    vector<int> maximizeXor(vector<int>& nums, vector<vector<int>>& queries) {
        vector<int> ans(queries.size(), 0);

        int maxVal = *max_element(nums.begin(), nums.end());
        for (auto&& q : queries) maxVal = max(maxVal, q[0]);

        if (maxVal == 0) return ans; // because log2(0) will fail
        BinaryTrieTree tree(log2(maxVal) + 1);

        for (int v : nums)
            tree.add(v);

        int i = 0;
        for (auto&& q : queries) {
            int x = q[0];
            int m = q[1];
            int t = tree.find(~x, m);
            ans[i++] = t == -1 ? -1 : x ^ t;
            //cout << "x=" << x << " m=" << m << " find()=" << tree.find(~x, m) << endl;
        }

        return ans;
    }
};

