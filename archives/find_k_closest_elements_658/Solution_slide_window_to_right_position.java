//
// 658. Find K Closest Elements
// https://leetcode.com/problems/find-k-closest-elements/
// 
// 61 / 61 test cases passed.	Status: Accepted
// Runtime: 2 ms
// Memory Usage: 40.9 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      02/07/2021, 19:53:33
// LeetCode submit time: 5 months, 2 weeks ago
// Submission detail page: https://leetcode.com/submissions/detail/442491150//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
/**
 * Created by Zhiyong Pan on 2021-01-13.
 */
public class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // range [a, b) is the answer
        int a = 0, b = k;

        for (int i = k; i < arr.length; ++i) {
            if (arr[i] <= x) {
                ++a;
                ++b;
            } else if (arr[i] - x < x - arr[a]) {
                ++a;
                ++b;
            }

            if (arr[a] >= x)
                break;
        }

        List<Integer> ans = new ArrayList<>(k);
        for (int i = a; i < b; ++i) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
