package kth_largest_element_in_an_array;

import java.util.PriorityQueue;

/**
 * Cheating with the heap implementation provided by Java platform.
 *
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap;

        // use a min heap or a max heap depending on where is k in the array's index range.
        if (k <= nums.length / 2) {
            // use a max heap
            heap = new PriorityQueue<>(k, (a, b) -> b - a);
        } else {
            // use a min heap
            // Turn the problem into "find the (n-k+1)-th smallest element".
            k = nums.length - k + 1;
            heap = new PriorityQueue<>(k);
        }

        for (int i : nums) {
            heap.add(i);
        }

        for (int i = 0; i < k - 1; ++i) {
            heap.poll();
        }

        return heap.poll();
    }
}
