/* 870. Advantage Shuffle
 * https://leetcode.com/problems/advantage-shuffle/
 * --
 * Zhiyong
 * 2021-03-24
 */

/*
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
*/
class Solution {
public:
    vector<int> advantageCount(vector<int>& A, vector<int>& B) {
        // Greedy:
        // B (11,3) (13,0) (25,1) (32,2)  // sort B
        // A   8     12     24     32     // sort A
        // A  12     24     32      8     // arrange A
        // A2 (12,3) (24,0) (32,1) (8,2)   // associate A with B's original indices
        // A2 (24,0) (32,1) (8,2)  (12,3)  // sort A2 by index
        
        // Sort B by value, keeping the original indicies
        vector<pair<int, int>> B2; // pair = {num, idx}
        B2.reserve(B.size());
        for (int i = 0; i < B.size(); ++i) 
            B2.push_back({B[i], i});
        sort(B2.begin(), B2.end());
        
        //cout << "B2 after sort: ";
        //for (auto& p : B2) cout << '(' << p.first << ',' << p.second << ") ";
        //cout << endl;
        
        // Sort A by value
        sort(A.begin(), A.end());
        
        // For each number in sorted B, try to find the minimum number in A that is greater than it.
        // Unused small numbers of A will be put aside, they can be used to match "super" numbers of B.
        vector<pair<int, int>> A2; // pair = {idx, num}
        stack<int> unusedA;
        int idxA = 0;
        for (int i = 0; i < B2.size(); ++i) {
            while (idxA < A.size() && A[idxA] <= B2[i].first) {
                unusedA.push(A[idxA]);
                ++idxA;
            }
            if (idxA < A.size()) {
                A2.push_back({B2[i].second, A[idxA]});
                ++idxA;
            } else {
                A2.push_back({B2[i].second, unusedA.top()});
                unusedA.pop();
            }
        }
        
        for (auto&& p : A2)
            A[p.first] = p.second;
        return A;
    }
};
