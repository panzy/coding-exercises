//
// 4. 0
// https://leetcode.com/problems/0/
// 
// 2085 / 2085 test cases passed.	Status: Accepted
// Runtime: 2 ms
// Memory Usage: 40.6 MB
// 
// --
// Created by Zhiyong Pan
// Git commit time:      19/07/2022, 21:12:03
// LeetCode submit time: 1 year, 10 months ago
// Submission detail page: https://leetcode.com/submissions/detail/391442391//
// Committed with a user script: https://gist.github.com/panzy/c126371311dc166a94e611de8f45e63c
//
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		
		// swap to make sure nums1 is longer than nums2
		if (m < n) {
			int[] ta = nums1;
			nums1 = nums2;
			nums2 = ta;
			
			int ti = m;
			m = n;
			n = ti;
		}
		
		// |stop| is a position in the combined array,
		// the median is either [stop-1] or ([stop-1]+[stop])/2, depending on
		// whether is combined length is odd or even.
		// Warning: |stop| might be the end.
		int stop = (m + n) % 2 == 0 ? (m + n) / 2 : (m + n) / 2 + 1;
		assert stop >= 1;

		// nums1[0..i] and nums2[0..j] consist the first half of the combined
		// array, so
		// 	(i + 1) + (j + 1) == stop + 1
		int i = -1, j = -1;
		
		// try to jump |i| and |j|
		int p = n > 0 ? positionFor(nums1, nums2[0]) : -1;
		int q = n > 0 ? positionFor(nums1, nums2[n - 1]) : -1;
		if (q != -1 && q + n < stop) { // the whole nums2 is before the median
			j = n - 1;
		} else if (p != -1) {
			if (p > stop) { // the whole nums2 is after the median
				i = stop;
			} else { // part of nums2 is before the median
				i = p - 1;
			}
		}
		
		// Increase |i| and |j| until we reach |stop|.
		//
		// Method: repeatedly choose the smaller one from
		// 		nums1[i+1]
		// and
		// 		nums2[j+1]
		while (i + j + 1 < stop) {
			if (i + 1 == m) {
				j = stop - i - 1;
			} else if (j + 1 == n) {
				i = stop - j - 1;
			} else {
				if (nums1[i + 1] <= nums2[j + 1]) {
					++i;
				} else {
					++j;
				}
			}
		}
		
		// Determine the median value
		
		// |a| and |b| are the last 2 items of nums1[0..i],
		// |c| and |d| are the last 2 items of nums2[0..j].
		// Warning: 3 out of 4 might be not exist.
		// Non-existent values are marked by Integer.MIN_VALUE.
		int a, b, c, d;
		a = b = c = d = Integer.MIN_VALUE;
		
		if (i >= 0) {
			if (i > 0)
				a = nums1[i - 1];
			if (i < m)
				b = nums1[i];
		}
		
		if (j >= 0) {
			if (j > 0)
				c = nums2[j - 1];
			if (j < n)
				d = nums2[j];
		}
		
		// |v| is the value after the median in the combined array,
		// |v0| is the value before or equals to the median.
		int v = Math.max(b, d);
		int v0 = Math.max(Math.max(a, c), Math.min(b,  d));
		
		if ((m + n) % 2 == 1) {
			return v0;
		} else {
			return (v0 + v) / 2.0;
		}
    }
    
    /**
     * Get a position in a sorted array that if the specified value is inserted
     * there, the array will remain sorted.
     * @param a the sorted array.
     * @param v the value to insert.
     * @return between 0 and a.length, inclusively.
     */
    public static int positionFor(int[] a, int v) {
    	if (a.length == 0) return 0;
    	return positionFor_(v, a, 0, a.length);
    }
   
    // recursive implementation for positionFor().
    private static int positionFor_(int v, int[] a, int lo, int hi) {
    	if (lo >= hi) return lo;
    	
    	int m = (lo + hi) / 2;
    	if (v == a[m]) return m;
    	else if (v < a[m]) return positionFor_(v, a, lo, m - 1);
    	else return positionFor_(v, a, m + 1, hi);
    }
}