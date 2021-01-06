package kth_largest_element_in_an_array;

import java.util.PriorityQueue;

/**
 * Cheating with the heap implementation provided by Java platform.
 *
 * Created by Zhiyong Pan on 2021-01-06.
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // a max heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> b - a);

        for (int i : nums) {
            heap.add(i);
        }

        for (int i = 0; i < k - 1; ++i) {
            heap.poll();
        }

        return heap.poll();
    }
}
