package minimum_genetic_mutation;

import word_ladder.Solution4;

import java.util.*;

/**
 * Created by Zhiyong Pan on 2021-01-14.
 */
public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        int len = new Solution4().ladderLength(start, end, Arrays.asList(bank));
        return len == -1 ? -1 : len - 1;
    }
}
