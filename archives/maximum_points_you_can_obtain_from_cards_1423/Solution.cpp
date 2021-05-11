/* 1423. Maximum Points You Can Obtain from Cards
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 * --
 * Zhiyong 2021-05-11
 */
class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        // answer = sum - (the minimum sum of a subarray of length n - k).
        
        int n = cardPoints.size();
        int minSubSum = reduce(cardPoints.begin(), cardPoints.begin() + n - k, 0);
        int totalSum = minSubSum + reduce(cardPoints.begin() + n - k, cardPoints.end(), 0);
        
        if (k < n) {
            for (int i = n - k, s = minSubSum; i < n; ++i) {
                s = s - cardPoints[i - (n - k)] + cardPoints[i];
                if (s < minSubSum)
                    minSubSum = s;
            }
        }
        
        return totalSum - minSubSum;
    }
};
