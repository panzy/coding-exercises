package increasing_subsequences_491;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Finally! I came up with this piece of code. It's elegant and fast.
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution3_graph {
    int[] nums;
    ArrayList<List<Integer>> ans;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        ans = new ArrayList<>();

        // Notice that:
        // (1) I didn't init a search from each and every number, because that way we would have to deduplicate
        //     the answers in the end.
        // (2) I didn't init a search from [0], because that way any less numbers after [0] would be unreachable.
        //
        // So I introduced a dummy number: [-1] = MIN_VALUE.
        search(-1, new ArrayList<>(), Integer.MIN_VALUE);

        return ans;
    }

    /**
     * Search increasing subsequences from the given start point.
     *
     * @param from the number index we are current at.
     * @param path the path from the very beginning to the current number (inclusive!)
     * @param pathTail the last number of the path.
     */
    private void search(int from, ArrayList<Integer> path, int pathTail) {

        if (path.size() > 1) { // path + the current number is a valid subsequence
            ArrayList<Integer> newAns = new ArrayList<>(path);
            ans.add(newAns);
        }

        HashSet<Integer> occurred = new HashSet<>();
        for (int i = from + 1; i < nums.length; ++i) {
            if (pathTail <= nums[i] && !occurred.contains(nums[i])) {
                occurred.add(nums[i]);
                path.add(nums[i]);
                search(i, path, nums[i]);
                path.remove(path.size() - 1);
            }
        }
    }
}
