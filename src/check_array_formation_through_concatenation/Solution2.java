package check_array_formation_through_concatenation;

import _lib.IntArrays;

import java.util.Arrays;

/**
 * A straightforward approach: searching each piece in arr.
 *
 * Created by Zhiyong Pan on 2021-01-01.
 */
public class Solution2 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        for (int p = 0; p < pieces.length; ++p) {
            int i = IntArrays.indexOf(arr, pieces[p][0]);
            if (i < 0 || i + pieces[p].length > arr.length)
                return false;
            if (Arrays.compare(
                    pieces[p], 0, pieces[p].length,
                    arr, i, i + pieces[p].length) != 0)
                return false;
        }
        return true;
    }
}

