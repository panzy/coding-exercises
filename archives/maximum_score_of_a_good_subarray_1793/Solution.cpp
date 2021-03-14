/* 1793. Maximum Score of a Good Subarray
https://leetcode.com/problems/maximum-score-of-a-good-subarray/

You are given an array of integers nums (0-indexed) and an integer k.

The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

Return the maximum possible score of a good subarray.

Example 1:

Input: nums = [1,4,3,7,4,5], k = 3
Output: 15
Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.

Example 2:

Input: nums = [5,5,4,5,4,1,1,1], k = 0
Output: 20
Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 2 * 10^4
0 <= k < nums.length

--------------------------------------------------------------------------------
This problem is similar to 84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/

but it's less complex and an O(n) alglorithm is possible.

--
Zhiyong
2021-03-14
*/
class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size();
        
        int i = k, j = k; // [i, j] defines a subarray
        int len = 1; // len = j + 1 - i
        int score = nums[k];
        int minEle = nums[k]; // the min element inside the subarray
        
        // The subarray start from length 1.
        // At this length, it has only one possible position.
        // 
        // For each length, when we want to increase it,
        // we essentially have only two options: leftwards and rightwards.        
        // We choose them greedily, based on their impact on |minEle|.
        //
        // XXX How to prove the algorithm is correct?
        // 
        // I have a hard time understanding why growing the subarray from[k, k]
        // gradually to [0, n-1] linearly is correct because obviously, it
        // doesn't check all the good subarrays. How does the algorithm make
        // sure the correct answer is not hidden in those missed subarrays?

        // Besides, the subarray growing process is not the only one that can
        // lead to the final answer. So we can't say "at this point we must
        // grow [i, j] to [i, j+1] because [i-1] has a worse impact on the min
        // element than [j+1] does." No, choosing a less desired direction at
        // some points doesn't necessarily lead to a wrong answer. See Example
        // 1, nums = [1,4,3,7,4,5], k = 3, it's okay to grow {7} to {3,7} other
        // than {7,4}.

        // Then I try to convince myself that the process is correct because
        // it's reversible. Suppose the final answer is [i, j]. Performing the
        // reversed algorithm on this subarray, that is, shrinking it
        // gradually, will eventually reach [k, k]. So in the original
        // algorithm, [i, j] is guaranteed to reachable from [k, k]. Is this
        // valid proof?
        
        while (len < n) {
            // extending leftwards will change minEle to ...
            int L = i > 0 ? min(minEle, nums[i - 1]) : 0;
            int R = j + 1 < n ? min(minEle, nums[j + 1]) : 0;
            
            if (L == R) {
                --i;
                ++j;
                len += 2;
                minEle = L;
            } else if (L > R) {
                --i;
                ++len;
                minEle = L;
            } else {
                ++j;
                ++len;
                minEle = R;
            }
            
            score = max(score, minEle * len);                
        }
        
        return score;
    }
};
