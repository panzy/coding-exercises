package vertical_order_traversal_of_a_binary_tree_987;

import util.bintree.TreeFactory;
import util.bintree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhiyong Pan on 2021-01-29.
 */
public class Solution {
    // key = x coordinate
    // value = [y coordinate, node value]
    HashMap<Integer, ArrayList<int[]>> reports;

    int minX, maxX;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        reports = new HashMap<>();
        minX = 0;
        maxX = 0;

        if (root != null)
            traverse(root, 0, 0);

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int x = minX; x <= maxX; ++x) {
            ArrayList<int[]> r = reports.get(x);
            if (r != null) {
                // Sort by y coordinates and value.
                Collections.sort(r, (a, b) -> {
                    if (a[0] != b[0])
                        return b[0] - a[0];
                    else
                        return a[1] - b[1];
                });
                ans.add(r.stream().mapToInt(a -> a[1]).boxed().collect(Collectors.toList()));
            }
        }

        return ans;
    }

    private void traverse(TreeNode root, int x, int y) {
        // Put root into reports
        if (reports.containsKey(x))
            reports.get(x).add(new int[]{y, root.val});
        else {
            ArrayList<int[]> report = new ArrayList<>();
            report.add(new int[]{y, root.val});
            reports.put(x, report);
        }

        if (x < minX)
            minX = x;
        else if (x > maxX)
            maxX = x;

        if (root.left != null)
            traverse(root.left, x - 1, y - 1);

        if (root.right != null)
            traverse(root.right, x + 1, y - 1);
    }

    @Test
    void testVerticalTraversal() {
        {
            // [0,8,1,null,null,3,2,null,4,5,null,null,7,6]
            // [[8],[0,3,6],[1,4,5],[2,7]]
            TreeNode root = TreeFactory.fromArray(new Integer[]{0, 8, 1, null, null, 3, 2, null, 4, 5, null, null, 7, 6});
            List<List<Integer>> reports = verticalTraversal(root);
            Assertions.assertArrayEquals(new Integer[]{8}, reports.get(0).toArray(new Integer[1]));
            Assertions.assertArrayEquals(new Integer[]{0, 3, 6}, reports.get(1).toArray(new Integer[3]));
            Assertions.assertArrayEquals(new Integer[]{1, 4, 5}, reports.get(2).toArray(new Integer[3]));
            Assertions.assertArrayEquals(new Integer[]{2, 7}, reports.get(3).toArray(new Integer[2]));
        }

        {
            // [0,2,1,3,null,null,null,4,5,null,7,6,null,10,8,11,9]
            // [[4,10,11],[3,6,7],[2,5,8,9],[0],[1]]
            TreeNode root = TreeFactory.fromArray(new Integer[]{0, 2, 1, 3, null, null, null, 4, 5, null, 7, 6, null, 10, 8, 11, 9});
            List<List<Integer>> reports = verticalTraversal(root);
            Assertions.assertArrayEquals(new Integer[]{4, 10, 11}, reports.get(0).toArray(new Integer[3]));
            Assertions.assertArrayEquals(new Integer[]{3, 6, 7}, reports.get(1).toArray(new Integer[3]));
            Assertions.assertArrayEquals(new Integer[]{2, 5, 8, 9}, reports.get(2).toArray(new Integer[4]));
            Assertions.assertArrayEquals(new Integer[]{0}, reports.get(3).toArray(new Integer[1]));
            Assertions.assertArrayEquals(new Integer[]{1}, reports.get(4).toArray(new Integer[1]));
        }
    }
}
