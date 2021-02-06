package next_greater_element_ii;

import _lib.IntArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * An approach adopting the monotonic stack idea.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class SolutionStack {
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length < 1) return new int[0];

        // attributes of the stack:
        // (for a given index i of the array nums)
        // 1. all elements are after [i] when they were in the array;
        // 2. all elements are in the same order as they're in the array, the top one is the nearest to [i];
        // 3. all elements are greater than [i] (smaller ones are simply dropped);
        // 4. it's ordered, with the top being the smallest.
        //
        // the beauty of these attributes is that:
        // 1. this stack is easy to build as we scan the array backwards;
        // 2. the stack top is always the next greater number for [i].
        Stack<Integer> stack = new Stack<>();
        stack.ensureCapacity(nums.length);

        int[] ans = new int[nums.length];

        for (int i = IntArrays.indexOfMax(nums); i >= 0 - nums.length; --i) {
            // take care of index bound
            int ii = i >= 0 ? i : i + nums.length;

            // pop all numbers that are not greater than [i]
            while (!stack.isEmpty() && stack.peek() <= nums[ii])
                stack.pop();

            // the stack top is the next greater number for [i]
            ans[ii] = stack.isEmpty() ? -1 : stack.peek();

            // [i] is the new top
            stack.push(nums[ii]);
        }

        // collect answers from the map
        return ans;
    }
}

class SolutionStackTest {
    SolutionBruteforce solutionB = new SolutionBruteforce();
    SolutionStack solution = new SolutionStack();

    @Test
    void empty() {
        int[] nums = new int[0];
        int[] expected = new int[0];
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }

    @Test
    void example1() {
        int[] nums = new int[]{1, 2, 1};
        int[] expected = new int[]{2, -1, 2};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }

    @Test
    void example2() {
        int[] nums = new int[]{1, 2, 4, 3};
        int[] expected = new int[]{2, 4, -1, 4};
        Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
    }

    @Test
    void crossValidate() {
        // use the bruteforce solution to validate this solution.
        for (int r = 0; r < 100; ++r) {
            int[] nums = new int[10];
            IntArrays.fillUniqueRandom(nums);
            int[] expected = solutionB.nextGreaterElements(nums);
            System.out.println("nums: " + IntArrays.join(nums));
            System.out.println("expected: " + IntArrays.join(expected));
            Assertions.assertArrayEquals(expected, solution.nextGreaterElements(nums));
        }
    }
}
