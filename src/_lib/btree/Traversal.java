package _lib.btree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Implement a binary-tree traversal API with various algorithms: DFS(with 3 orders), BFS and Backtracking.
 *
 * --
 * Zhiyong Pan, 2020-12-30
 */
public class Traversal {

    /**
     * Listen for node events during traversal.
     * @param <Result>
     */
    public interface Listener<Result> {
        /** A node is outputted. */
        void onNode(TreeNode node);
        default Result onDone() { return null; };
    }

    /**
     * For backtracking traversal, it's also possible to get every nodes on the path towards a leaf.
     * @param <Result>
     */
    public interface BacktrackingListener<Result> extends Listener<Result> {
        default void onPathNode(TreeNode node) {};
    }

    /**
     * Traverse a tree.
     * @param root root of the tree to traverse.
     * @param listener callbacks for node events.
     * @param <ResultType> type of the return value.
     * @return the value returned by {@link BacktrackingListener#onDone()}.
     */
    public static <ResultType> ResultType backtrack(TreeNode root, BacktrackingListener<ResultType> listener) {

        Deque<TreeNode> path = new ArrayDeque<>();
        // The most recently popped node.
        // Init it to a random so we won't confuse it with an absent child node.
        TreeNode poppedNode = new TreeNode();

        // push the root
        listener.onPathNode(root);
        path.push(root);

        TreeNode top = path.peek();
        while (top != null) {
            if (top.left != null && top.left != poppedNode && top.right != poppedNode) {
                // go left
                listener.onPathNode(top.left);
                path.push(top.left);
                top = top.left;
            } else if (top.right != null && top.right != poppedNode) {
                // go right
                listener.onPathNode(top.right);
                path.push(top.right);
                top = top.right;
            } else {
                // go back
                listener.onNode(top);
                poppedNode = path.pop();
                top = path.peek();
            }
        }

        return listener.onDone();
    }

    public static <Result> Result preorder(TreeNode root, Listener<Result> listener) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            listener.onNode(root);

            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return listener.onDone();
    }

    public static <Result> Result inorder(TreeNode root, Listener<Result> listener) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();

            if (root.left == null && root.right == null
                    || stack.peek() == root.right) {
                listener.onNode(root);
                continue;
            }

            if (root.right != null) {
                stack.push(root.right);
                stack.push(root);
            }

            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return listener.onDone();
    }

    public static <Result> Result postorder(TreeNode root, Listener<Result> listener) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        stack.push(root);

        TreeNode prevPopped = new TreeNode();

        while (!stack.isEmpty()) {
            root = stack.peek();

            if (prevPopped == root.right || prevPopped == root.left) {
                // this node's children have been visited, it's time to turn back
                listener.onNode(root);
                prevPopped = stack.pop();
                continue;
            }

            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }

            if (root.right == null && root.left == null) {
                // leaf reached, it's time to turn back
                listener.onNode(root);
                prevPopped = stack.pop();
            }
        }

        return listener.onDone();
    }

    public static <Result> Result bfs(TreeNode root, Listener<Result> listener) {

        Queue<TreeNode> layer = new ArrayDeque<>();
        layer.add(root);

        while (!layer.isEmpty()) {
            root = layer.remove();
            listener.onNode(root);
            if (root.left != null)
                layer.add(root.left);
            if (root.right != null)
                layer.add(root.right);
        }

        return listener.onDone();
    }

    ////////////////////////////////////////////////////////////////////////////////
    // Unit tests below
    ////////////////////////////////////////////////////////////////////////////////

    Listener<int[]> listener = new Listener() {
        int[] output = new int[5];
        int i = 0;

        @Override
        public void onNode(TreeNode node) {
            output[i] = node.val;
            ++i;
        }

        @Override
        public int[] onDone() {
            return output;
        }
    };

    BacktrackingListener<int[]> backtrackingListener = new BacktrackingListener() {
        int i = 0;
        int[] output = new int[5];

        @Override
        public void onNode(TreeNode node) {
            output[i] = node.val;
            ++i;
        }

        @Override
        public int[] onDone() {
            return output;
        }
    };

    @Test
    void backtrack_example1() {
        Integer[] treeValues = new Integer[]{5, 3, 4, 1, 2};
        int[] output = backtrack(TreeFactory.fromArray(treeValues), backtrackingListener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void backtrack_example2() {
        Integer[] treeValues = new Integer[]{5, 1, 4, null, null, 2, 3};
        int[] output = backtrack(TreeFactory.fromArray(treeValues), backtrackingListener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void preorder_example1() {
        Integer[] treeValues = new Integer[]{1, 2, 5, 3, 4};
        int[] output = preorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void preorder_example2() {
        Integer[] treeValues = new Integer[]{1, 2, 3, null, null, 4, 5};
        int[] output = preorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void inorder_example1() {
        Integer[] treeValues = new Integer[]{4, 2, 5, 1, 3};
        int[] output = inorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void inorder_example2() {
        Integer[] treeValues = new Integer[]{2, 1, 4, null, null, 3, 5};
        int[] output = inorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void postorder_example1() {
        Integer[] treeValues = new Integer[]{5, 3, 4, 1, 2};
        int[] output = postorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void postorder_example2() {
        Integer[] treeValues = new Integer[]{5, 1, 4, null, null, 2, 3};
        int[] output = postorder(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void bfs_example1() {
        Integer[] treeValues = new Integer[]{1, 2, 3, 4, 5};
        int[] output = bfs(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }

    @Test
    void bfs_example2() {
        Integer[] treeValues = new Integer[]{1, 2, 3, null, null, 4, 5};
        int[] output = bfs(TreeFactory.fromArray(treeValues), listener);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, output);
    }
}