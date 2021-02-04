package queue_using_two_stacks;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Zhiyong Pan on 2021-02-04.
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        Solution solution = new Solution();

        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; ++i) {
            int cmd = scanner.nextInt();
            if (cmd == 1) {
                solution.push(scanner.nextInt());
            } else if (cmd == 2) {
                solution.pop();
            } else {
                assert scanner.nextInt() == 3;
                System.out.println(solution.peek());
            }
        }
    }

    private int peek() {
        ensureStack2();
        assert !stack2.isEmpty();
        return stack2.peek();
    }

    private void pop() {
        ensureStack2();
        assert !stack2.isEmpty();
        stack2.pop();
    }

    private void ensureStack2() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    private void push(int v) {
        stack1.push(v);
    }
}
