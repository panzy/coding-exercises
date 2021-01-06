# 215. Kth Largest Element in an Array

https://leetcode.com/problems/kth-largest-element-in-an-array/

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

## Notes

The `std::nth_element()` function in C++ STL does exactly this job.

For Java, there is a heap implementation, `java.util.PriorityQueue`.