import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
Queues: A Tale of Two Stacks
https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
*/

class MyQueue<E> {
    Stack<E> stack1 = new Stack<>(), stack2 = new Stack<>();

    public E peek() {
        ensureStack2();
        assert !stack2.isEmpty();
        return stack2.peek();
    }

    public void pop() {
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

    public void push(E v) {
        stack1.push(v);
    }
}

public class Solution {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.push(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.pop();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}