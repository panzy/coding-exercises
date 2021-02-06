package increasing_subsequences_491;

import java.util.*;

/**
 * An ugly solution. But it works.
 *
 * It's surprisingly frustrating to get it work correctly.
 *
 * The ugly part is that I had to deduplicate in the end.
 *
 * Created by Zhiyong Pan on 2021-01-20.
 */
public class Solution {
    int[] nums;

    // answers[i] = subsequences that start from nums[i].
    List<List<Integer>>[] answers;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        answers = new List[nums.length];

        for (int i = 0; i < nums.length; ++i)
            subseq(i);

        ArrayList<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < answers.length; ++i) {
            if (answers[i] != null)
                ans.addAll(answers[i]);
        }

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

        // For debug purpose
//        for (List<Integer> l : ans2) {
//            System.out.println(l.stream().map(String::valueOf).collect(Collectors.joining(",")));
//        }

        return ans2;
    }

    List<List<Integer>> subseq(int begin) {
        if (answers[begin] != null)
            return answers[begin];

        List<List<Integer>> seqs = new LinkedList<>();
        HashSet<Integer> visitedNums = new HashSet<>();
        for (int i = begin + 1; i < nums.length; ++i) {
            if (nums[i] >= nums[begin] && !visitedNums.contains(nums[i])) {
                visitedNums.add(nums[i]);
                // Found a subseq: [begin, i]
                {
                    List<Integer> l2 = new LinkedList<>();
                    l2.add(nums[begin]);
                    l2.add(nums[i]);
                    seqs.add(l2);
                }

                for (List<Integer> l : subseq(i)) {
                    if (l.size() > 0) {
                        List<Integer> l2 = new LinkedList<>();
                        l2.add(nums[begin]);
                        l2.addAll(l);
                        seqs.add(l2);
                    }
                }
            }
        }

        return answers[begin] = seqs;
    }
}
