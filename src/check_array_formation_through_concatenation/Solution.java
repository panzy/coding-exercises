package check_array_formation_through_concatenation;

import java.util.*;

/**
 * A straightforward approach: binary-searching each arr part in pieces.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        // sort pieces by their first number
        Arrays.sort(pieces, (o1, o2) -> o1[0] - o2[0]);

        for (int i = 0; i < arr.length;) {
            // search the piece that starts with arr[i]
            int finalI = i;
            int p = Arrays.binarySearch(
                    pieces,
                    arr, // actually, what we want to search is arr[i:i+x.length]
                    (x, y) -> Arrays.compare(
                            // x is the current piece
                            x, 0, x.length,
                            // y is arr the whole
                            arr, finalI, Math.min(arr.length, finalI + x.length)));
            if (p < 0)
                return false;
            else
                i += pieces[p].length;
        }

        return true;
    }
}
