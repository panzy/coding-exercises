/* 823. Binary Trees With Factors
 * https://leetcode.com/problems/binary-trees-with-factors/
 *
 * Given an array of unique integers, arr, where each integer arr[i] is
 * strictly greater than 1.
 *
 * We make a binary tree using these integers, and each number may be used for
 * any number of times. Each non-leaf node's value should be equal to the
 * product of the values of its children.
 * 
 * Return the number of binary trees we can make. The answer may be too large
 * so return the answer modulo 109 + 7.
 * 
 * Example 1:
 * 
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 * 
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10,
 * 2, 5], [10, 5, 2].
 * 
 * 
 * Constraints:
 * 
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 * 
 * --
 * Zhiyong
 * 2021-03-14
*/

class Solution {
    const unsigned long long M = 1e9 + 7;
    unordered_set<int> nums;
    unordered_map<int, unsigned long long> memo;
    
    unsigned long long count(int root) {
        if (memo.count(root)) return memo[root];
        
        unsigned long long ans = 1;
        for (int a : nums) {
            if (a < root && root % a == 0 && nums.count(root / a)) {
                //cout << root << "=" << a << "x" << root / a << endl;
                ans = (ans + count(a) * count(root / a)) % M;
            }
        }
        
        memo[root] = ans;
        return ans;
    }
    
public:
    int numFactoredBinaryTrees(vector<int>& arr) {
        memo.clear();
        nums.clear();        
        for (int v : arr) nums.insert(v);
        
        unsigned long long ans = 0;
        for (int v : arr) {
            ans = (ans + count(v)) % M;
        }
        
        return ans;
    }
};
