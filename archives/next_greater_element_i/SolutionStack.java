package next_greater_element_i;

import _lib.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * An approach adopting the monotonic stack idea.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class SolutionStack {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // attributes of the stack:
        // (for a given index i of the array nums2)
        // 1. all elements are after [i] when they were in the array;
        // 2. all elements are in the same order as they're in the array, the top one is the nearest to [i];
        // 3. all elements are greater than [i] (smaller ones are simply dropped);
        // 4. it's ordered, with the top being the smallest.
        //
        // the beauty of these attributes is that:
        // 1. this stack is easy to build as we scan the array backwards;
        // 2. the stack top is always the next greater number for [i].
        Stack<Integer> stack = new Stack<>();
        stack.ensureCapacity(nums2.length);

        // collect all answers -- some of them are unwanted because the key
        // doesn't exist in nums1, but we don't care.
        HashMap<Integer, Integer> map = new HashMap<>(nums1.length);

        for (int i = nums2.length - 1; i >= 0; --i) {
            // pop all numbers that are not greater than [i]
            while (!stack.isEmpty() && stack.peek() <= nums2[i])
                stack.pop();

            // the stack top is the next greater number for [i]
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());

            // [i] is the new top
            stack.push(nums2[i]);
        }

        // collect answers from the map
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}

class SolutionStackTest {
    SolutionBruteforce solutionB = new SolutionBruteforce();
    SolutionStack solution = new SolutionStack();

    @Test
    void example1() {
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        int[] expected = new int[]{-1, 3, -1};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void example2() {
        int[] nums1 = new int[]{2, 4};
        int[] nums2 = new int[]{1, 2, 3, 4};
        int[] expected = new int[]{3, -1};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void crossValidate() {
        // use the bruteforce solution to validate this solution.
        for (int r = 0; r < 100; ++r) {
            int n = 10;
            int[] nums2 = new int[n];

            IntArrays.fillUniqueRandom(nums2);

            int[] nums1 = Arrays.copyOf(nums2, n - 4);
            IntArrays.shuffle(nums1);

            int[] expected = solutionB.nextGreaterElement(nums1, nums2);

            System.out.println("nums1: " + IntArrays.join(nums1));
            System.out.println("nums2: " + IntArrays.join(nums2));
            System.out.println("expected: " + IntArrays.join(expected));
            Assertions.assertArrayEquals(expected, solution.nextGreaterElement(nums1, nums2));
        }
    }
}
