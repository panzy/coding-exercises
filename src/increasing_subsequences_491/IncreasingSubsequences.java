package increasing_subsequences_491;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        return new Solution3_graph().findSubsequences(nums);
    }

    @Test
    void example1() {
        List<List<Integer>> seqs = findSubsequences(new int[]{4, 6, 7, 7});
        Assertions.assertEquals(8, seqs.size());
    }

    @Test
    void example2() {
        List<List<Integer>> seqs = findSubsequences(new int[]{4, 3, 2, 1});
        Assertions.assertEquals(0, seqs.size());
    }

    @Test
    void example3() {
        List<List<Integer>> seqs = findSubsequences(new int[]{1, 2, 1, 1});
        Assertions.assertEquals(3, seqs.size());
    }

    @Test
    void example4() {
        List<List<Integer>> seqs = findSubsequences(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1});
        Assertions.assertEquals(1018, seqs.size());
    }

    @Test
    void example5() {
        List<List<Integer>> seqs = findSubsequences(new int[]{1, 3, 5, 7});
        // Expected: [[1,3],[1,3,5],[1,3,5,7],[1,3,7],[1,5],[1,5,7],[1,7],[3,5],[3,5,7],[3,7],[5,7]]
        Assertions.assertEquals(11, seqs.size());
    }

    @Test
    void example6() {
        List<List<Integer>> seqs = findSubsequences(new int[]{100, 90, 80, 70, 60, 50, 60, 70, 80, 90, 100});
        Assertions.assertEquals(88, seqs.size());
    }

    @Test
    void example7() {
        List<List<Integer>> seqs = findSubsequences(new int[]{4, 2, 3, 1});
        Assertions.assertEquals(1, seqs.size());
    }
}
