package increasing_subsequences_491;

import java.util.*;

/**
 * It's a rewriting of the previous solution.
 *
 * I think the code is more clearer, even though it isn't recursive.
 *
 * But somehow it's much slower (67ms vs 28ms as per measured by LeetCode).
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution2 {
    int[] nums;

    // answers[i] = subsequences that start from nums[i].
    List<List<Integer>>[] answers;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        int n = nums.length;

        answers = new List[n];
        for (int i = 0; i < n; ++i)
            answers[i] = new LinkedList<>();

        /* Build the answers in a manner like this (assume the input is {4, 6, 7, 7}):

                answers[2]:
                7,7
                answers[1]:
                6,7
                6,7,7
                answers[0]:
                4,6
                4,7
                4,6,7
                4,6,7,7
                4,7,7
         */
        for (int i = n - 2; i >= 0; --i) {

            // Collect subsequences of two elements: [i, j].
            // If there are [j] == [j2], we ignore j2.
            // Notice that although this can avoid duplicates in answers[i],
            // there might still be duplicates after all the answers are combined.
            HashSet<Integer> occurred = new HashSet<>();
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] <= nums[j] && !occurred.contains(nums[j])) {
                    occurred.add(nums[j]);
                    List<Integer> newSubseq = new LinkedList<>();
                    newSubseq.add(nums[i]);
                    newSubseq.add(nums[j]);
                    answers[i].add(newSubseq);
                }
            }

            // For each of the subsequences that start from [j] > [i],
            // prepend nums[i] to it and get a new subsequence.
            for (int j = i + 1; j < n; ++j) {
                if (nums[i] <= nums[j]) {
                    for (List<Integer> suffix : answers[j]) {
                        List<Integer> newSubseq = new LinkedList<>();
                        newSubseq.add(nums[i]);
                        newSubseq.addAll(suffix);
                        answers[i].add(newSubseq);
                    }
                }
            }

            // For debug purpose
//            System.out.printf("answers[%d]:%n", i);
//            for (List<Integer> l : answers[i]) {
//                System.out.println(l.stream().map(String::valueOf).collect(Collectors.joining(",")));
//            }
        }

        // Combine answers.
        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < answers.length; ++i) {
            if (answers[i] != null)
                ans.addAll(answers[i]);
        }

        // Deduplicate.
        return deduplicate(ans);
    }

    private ArrayList<List<Integer>> deduplicate(ArrayList<List<Integer>> ans) {
        Comparator<List<Integer>> listComparator = new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> s1, List<Integer> s2) {
                return compareSeq(s1.iterator(), s2.iterator());
            }

            int compareSeq(Iterator<Integer> s1, Iterator<Integer> s2) {
                if (s1.hasNext() && s2.hasNext()) {
                    int d = s1.next() - s2.next();
                    if (d == 0)
                        return compareSeq(s1, s2);
                    else
                        return d;
                } else if (s1.hasNext()) {
                    return 1;
                } else if (s2.hasNext()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        ans.sort(listComparator);

        ArrayList<List<Integer>> ans2 = new ArrayList<>(ans.size());
        List<Integer> prev = null;
        for (List<Integer> s : ans) {
            if (prev == null || listComparator.compare(prev, s) != 0) {
                ans2.add(s);
                prev = s;
            }
        }

        return ans2;
    }
}
